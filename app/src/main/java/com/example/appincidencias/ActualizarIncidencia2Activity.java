package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActualizarIncidencia2Activity extends AppCompatActivity {

    private Button btnActualizarIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia2);

        btnActualizarIncidencia = findViewById(R.id.btn_actualizar_incidencia);

        btnActualizarIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualizarIncidencia2Activity.this, MenuPrincipalActivity.class));
            }
        });
    }
}

