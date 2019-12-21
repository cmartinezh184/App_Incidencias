package com.example.appincidencias;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaIncidenciasActivity extends AppCompatActivity {

    private ListView listaIncidencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_incidencias);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.tlbr_lista);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        listaIncidencias = (ListView) findViewById(R.id.listView_incidencias);

        cargarLista();
    }

    private void cargarLista(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final ArrayList<String> listaIncidenciasDB = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://54.227.173.39/Incidencias/EnlistarIncidencias.php", null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String objectString = object.optString("incidencia_id", "N/A")
                            + " - Detalle: " + object.optString("descripcion", "N/A")
                            + " - LAT: " + object.optString("latitud", "N/A")
                            + " - LONG: " + object.optString("longitud", "N/A")
                            + " - Categ: " + object.optString("categoria", "N/A")
                            + " - Empresa: " + object.optString("empresa", "N/A");
                    listaIncidenciasDB.add(objectString);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ListaIncidenciasActivity.this, android.R.layout.simple_list_item_1, listaIncidenciasDB);
                listaIncidencias.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error response: ", error);
            }
        });



        queue.add(jsonArrayRequest);

    }


}
