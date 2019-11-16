package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class actualizar_incidencia_mapa extends AppCompatActivity {

    private Button btnActualizarIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_incidencia_mapa);

        btnActualizarIncidencia = findViewById(R.id.btn_registrarse2);

        btnActualizarIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(actualizar_incidencia_mapa.this, menuPrincipal.class));
            }
        });
    }
}

