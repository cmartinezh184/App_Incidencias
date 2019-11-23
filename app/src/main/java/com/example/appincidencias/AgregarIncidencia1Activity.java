package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AgregarIncidencia1Activity extends AppCompatActivity {

    private Button btnIncidencia;
    private EditText Cedula, Nombre, PrimerApellido, SegundoApellido, Provincia, Canton, Distrito;
    private Spinner Categoria, Empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_incidencia1);

        //Extraer los datos
        Cedula = (EditText) findViewById(R.id.editText);
        Nombre = (EditText) findViewById(R.id.editText2);
        PrimerApellido = (EditText) findViewById(R.id.txt_cedula_registro);
        SegundoApellido = (EditText) findViewById(R.id.txt_nombre_registro);
        Categoria = (Spinner) findViewById(R.id.spinner2);
        Empresa = (Spinner) findViewById(R.id.spinner);
        Provincia = (EditText) findViewById(R.id.txt_provincia_detalle);
        Canton = (EditText) findViewById(R.id.txt_canton_detalle);
        Distrito = (EditText) findViewById(R.id.txt_distrito_detalle);

        // Boton
        btnIncidencia = findViewById(R.id.btn_siguiente_incidencia);

        try{
            btnIncidencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent navegar = new Intent(AgregarIncidencia1Activity.this, AgregarIncidencia2Activity.class);

                    navegar.putExtra("cedula", Integer.parseInt(Cedula.getText().toString()));

                    if (!Provincia.getText().equals("") || !Canton.getText().equals("") || !Distrito.getText().equals("") || !Cedula.getText().equals("")) {
                        startActivity(navegar);
                    } else {
                        Toast.makeText(AgregarIncidencia1Activity.this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(AgregarIncidencia1Activity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }


     }
}






