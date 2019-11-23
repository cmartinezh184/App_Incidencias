package com.example.appincidencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActualizarIncidencia1Activity extends AppCompatActivity{
    private Button btnIncidencia;
    private EditText Cedula, Nombre, PrimerApellido, SegundoApellido, Provincia, Canton, Distrito;
    private Spinner Categoria, Empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia1);

        //Extraer los datos
        Cedula = (EditText) findViewById(R.id.txt_cedula_actualizar_incidencia);
        Nombre = (EditText) findViewById(R.id.txt_nombre_actualizar_incidencia);
        PrimerApellido = (EditText) findViewById(R.id.txt_apellido1_actualizar_incidencia);
        SegundoApellido = (EditText) findViewById(R.id.txt_apellido2_actualizar_incidencia);
        Categoria = (Spinner) findViewById(R.id.txt_categoria_incidencia_actualizar);
        Empresa = (Spinner) findViewById(R.id.txt_categoria_empresa_actualizar);
        Provincia = (EditText) findViewById(R.id.txt_provincia_actualizar_incidencia);
        Canton = (EditText) findViewById(R.id.txt_canton_actualizar_incidencia);
        Distrito = (EditText) findViewById(R.id.txt_distrito_actualizar_incidencia);

        // Boton
        btnIncidencia = findViewById(R.id.btn_siguiente_actualizar_incidencia);

        try{
            btnIncidencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent navegar = new Intent(ActualizarIncidencia1Activity.this, ActualizarIncidencia2Activity.class);

                    navegar.putExtra("cedula", Integer.parseInt(Cedula.getText().toString()));
                    navegar.putExtra("idIncidencia", Integer.parseInt("ACÁ VA EL ID DE LA INCIDENCIA QUE VIENE DEL LIST VIEW"));

                    if (!Provincia.getText().equals("") || !Canton.getText().equals("") || !Distrito.getText().equals("") || !Cedula.getText().equals("")) {
                        startActivity(navegar);
                    } else {
                        Toast.makeText(ActualizarIncidencia1Activity.this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(ActualizarIncidencia1Activity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }

    }
}





