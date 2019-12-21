package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ActivarUsuarioActivity extends AppCompatActivity {

    private EditText codigo;
    private Button btn_actualizar;
    private IncidenciasWS ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_usuario);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.tlbcr_activar_usuario);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        ws = new IncidenciasWS(getApplicationContext());

        codigo = (EditText) findViewById(R.id.txt_codigo_activacion);
        btn_actualizar = (Button) findViewById(R.id.btn_activar_usuario);

        final String[] correo = {""};
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            Toast.makeText(ActivarUsuarioActivity.this, "Intente de nuevo", Toast.LENGTH_LONG).show();
        } else {
            correo[0] = extras.getString("correo");
        }

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se indica la direccion del servidor que se va a utilizar
                String url = "http://54.227.173.39/Incidencias/ActivarUsuario.php";

                // Se establece la conexion con el servidor
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1")){
                            startActivity(new Intent(ActivarUsuarioActivity.this, MenuPrincipalActivity.class));
                            Toast.makeText(ActivarUsuarioActivity.this, "Usuario activado", Toast.LENGTH_LONG).show();
                        } else if(response.equals("0")){
                            startActivity(new Intent(ActivarUsuarioActivity.this, LoginActivity.class));
                            Toast.makeText(ActivarUsuarioActivity.this, "Revise sus credenciales", Toast.LENGTH_LONG).show();
                        } else if(response.equals("2")){
                            startActivity(new Intent(ActivarUsuarioActivity.this, LoginActivity.class));
                            Toast.makeText(ActivarUsuarioActivity.this, "No se ha podido activar su usuario, intente mas tarde", Toast.LENGTH_LONG).show();
                        } else {
                            System.out.println(response);
                            startActivity(new Intent(ActivarUsuarioActivity.this, LoginActivity.class));
                            Toast.makeText(ActivarUsuarioActivity.this, "Verifique su codigo", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ActivarUsuarioActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

                    // Se pasan los parametros que se van a utilizar
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parametros = new HashMap<String, String>();
                        parametros.put("correo", correo[0]);
                        parametros.put("codigo_ingresado", codigo.getText().toString());
                        return parametros;
                    }
                };

                // Se hace la consulta
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.start();
                requestQueue.add(stringRequest);
            }
        });
    }
}
