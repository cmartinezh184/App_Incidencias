package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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


public class RegistrarUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button registrar;
    private TextView Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia;
    private Spinner SpinnerProvincia, SpinnerDistrito, SpinnerCantonSJ, SpinnerCantonHE, SpinnerCantonLI, SpinnerCantonGUA, SpinnerCantonPUN, SpinnerCantonCAR, SpinnerCantonALA;
    private ArrayAdapter<String> adapterProvincia, adapterDistrito, adapterCantonSJ, adapterCantonHE, adapterCantonLI, adapterCantonGUA, adapterCantonPUN,adapterCantonCAR, adapterCantonALA;
    private IncidenciasWS ws;
    private Persona usuario;

    //Items Provincias
    String ProvinciasItems[]={"San José","Heredia","Limón","Guanacaste","Puntarenas","Cartago","Alajuela"};

    //Items Cantones
    String CantonSanJose[]={"Escazú", "Curridabat", "Desamparados"};
    String CantonHeredia[]={"Flores", "Belén","Barva"};
    String CantonLimon[]={"Guácimo","Matina","Pococí"};
    String CantonGuanacaste[]={"Liberia","La Cruz","Abangares"};
    String CantonPuntarenas[]={"Buenos Aires","Corredores","Coto Brus"};
    String CantonCartago[]={"Oreamuno","El Guarco","Tierra Blanca"};
    String CantonAlajuela[]={"Atenas","Grecia","Guatuso"};

    //Items Distritos
    String[] DistritosSanJose={"San Jose","San Antonio","Granadilla","San Rafael Arriba"};


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
        SpinnerProvincia = (Spinner) findViewById(R.id.Provincias_Usuario);
        SpinnerCantonSJ = (Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonHE=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonLI=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonGUA=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonPUN=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonCAR=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerCantonALA=(Spinner) findViewById(R.id.Canton_Usuario);
        SpinnerDistrito = (Spinner) findViewById(R.id.Distrito_Usuario);
        DireccionID = (TextView) findViewById(R.id.txt_direccion_registro);
        Correo = (TextView) findViewById(R.id.txt_correo_registro);
        Telefono = (TextView) findViewById(R.id.txt_telefono_registro);
        Contrasenia = (TextView) findViewById(R.id.pswd_contrasenia_registro);

        SpinnerProvincia.setOnItemSelectedListener(this);
        adapterProvincia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,ProvinciasItems);
        SpinnerProvincia.setAdapter(adapterProvincia);

        SpinnerCantonSJ.setOnItemSelectedListener(this);
        adapterCantonSJ = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonSanJose);
        SpinnerCantonSJ.setAdapter(adapterCantonSJ);

        SpinnerCantonHE.setOnItemSelectedListener(this);
        adapterCantonHE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonHeredia);
        SpinnerCantonHE.setAdapter(adapterCantonHE);

        SpinnerCantonLI.setOnItemSelectedListener(this);
        adapterCantonLI = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonLimon);
        SpinnerCantonLI.setAdapter(adapterCantonLI);

        SpinnerCantonGUA.setOnItemSelectedListener(this);
        adapterCantonGUA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonGuanacaste);
        SpinnerCantonGUA.setAdapter(adapterCantonGUA);

        SpinnerCantonPUN.setOnItemSelectedListener(this);
        adapterCantonPUN = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonPuntarenas);
        SpinnerCantonPUN.setAdapter(adapterCantonPUN);

        SpinnerCantonCAR.setOnItemSelectedListener(this);
        adapterCantonCAR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonCartago);
        SpinnerCantonCAR.setAdapter(adapterCantonCAR);

        SpinnerCantonALA.setOnItemSelectedListener(this);
        adapterCantonALA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CantonAlajuela);
        SpinnerCantonALA.setAdapter(adapterCantonALA);


        SpinnerDistrito.setOnItemSelectedListener(this);
        adapterDistrito = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,DistritosSanJose);
        SpinnerDistrito.setAdapter(adapterDistrito);

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
                                parametros.put("distrito_id", SpinnerDistrito.getSelectedItemPosition() + "");
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

                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegistrarUsuarioActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapter, View view, int position, long arg3) {
        switch (adapter.getId()){
            case R.id.Provincias_Usuario:
                switch(position){
                    case 0:
                        SpinnerCantonSJ.setAdapter(adapterCantonSJ);
                        break;
                    case 1:
                        SpinnerCantonHE.setAdapter(adapterCantonHE);
                        break;
                    case 2:
                        SpinnerCantonLI.setAdapter(adapterCantonLI);
                        break;
                    case 3:
                        SpinnerCantonGUA.setAdapter(adapterCantonGUA);
                        break;
                    case 4:
                        SpinnerCantonPUN.setAdapter(adapterCantonPUN);
                        break;
                    case 5:
                        SpinnerCantonCAR.setAdapter(adapterCantonCAR);
                        break;
                    case 6:
                        SpinnerCantonALA.setAdapter(adapterCantonALA);
                        break;
                }
                break;
            case R.id.Distrito_Usuario:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    }
