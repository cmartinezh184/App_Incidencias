package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuPrincipalActivity extends AppCompatActivity {

    private ImageButton incidencias;
    private ImageButton actualizar;
    private Button lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.tlbr_menu);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        incidencias = findViewById(R.id.btn_incidencias_menu);
        actualizar = findViewById(R.id.btn_actualizar_incidencia_menu);
        lista = findViewById(R.id.btn_lista_incidencias);

        incidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, AgregarIncidenciaActivity.class));


            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, ListaIncidenciasActivity.class));

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipalActivity.this, ActualizarIncidenciaActivity.class));
            }
        });
    }
}
