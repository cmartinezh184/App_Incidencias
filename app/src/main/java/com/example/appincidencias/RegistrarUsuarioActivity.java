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


public class RegistrarUsuarioActivity extends AppCompatActivity {

    private Button registrar;
    private TextView Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        //Extraer los datos
        Cedula = (TextView) findViewById(R.id.txt_cedula_registro);
        Nombre = (TextView) findViewById(R.id.txt_nombre_registro);
        PrimerApellido = (TextView) findViewById(R.id.txt_primer_apellido_registro);
        SegundoApellido = (TextView) findViewById(R.id.txt_segundo_apellido);
        DireccionID = (TextView) findViewById(R.id.txt_direccion_registro);
        Correo = (TextView) findViewById(R.id.txt_correo_registro);
        Telefono = (TextView) findViewById(R.id.txt_telefono_registro);
        Contrasenia = (TextView) findViewById(R.id.pswd_contrasenia_registro);


        registrar = findViewById(R.id.btn_registrarse2);

        try {
            //Evento de componente
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String correoTexto = Correo.getText().toString().trim();
                    final String telefonoTexto = Telefono.getText().toString();
                    final String regexCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    final String regexTelefono = "^[+]?[0-9]{8,11}$";
                    if (!correoTexto.matches(regexCorreo) || !telefonoTexto.matches(regexTelefono)) {
                        Toast.makeText(RegistrarUsuarioActivity.this, "Ingrese un correo valido", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(RegistrarUsuarioActivity.this, MenuPrincipalActivity.class));
                        AgregarUsuario(v);
                        Toast.makeText(RegistrarUsuarioActivity.this, "Usuario registrado", Toast.LENGTH_LONG).show();
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
