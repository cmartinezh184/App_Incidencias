package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText correo;
    private EditText contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.correo = findViewById(R.id.txt_correo2);
        this.contrasenia = findViewById(R.id.txt_contrasenia2);
        this.login = findViewById(R.id.btn_iniciarSesion2);



        try {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String correoTexto = correo.getText().toString().trim();
                    final String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!correoTexto.matches(regex)) {
                        Toast.makeText(LoginActivity.this, "Ingrese un correo valido", Toast.LENGTH_LONG).show();
                    } else if (!LogIn(correo.getText().toString(), contrasenia.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Inicio de sesion fallido", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, menuPrincipal.class));
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Inicio de sesion fallido", Toast.LENGTH_LONG).show();
        }


    }


    private boolean LogIn(String correo, String contrasenia){
        if(!correo.equals("") && !contrasenia.equals("")) {
            Toast.makeText(this, "Sesion Iniciada", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
