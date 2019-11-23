package com.example.appincidencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActualizarIncidencia1Activity extends AppCompatActivity{
    private Button btnActualizar;
    private EditText cedula;
    private EditText nombre;
    private EditText primerApellido;
    private EditText segundoApellido;
    private EditText provincia;
    private EditText canton;
    private EditText distrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia1);

        btnActualizar = findViewById(R.id.btn_siguiente_actualizar_incidencia);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegar = new Intent(ActualizarIncidencia1Activity.this, ActualizarIncidencia2Activity.class);

                // Codigo para llevar los datos a la siguiente activity de actualizacion

                startActivityForResult(navegar, 0);

            }
        });
    }
}





