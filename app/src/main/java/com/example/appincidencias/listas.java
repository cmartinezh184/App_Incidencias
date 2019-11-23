import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class listas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            // Insert con execSQL
            db.execSQL("INSERT INTO comments (Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia) VALUES ('Digital Learning','Esto es un comentario insertado usando el método execSQL()')");

            // Insert con ContentValues
            ContentValues cv = new ContentValues();
            cv.put("user", "Academia Android");
            cv.put("comment", "Esto es un comentario insertado usando el método insert()");
            db.insert("comments", null, cv);

            // Update con execSQL
            db.execSQL("UPDATE comments SET comment='Esto es un comentario actualizado por el método execSQL()' WHERE user='Digital Learning'");

            // Update con ContentValues
            cv = new ContentValues();
            cv.put("comment", "Esto es un comentario actualizado por el método update()");
            String[] args = new String []{ "Academia Android"};
            db.update("comments", cv, "user=?", args);

            //Consultamos los datos
            Cursor c = db.rawQuery("SELECT Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia FROM DBHelper", null);

            if (c != null) {
                c.moveToFirst();
                //Do-while es de tipo pos prueba que  primero realiza las acciones luego pregunta.
                do {
                    //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    String Cedula = c.getString(c.getColumnIndex("Cedula"));
                    String Nombre = c.getString(c.getColumnIndex("Nombre"));
                    String PrimerApellido = c.getString(c.getColumnIndex("PrimerApellido"));
                    String SegundoApellido = c.getString(c.getColumnIndex("SegundoApellido"));
                    String DireccionID = c.getString(c.getColumnIndex("DireccionID"));
                    String Correo = c.getString(c.getColumnIndex("Correo"));
                    String Telefono = c.getString(c.getColumnIndex("Telefono"));
                    String Contrasenia= c.getString(c.getColumnIndex("Contrasenia"));
                } while (c.moveToNext());
            }

            //Cerramos el cursor y la conexion con la base de datos
            c.close();
            db.close();

        }

    }
}
