package com.example.appincidencias;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.UUID;


public class ActualizarIncidenciaActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener{

    private Button btnActualizarIncidencia, btnElegirFoto;
    private EditText cedula, descripcion;
    private ArrayAdapter<String> adapterCategoria, adapterEmpresa;
    private TextView txt_latitud, txt_longitud;
    private Spinner Categoria, Empresa;
    private MapView mapa;
    private ImageView foto;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final int PICK_IMAGE_REQUEST = 22;

    private Uri filePath;
    private Bitmap bitmap;

    private Spinner SpinnerProvincia, SpinnerDistrito, SpinnerCantonSJ, SpinnerCantonHE, SpinnerCantonLI, SpinnerCantonGUA, SpinnerCantonPUN, SpinnerCantonCAR, SpinnerCantonALA;
    private ArrayAdapter<String> adapterProvincia, adapterDistrito, adapterCantonSJ, adapterCantonHE, adapterCantonLI, adapterCantonGUA, adapterCantonPUN,adapterCantonCAR, adapterCantonALA;
    //Items
    String CategoriaItems[]={"Puentes","Carreteras","Servicios Públicos","Servicio Privado"};
    String EmpresaItems[]= {"ICE", "Municipalidad", "Cablera", "AyA"};


    //Items Provincias
    String ProvinciasItems[]={"San José","Heredia","Limón","Guanacaste","Puntarenas","Cartago","Alajuela"};

    //Items Cantones
    String CantonSanJose[]={"Escazú", "Curridabat", "Desamparados"};
    String CantonHeredia[]={"Flores", "Belén","Barva"};
    String CantonLimon[]={"Guácimo","Matina","Pococí"};
    String CantonGuanacaste[]={"Liberia","La Cruz","Abangares"};
    String CantonPuntarenas[]={"Buenos Aires","Corredores","Coto Brus"};
    String CantonCartago[]={"Oreamuno","El Guarco","Tierra Blanca"};
    String CantonAlajuela[]={"Atenas","Grecia","Guatuso"};

    //Items Distritos
    String DistritosSanJose[]={"San Antonio","Granadilla","San Rafael Arriba"};

