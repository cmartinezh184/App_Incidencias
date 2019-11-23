package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private Button registrar;
    private Button login;
    private ListView list_listas;

    private EditText Cedula, Nombre, PrimerApellido, SegundoApellido,DireccionID, Correo, Telefono, Contrasenia;
    private Button agregar, consultar, eliminar;
    private ListView list_contactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Creacion de los botones para el cambio de pantalla
        registrar = (Button)findViewById(R.id.btn_registrarse_main);
        login = (Button)findViewById(R.id.btn_login_main);
        list_listas = (ListView) findViewById(R.id.list_listas);

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
