package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menuPrincipal extends AppCompatActivity {

    private ImageButton incidencias;
    private ImageButton actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        incidencias = findViewById(R.id.btn_incidencias_menu);
        actualizar = findViewById(R.id.btn_actualizar_menu);

        incidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Codigo para pasarse a la actividad de incidencias

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Codigo para pasarse a la actividad de incidencias
            }
        });
    }
}
