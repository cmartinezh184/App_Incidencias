package com.example.appincidencias;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase utilizada para conectarse al servicio web en el servidor
 */
public class IncidenciasWS {

    // Variables

    private final String url = "http://54.227.173.39/Incidencias/";// URL del servidor
    public Context context;// Almacena el contexto de la pantalla donde se invoca

    //Variable para escuchar las respuestas del servidor
    private Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
        }
    };

    //Variable para escuchar respuestas de error del servidor
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    // Constructor

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion donde se ocupa
     */
    public IncidenciasWS(Context context) {
        this.context = context;
    }


    // Metodos

    /**
     * Metodo para iniciar sesion con credenciales de la base de datos. Utiliza POST para la
     * validacion
     * @param correo correo del usuario
     * @param contrasenia contrasenia del usuario
     */
    public void logIn(final String correo, final String contrasenia){

        // Se indica la direccion del servidor que se va a utilizar
        String logIn = url + "LogIn.php";

        // Se establece la conexion con el servidor
        StringRequest stringRequest = new StringRequest(Request.Method.POST, logIn, this.responseListener, this.errorListener) {

            // Se pasan los parametros que se van a utilizar
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo);
                parametros.put("contrasenia", contrasenia);
                return parametros;
            }
        };

        // Se hace la consulta
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.start();
        requestQueue.add(stringRequest);

    }

    public void registrarIncidencia() {
        // Se indica la direccion del servidor que se va a utilizar
        String registrarIncidencia = url + "RegistrarIncidencia.php";
    }

    public void modificarIncidencia() {
        // Se indica la direccion del servidor que se va a utilizar
        String modificarIncidencia = url + "ModificarIncidencia.php";
    }

    /**
     * Metodo que se a traves de un POST registra usuarios a la base de datos
     * @param persona objeto con la informacion ingresada por el usuario
     */
    public void registrarUsuario(final Persona persona) {
        // Se indica la direccion del servidor que se va a utilizar
        String logIn = url + "RegistrarUsuario.php";

        // Se establece la conexion con el servidor
        StringRequest stringRequest = new StringRequest(Request.Method.POST, logIn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                // Codigo para enviar el correo al usuario
            }
        }, this.errorListener) {

            // Se pasan los parametros que se van a utilizar
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("cedula", persona.getCedula() + "");
                parametros.put("primer_nombre", persona.getPrimerNombre());
                parametros.put("segundo_nombre", persona.getSegundoNombre());
                parametros.put("primer_apellido", persona.getPrimerApellido());
                parametros.put("segundo_apellido", persona.getSegundoApellido());
                parametros.put("correo", persona.getCorreo());
                parametros.put("telefono", persona.getTelefono() + "");
                parametros.put("distrito_id", persona.getDistritoID() + "");
                parametros.put("direccion", persona.getDireccion());
                parametros.put("contrasenia", persona.getContrasenia());
                return parametros;
            }
        };

        // Se hace la consulta
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.start();
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo para enlistar las incidencias desde la base de datos en el servidor
     * @return lista de incidencias
     */
    public ArrayList<String> enlistarIncidencias(){

        // Se indica la direccion del servidor que se va a utilizar
        String enlistarIncidencias = url + "EnlistarIncidencias.php";

        RequestQueue queue = Volley.newRequestQueue(context);
        final ArrayList<String> lista = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, enlistarIncidencias, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String objectString = object.optString(
                            object.optString("descripcion", "N/A")
                            + " - " + object.optString("latitud", "N/A")
                            + " - " + object.optString("longitud", "N/A")
                    )
                            ;
                    lista.add(objectString);
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error response: ", error);
            }
        });

        /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaProductos.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        queue.add(jsonArrayRequest);*/

        return lista;
    }

    /**
     * Metodo que busca una incidencia por su ID en el servidor
     * @param idIncidencia ID de la incidencia
     * @return objeto con la informacion de la incidencia
     */
    public Incidencia consultarIncidencia(int idIncidencia) {

        // Se indica la direccion del servidor que se va a utilizar
        String consultarIncidencia = url + "ConsultarInciedncia.php?incidencia_id=" +idIncidencia ;

        final Incidencia incidencia = new Incidencia();

        RequestQueue queue = Volley.newRequestQueue(context);
        final ArrayList<String> lista = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, consultarIncidencia, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String objectString = object.optString(
                            object.optString("descripcion", "N/A")
                                    + " - " + object.optString("latitud", "N/A")
                                    + " - " + object.optString("longitud", "N/A")
                    );

                    incidencia.setIdIncidencia(Integer.parseInt(object.optString("incidencia_id")));
                    incidencia.setDescripcion(object.optString("descripcion"));
                    incidencia.setIdUsuario(Integer.parseInt(object.optString("usuario_id")));
                    incidencia.setLatitud(object.optString("latitud"));
                    incidencia.setLongitud(object.optString("longitud"));

                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error response: ", error);
            }
        });

        return incidencia;
    }
}
