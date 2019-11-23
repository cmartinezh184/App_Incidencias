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

import java.util.Random;


public class AgregarIncidencia2Activity extends AppCompatActivity {

    private Button btnRegistrar;
    private EditText ubicacion;
    private EditText descripcion;
    private Bundle recupera = getIntent().getExtras();
    private final int cedula = recupera.getInt("llave");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_incidencia2);

        btnRegistrar = findViewById(R.id.btn_agregar_incidencia);
        ubicacion = findViewById(R.id.txt_ubicacion_agregar);
        descripcion = findViewById(R.id.txt_descripcion_agregar);

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

        incidencia.setUbicacion(ubicacion.getText().toString());
        incidencia.setDescripcion(descripcion.getText().toString());
        incidencia.setIdUsuario(cedula);
        incidencia.setIdIncidencia(r.nextInt());

        if(!incidencia.getDescripcion().isEmpty() || incidencia.getIdUsuario() != 0 || !incidencia.getUbicacion().isEmpty()){
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
}

