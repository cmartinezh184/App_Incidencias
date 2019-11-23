package com.example.appincidencias;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class listas extends Activity {


        ListView list_listas;
        ArrayList<String> listarInfo;
        ArrayList<Incidencia> listaincidencia;


    @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.lista_incidencias);

            list_listas = (ListView) findViewById(R.id.list_listas);

        Toast.makeText(this, "Entro a Create", Toast.LENGTH_SHORT).show();


            ListarListas();

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listarInfo);
            list_listas.setAdapter(adapter);
        }

        private void ListarListas() {
            DBHelper conn = new DBHelper(this, "Administrador", null, 1);
            SQLiteDatabase bd = conn.getWritableDatabase();

            Incidencia lista = null;
            listaincidencia = new ArrayList<Incidencia>();

            Toast.makeText(this,"Entro a listar",Toast.LENGTH_SHORT).show();
            try {
                Cursor fila = bd.rawQuery
                        ("SELECT * FROM bd", null);
                while (fila.moveToNext()) {
                    // Se obtiene el siguiente contacto en la lista y se ingresa al objeto lista
                    lista= new Incidencia();
                    lista.setDescripcion(fila.getString(0));
                    lista.setIdIncidencia(Integer.parseInt(fila.getString(1)));
                    lista.setIdUsuario(Integer.parseInt(fila.getString(2)));
                    lista.setUbicacion(fila.getString(3));
                    // Se agrega el nuevo contacto obtenido a la lista de lista
                    listaincidencia.add(lista);
                }

                ObtenerLista();
            } catch (Exception e) {
                Toast.makeText(this, "Error " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    private void ObtenerLista () {
        listarInfo = new ArrayList<String>();
        for (int i = 0; i < listaincidencia.size(); i++) {
            listarInfo.add(listaincidencia.get(i).getIdIncidencia() + " - " + listaincidencia.get(i).getIdUsuario() + " - " + listaincidencia.get(i).getDescripcion() + " - " +  listaincidencia.get(i).getUbicacion());
        }
    }

    }

