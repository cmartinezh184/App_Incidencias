package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;


public class AgregarIncidencia2Activity extends AppCompatActivity implements OnMapReadyCallback {

    private Button btnRegistrar;
    private EditText ubicacion;
    private EditText descripcion;
    private MapView mapa;
   /* private Bundle recupera = getIntent().getExtras();*/
    /*private final int cedula = recupera.getInt("llave");*/

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_incidencia2);

        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        btnRegistrar = findViewById(R.id.btn_agregar_incidencia);
        ubicacion = findViewById(R.id.txt_ubicacion_agregar);
        descripcion = findViewById(R.id.txt_descripcion_agregar);
        mapa = (MapView) findViewById(R.id.mapView_agregar);

        mapa.onCreate(mapViewBundle);
        mapa.getMapAsync(this);

        try {

            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AgregarIncidencia(v)) {
                        startActivity(new Intent(AgregarIncidencia2Activity.this, MenuPrincipalActivity.class));
                    } else {
                        Toast.makeText(null, "No se ha podido registrar la incidencia", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean AgregarIncidencia(View v) {
        DBHelper conn = new DBHelper(this, "Usuario", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        Random r = new Random();

        Incidencia incidencia = new Incidencia();

        incidencia.setDescripcion(descripcion.getText().toString());
        incidencia.setIdUsuario(1);
        incidencia.setIdIncidencia(r.nextInt());

        if(!incidencia.getDescripcion().isEmpty() || incidencia.getIdUsuario() != 0 /*|| !incidencia.getUbicacion().isEmpty()*/){
            ContentValues valores = new ContentValues();
            valores.put("Ubicacion", ubicacion.getText().toString());
            valores.put("Descripcion", descripcion.getText().toString());

            db.insert("Incidencia", null, valores);
            db.close();

            Toast.makeText(this, "Incidencia agregada exitosamente",
                    Toast.LENGTH_LONG).show();

            return true;
        }

        Toast.makeText(this, "Debe llenar todos los campos",
                Toast.LENGTH_LONG).show();
        return false;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if(mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapa.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapa.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapa.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    protected void onPause() {
        mapa.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapa.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapa.onLowMemory();
    }
}

