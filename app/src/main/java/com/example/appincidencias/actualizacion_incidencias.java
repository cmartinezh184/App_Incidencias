package com.example.appincidencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class actualizacion_incidencias extends AppCompatActivity{
    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizacion_incidencias);

        btnActualizar = findViewById(R.id.btn_siguiente_incidencia);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(actualizacion_incidencias.this, actualizar_incidencia_mapa.class));

            }
        });
    }
}





