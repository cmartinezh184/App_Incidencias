package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class detalle_incidencia extends AppCompatActivity {

    private Button btnIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_incidencia);

        btnIncidencia = findViewById(R.id.btn_siguiente_incidencia);

        btnIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detalle_incidencia.this, detalle_incidencia2.class));


            }
        });
    }
}





