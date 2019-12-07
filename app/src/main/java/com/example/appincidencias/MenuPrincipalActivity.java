package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuPrincipalActivity extends AppCompatActivity {

    private ImageButton incidencias;
    private ImageButton actualizar;
    private Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        incidencias = findViewById(R.id.btn_incidencias_menu);
        actualizar = findViewById(R.id.btn_actualizar_incidencia_menu);
        agregar = findViewById(R.id.btn_registrar_incidencia_menu);

        incidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, listas.class));


            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, AgregarIncidencia1Activity.class));
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, ActualizarIncidencia1Activity.class));
            }
        });
    }
}
