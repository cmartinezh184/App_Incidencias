package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class RegistrarUsuarioActivity extends AppCompatActivity {

    private Button registrar;
    private TextView Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia, DistritoID;
    private IncidenciasWS ws;
    private Persona usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        // Web Service
        ws = new IncidenciasWS(getApplicationContext());
        usuario = new Persona();

        //Extraer los datos
        Cedula = (TextView) findViewById(R.id.txt_cedula_registro);
        Nombre = (TextView) findViewById(R.id.txt_nombre_registro);
        PrimerApellido = (TextView) findViewById(R.id.txt_primer_apellido_registro);
        SegundoApellido = (TextView) findViewById(R.id.txt_segundo_apellido);
        DireccionID = (TextView) findViewById(R.id.txt_direccion_registro);
        Correo = (TextView) findViewById(R.id.txt_correo_registro);
        Telefono = (TextView) findViewById(R.id.txt_telefono_registro);
        Contrasenia = (TextView) findViewById(R.id.pswd_contrasenia_registro);
        DistritoID = (TextView) findViewById(R.id.txt_distrito_registro);

        // Botones
        registrar = findViewById(R.id.btn_registrarse2);

        try {
            //Evento de componente
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String correoTexto = Correo.getText().toString().trim();
                    final String telefonoTexto = Telefono.getText().toString();
                    final String regexCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // Se valida que el correo tenga la estructura de 'letras'@'letras'.'letras'
                    final String regexTelefono = "^[+]?[0-9]{8,11}$"; // Se valida que el telefono tenga entre 8 a 11 digitos y solo numeros
                    if (!correoTexto.matches(regexCorreo) || !telefonoTexto.matches(regexTelefono)) { // Se comparan los valores ingresados con las validaciones de correo y telefono
                        Toast.makeText(RegistrarUsuarioActivity.this, "Ingrese un correo/telefono valido", Toast.LENGTH_LONG).show();
                    } else { // Si la validacion es exitosa se registra el usuario

                        final int codigoActivacion = (int) ((Math.random()*((99999-10000)+1))+1000);
                        // Se indica la direccion del servidor que se va a utilizar
                        String url = "http://54.227.173.39/Incidencias/RegistrarUsuario.php";

                        // Se establece la conexion con el servidor
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrarUsuarioActivity.this, LoginActivity.class));
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {

                            // Se pasan los parametros que se van a utilizar
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> parametros = new HashMap<String, String>();
                                parametros.put("cedula", Cedula.getText().toString());
                                parametros.put("primer_nombre", Nombre.getText().toString());
                                parametros.put("segundo_nombre", "");
                                parametros.put("primer_apellido", PrimerApellido.getText().toString());
                                parametros.put("segundo_apellido", SegundoApellido.getText().toString());
                                parametros.put("correo", Correo.getText().toString());
                                parametros.put("telefono", Telefono.getText().toString());
                                parametros.put("distrito_id", DistritoID.getText().toString());
                                parametros.put("direccion", DireccionID.getText().toString());
                                parametros.put("contrasenia", Contrasenia.getText().toString());
                                parametros.put("codigo", codigoActivacion + "");
                                return parametros;
                            }
                        };

                        // Se hace la consulta
                        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                        requestQueue.start();
                        requestQueue.add(stringRequest);
                        new EmailSender(Correo.getText().toString(), codigoActivacion).execute();

                       /* // Se pasan los a un objeto Persona
                        usuario.setPrimerNombre(Nombre.getText().toString());
                        usuario.setSegundoNombre("");
                        usuario.setCedula(Integer.parseInt(Cedula.getText().toString()));
                        usuario.setPrimerApellido(PrimerApellido.getText().toString());
                        usuario.setSegundoApellido(SegundoApellido.getText().toString());
                        usuario.setDireccion(DireccionID.getText().toString());
                        usuario.setDistritoID(Integer.parseInt(DistritoID.getText().toString()));
                        usuario.setCorreo(Correo.getText().toString());
                        usuario.setTelefono(Integer.parseInt(Telefono.getText().toString()));
                        usuario.setContrasenia(Contrasenia.getText().toString());

                        // Se registra el objeto persona en la base de datos
                        ws.registrarUsuario(usuario);
                        startActivity(new Intent(RegistrarUsuarioActivity.this, LoginActivity.class));*/
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegistrarUsuarioActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
    }


        private void AgregarUsuario(View v){
            DBHelper conn= new DBHelper(this, "Usuario", null, 1);
            SQLiteDatabase bd =  conn.getWritableDatabase();

            try{
                if(!Cedula.equals("") && !Nombre.equals("") && !PrimerApellido.equals("")
                        && !SegundoApellido.equals("")&& !DireccionID.equals("") && !Correo.equals("")
                        && !Telefono.equals("") && !Contrasenia.equals("")) {

                    ContentValues valores = new ContentValues();//recoge toda la informacion en la pantalla y lo guarda, es como un Hash
                    valores.put("Cedula", Cedula.getText().toString());
                    valores.put("Nombre", String.valueOf(Nombre));
                    valores.put("PrimerApellido",String.valueOf(PrimerApellido));
                    valores.put("SegundoApellido",String.valueOf(SegundoApellido));
                    valores.put("DireccionID",String.valueOf(DireccionID));
                    valores.put("Correo",String.valueOf(Correo));
                    valores.put("Telefono",String.valueOf(Telefono));
                    valores.put("Contrasenia",String.valueOf(Contrasenia));
                    bd.insert("Persona", null, valores);
                    bd.close();

                    Cedula.setText("");
                    Nombre.setText("");
                    PrimerApellido.setText("");
                    SegundoApellido.setText("");
                    DireccionID.setText("");
                    Correo.setText("");
                    Telefono.setText("");
                    Contrasenia.setText("");

                    Toast.makeText(this, "Se registró exitosamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Es necesario llear todos los campos", Toast.LENGTH_LONG).show();
                }


            }catch (Exception ex){
                Toast.makeText(this, "Error al procesar en la Base de Datos"+ ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }


    }
