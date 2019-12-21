package com.example.appincidencias;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private boolean cargarLista(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final ArrayList<String> lista = new ArrayList<>();
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
                            + " - " + object.optString("descripcion", "N/A")
                            + " - " + object.optString("latitud", "N/A")
                            + " - " + object.optString("longitud", "N/A")
                            + " - " + object.optString("categoria", "N/A")
                            + " - " + object.optString("empresa", "N/A");
                    lista.add(objectString);
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error response: ", error);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaIncidencias.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        queue.add(jsonArrayRequest);

        return true;
    }


}
