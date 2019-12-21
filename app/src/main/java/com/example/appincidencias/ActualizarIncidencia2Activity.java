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


public class ActualizarIncidencia2Activity extends AppCompatActivity {

    private Button btnActualizar;
    private EditText ubicacion;
    private EditText descripcion;
    private Bundle recupera = getIntent().getExtras();
    private final int cedula = recupera.getInt("llave");
    private final int idIncidencia = recupera.getInt("idIncidencia");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia2);

        btnActualizar = findViewById(R.id.btn_actualizar_incidencia);
        ubicacion = findViewById(R.id.txt_ubicacion_actualizar);
        descripcion = findViewById(R.id.txt_descripcion_actualizar);

        try {

            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }


}

