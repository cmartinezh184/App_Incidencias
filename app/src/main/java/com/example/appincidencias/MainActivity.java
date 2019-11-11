package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button registrar;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Bienvenido!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Creacion de los botones para el cambio de pantalla
        registrar = (Button)findViewById(R.id.button4);
        login = (Button)findViewById(R.id.button3);

        // Redireccionamiento de los botones a las actividades correspondiedtes

            // Registro de Usuario
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrarUsuarioActivity.class));
            }
        });

            // Inicio de Sesion
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

}