    private boolean permiso = false;
    private int PERMISSION_ID = 44;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.tlbr_act_incidencia);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnActualizarIncidencia = findViewById(R.id.btn_actualizar_incidencia2);
        cedula = findViewById(R.id.txt_cedula_actualizar_incidencia);
        Categoria = (Spinner) findViewById(R.id.categoria_actualizar);
        Empresa = (Spinner) findViewById(R.id.empresa_actualizar);
        SpinnerProvincia = (Spinner) findViewById(R.id.Provincias_actualizar);
        SpinnerCantonSJ = (Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonHE=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonLI=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonGUA=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonPUN=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonCAR=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerCantonALA=(Spinner) findViewById(R.id.Canton_actualizar);
        SpinnerDistrito = (Spinner) findViewById(R.id.Distrito_actualizar);
        descripcion = findViewById(R.id.txt_descripcion_actualizar);
        txt_latitud = findViewById(R.id.txt_latitud_actualizar);
        txt_longitud = findViewById(R.id.txt_longitud_actualizar);
        foto = (ImageView) findViewById(R.id.img_incidencia_actualizar);
        btnElegirFoto = (Button) findViewById(R.id.btn_subirFoto_actualizar);

        Categoria.setOnItemSelectedListener(this);
        adapterCategoria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CategoriaItems);
        Categoria.setAdapter(adapterCategoria);

        Empresa.setOnItemSelectedListener(this);
        adapterEmpresa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,EmpresaItems);
        Empresa.setAdapter(adapterEmpresa);

        SpinnerProvincia.setOnItemSelectedListener(this);
        adapterProvincia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,ProvinciasItems);
        SpinnerProvincia.setAdapter(adapterProvincia);

        SpinnerCantonSJ.setOnItemSelectedListener(this);
        adapterCantonSJ = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonSanJose);
        SpinnerCantonSJ.setAdapter(adapterCantonSJ);

        SpinnerCantonHE.setOnItemSelectedListener(this);
        adapterCantonHE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonHeredia);
        SpinnerCantonHE.setAdapter(adapterCantonHE);

        SpinnerCantonLI.setOnItemSelectedListener(this);
        adapterCantonLI = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonLimon);
        SpinnerCantonLI.setAdapter(adapterCantonLI);

        SpinnerCantonGUA.setOnItemSelectedListener(this);
        adapterCantonGUA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonGuanacaste);
        SpinnerCantonGUA.setAdapter(adapterCantonGUA);

        SpinnerCantonPUN.setOnItemSelectedListener(this);
        adapterCantonPUN = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonPuntarenas);
        SpinnerCantonPUN.setAdapter(adapterCantonPUN);

        SpinnerCantonCAR.setOnItemSelectedListener(this);
        adapterCantonCAR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonCartago);
        SpinnerCantonCAR.setAdapter(adapterCantonCAR);

        SpinnerCantonALA.setOnItemSelectedListener(this);
        adapterCantonALA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonAlajuela);
        SpinnerCantonALA.setAdapter(adapterCantonALA);


        SpinnerDistrito.setOnItemSelectedListener(this);
        adapterDistrito = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,DistritosSanJose);
        SpinnerDistrito.setAdapter(adapterDistrito);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapa = findViewById(R.id.mapView_actualizar);

        mapa.onCreate(mapViewBundle);
        mapa.getMapAsync(this);

        btnElegirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnElegirFoto){
                    showFileChooser();
                }
            }
        });

        btnActualizarIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = getPath(filePath);

                try{
                    String uploadID = UUID.randomUUID().toString();

                    new MultipartUploadRequest(getApplicationContext(), uploadID, "http://54.227.173.39/Incidencias/ActualizarIncidencia.php")
                            .addFileToUpload(path, "foto")
                            .addParameter("descripcion", descripcion.getText().toString())
                            .addParameter("latitud", txt_latitud.getText().toString())
                            .addParameter("longitud", txt_longitud.getText().toString())
                            .setNotificationConfig(new UploadNotificationConfig())
                            .startUpload();
                    Toast.makeText(getApplicationContext(), "Incidencia Registrada", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ActualizarIncidenciaActivity.this, MenuPrincipalActivity.class));
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se ha podido registrar la incidencia", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ActualizarIncidenciaActivity.this, AgregarIncidenciaActivity.class));
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    txt_latitud.setText(location.getLatitude()+"");
                                    txt_longitud.setText(location.getLongitude()+"");
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            txt_latitud.setText(mLastLocation.getLatitude() + "");
            txt_longitud.setText(mLastLocation.getLongitude()+"");

        }
    };

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }



    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED){
            permiso = true;
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return;
        }
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
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
        getLastLocation();
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
        map.setMyLocationEnabled(true);
        getLastLocation();
        requestNewLocationData();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.Provincias_Registrar:
                switch(position){
                    case 0:
                        SpinnerCantonSJ.setAdapter(adapterCantonSJ);
                        break;
                    case 1:
                        SpinnerCantonHE.setAdapter(adapterCantonHE);
                        break;
                    case 2:
                        SpinnerCantonLI.setAdapter(adapterCantonLI);
                        break;
                    case 3:
                        SpinnerCantonGUA.setAdapter(adapterCantonGUA);
                        break;
                    case 4:
                        SpinnerCantonPUN.setAdapter(adapterCantonPUN);
                        break;
                    case 5:
                        SpinnerCantonCAR.setAdapter(adapterCantonCAR);
                        break;
                    case 6:
                        SpinnerCantonALA.setAdapter(adapterCantonALA);
                        break;
                }
                break;
            case R.id.Distrito_Registrar:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                foto.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);

        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ?",
                new String[]{document_id}, null
        );

        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }
}
