package com.example.appincidencias;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ViewAnimator;


import org.w3c.dom.Text;


public class RegistrarUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button registrar;
    private TextView Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia;
    private Spinner SpinnerProvincia, SpinnerDistrito, SpinnerCantonSJ, SpinnerCantonHE, SpinnerCantonLI, SpinnerCantonGUA, SpinnerCantonPUN, SpinnerCantonCAR, SpinnerCantonALA;
    private ArrayAdapter<String> adapterProvincia, adapterDistrito, adapterCantonSJ, adapterCantonHE, adapterCantonLI, adapterCantonGUA, adapterCantonPUN,adapterCantonCAR, adapterCantonALA;

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
    String DistritosSanJose[]={"San Antonio","Granadilla","San Rafael Arriba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        //Extraer los datos
        Cedula = (TextView) findViewById(R.id.txt_cedula_registro);
        Nombre = (TextView) findViewById(R.id.txt_nombre_registro);
        PrimerApellido = (TextView) findViewById(R.id.txt_primer_apellido_registro);
        SegundoApellido = (TextView) findViewById(R.id.txt_segundo_apellido);
        SpinnerProvincia = (Spinner) findViewById(R.id.Provincias);
        SpinnerCantonSJ = (Spinner) findViewById(R.id.Canton);
        SpinnerCantonHE=(Spinner) findViewById(R.id.Canton);
        SpinnerCantonLI=(Spinner) findViewById(R.id.Canton);
        SpinnerCantonGUA=(Spinner) findViewById(R.id.Canton);
        SpinnerCantonPUN=(Spinner) findViewById(R.id.Canton);
        SpinnerCantonCAR=(Spinner) findViewById(R.id.Canton);
        SpinnerCantonALA=(Spinner) findViewById(R.id.Canton);
        SpinnerDistrito = (Spinner) findViewById(R.id.Distrito);
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


        registrar = findViewById(R.id.btn_registrarse2);

        try {
            //Evento de componente
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String correoTexto = Correo.getText().toString().trim();
                    final String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!correoTexto.matches(regex)) {
                        Toast.makeText(RegistrarUsuarioActivity.this, "Ingrese un correo valido", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(RegistrarUsuarioActivity.this, menuPrincipal.class));
                        agregar(v);
                        Toast.makeText(RegistrarUsuarioActivity.this, "Usuario registrado", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegistrarUsuarioActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
    }


        private void agregar(View v){
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
                    Toast.makeText(this, "Es necesario llenar todos los campos", Toast.LENGTH_LONG).show();
                }


            }catch (Exception ex){
                Toast.makeText(this, "Error al procesar en la Base de Datos"+ ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }


    @Override
    public void onItemSelected(AdapterView<?> adapter, View view, int position, long arg3) {
       //Toast.makeText(this,"Posición: "+position,Toast.LENGTH_LONG).show();
        switch (adapter.getId()){
            case R.id.Provincias:
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
            case R.id.Distrito:
                        break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
