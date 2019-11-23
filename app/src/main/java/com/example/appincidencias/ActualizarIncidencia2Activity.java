package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class ActualizarIncidencia2Activity extends AppCompatActivity {

    private Button btnActualizar;
    private EditText ubicacion;
    private EditText descripcion;
    private Bundle recupera = getIntent().getExtras();
    private final int cedula = recupera.getInt("llave");
    private final int idIncidencia = recupera.getInt("idIncidencia");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_incidencia2);

        btnActualizar = findViewById(R.id.btn_actualizar_incidencia);
        ubicacion = findViewById(R.id.txt_ubicacion_actualizar);
        descripcion = findViewById(R.id.txt_descripcion_actualizar);

        try {

            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ActualizarIncidencia(v)) {
                        startActivity(new Intent(ActualizarIncidencia2Activity.this, MenuPrincipalActivity.class));
                        Toast.makeText(null, "Incidencia actualizada correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(null, "No se ha podido registrar la incidencia", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ActualizarIncidencia(View v) {
        DBHelper conn = new DBHelper(this, "Usuario", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        Incidencia incidencia = new Incidencia();

        incidencia.setUbicacion(ubicacion.getText().toString());
        incidencia.setDescripcion(descripcion.getText().toString());
        incidencia.setIdUsuario(cedula);
        incidencia.setIdIncidencia(idIncidencia);

        if(!incidencia.getDescripcion().isEmpty() || incidencia.getIdUsuario() != 0 || !incidencia.getUbicacion().isEmpty()){
            ContentValues valores = new ContentValues();
            valores.put("Ubicacion", ubicacion.getText().toString());
            valores.put("Descripcion", descripcion.getText().toString());
            valores.put("idIncidencia", idIncidencia);
            valores.put("idPersona", cedula);

            int cantidad = db.update("Incidencia", valores, "cedula =" + cedula, null);
            db.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Incidencia agregada exitosamente", Toast.LENGTH_LONG).show();
                return true;
            }

            Toast.makeText(this, "Ha ocurrido un error",
                    Toast.LENGTH_LONG).show();

            return false;
        }

        Toast.makeText(this, "Debe llenar todos los campos",
                Toast.LENGTH_LONG).show();
        return false;

    }
}

