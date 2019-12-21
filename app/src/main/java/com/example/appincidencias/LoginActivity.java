package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText correo, contrasenia;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.correo = findViewById(R.id.txt_correo2);
        this.contrasenia = findViewById(R.id.txt_contrasenia2);
        this.login = findViewById(R.id.btn_iniciarSesion2);
        this.log = findViewById(R.id.txt_log);

        try {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String correoTexto = correo.getText().toString().trim();
                    final String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!correoTexto.matches(regex) || correo.getText().toString().equals("") || contrasenia.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "Verifique sus datos", Toast.LENGTH_LONG).show();
                    }

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    // Se indica la direccion del servidor que se va a utilizar
                    String url = "http://54.227.173.39/Incidencias/LogIn.php";

                    // Se establece la conexion con el servidor
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("${\"usuario_activado\":\"0\"}")){
                                Intent intent = new Intent(LoginActivity.this, ActivarUsuarioActivity.class);
                                intent.putExtra("correo", correo.getText().toString()); // Se envia el correo del usuario a la siguiente actividad
                                startActivity(intent);
                            } else if(response.equals("${\"usuario_activado\":\"1\"}")){
                                startActivity(new Intent(LoginActivity.this, MenuPrincipalActivity.class));
                                Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                            } else {
                                correo.setText("");
                                contrasenia.setText("");
                                Toast.makeText(LoginActivity.this, "Credenciales incorrectos", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }){
                        // Se pasan los parametros que se van a utilizar
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> parametros = new HashMap<>();
                            parametros.put("correo", correo.getText().toString());
                            parametros.put("contrasenia", contrasenia.getText().toString());
                            return parametros;
                        }
                    };

                    // Se hace la consulta
                    requestQueue.start();
                    requestQueue.add(stringRequest);

                    /*int logInState = ws.logIn(correo.getText().toString(), contrasenia.getText().toString());
                    if(logInState == 1) {
                        startActivity(new Intent(LoginActivity.this, MenuPrincipalActivity.class));
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                    } else if(logInState == 0) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(LoginActivity.this, ActivarUsuarioActivity.class);
                        intent.putExtra("correo", correo.getText().toString()); // Se envia el correo del usuario a la siguiente actividad
                        startActivity(intent);
                    } else if(logInState == -2){
                        Toast.makeText(LoginActivity.this,"No sirve", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales incorrectos", Toast.LENGTH_LONG).show();
                        correo.setText("");
                        contrasenia.setText("");
                    }*/

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Inicio de sesion fallido", Toast.LENGTH_LONG).show();
        }
    }
}
