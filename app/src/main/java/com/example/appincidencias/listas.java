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
        ArrayList<listas> listaincidencia;


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

            listas lista = null;
            listaincidencia = new ArrayList<listas>();

            Toast.makeText(this,"Entro a listar",Toast.LENGTH_SHORT).show();
            try {
                Cursor fila = bd.rawQuery
                        ("SELECT * FROM bd", null);
                while (fila.moveToNext()) {
                    // Se obtiene el siguiente contacto en la lista y se ingresa al objeto lista
                    lista = new listas();
                    lista.setCedula(fila.getString(0));
                    lista.setNombre(fila.getString(1));
                    lista.setPrimerApellido(fila.getString(2));
                    lista.setSegundoApellido(fila.getString(3));
                    lista.setDireccionID(fila.getString(4));
                    lista.setCorreo(fila.getString(5));
                    lista.setTelefono(fila.getString(6));
                    lista.setContraenia(fila.getString(7));
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
            listarInfo.add(listaincidencia.get(i).getCedula() + " - " + listaincidencia.get(i).getNombre() + " - " + listaincidencia.get(i).getPrimerApellido) + " - " +  listaincidencia.get(i).getSegundoApellido() + " - " + listaincidencia.get(i).getDireccionID() + " - " + listaincidencia.get(i).getTelefono() + " - " + listaincidencia.get(i).getContrasenia() + " - " + listaincidencia.get(i).getTelefono());
        }
    }

    }

