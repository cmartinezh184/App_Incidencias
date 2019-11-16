package com.example.appincidencias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;




public class DBHelper extends SQLiteOpenHelper {

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Persona (Cedula INT PRIMARY KEY, Nombre TEXT," +
                " PrimerApellido TEXT, SegundoApellido TEXT, DireccionID INT, Correo TEXT, " +
                "Telefono INT, Contrasenia TEXT)");
        db.execSQL("CREATE TABLE Direccion (DireccionID INT, Anotaciones TEXT, DistritoID INT)");
        db.execSQL("CREATE TABLE Distrito (DistritoID INT, Nombre TEXT, CantonID INT)");
        db.execSQL("CREATE TABLE Canton (CantonID INT, Nombre TEXT, ProvinciaID INT)");
        db.execSQL("CREATE TABLE Provincia (ProvinciaID INT, Nombre TEXT)");


        //Insertar Datos en Provincia
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (1, SanJose)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (2, Heredia)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (3, Cartago)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (4, Limón)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (5, Puntarenas)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (6, Guanacaste)");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (7, Alajuela)");

        //Insertar Datos en Canton
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (1, Escazú, 1)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (2, Curridabat, 1)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (3, SantoDomingo, 2)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (4, Belén, 2)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (5, Cartago, 3)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (6, LaUnión, 3)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (7, Pococí, 4)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (4, Matina, 4)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (8, MontesDeOro,5)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (9, Osa,5)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (10, Liberia,6)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (11, Abangares,6)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (12, Grecia, 7)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (13, Alajuela, 7)");

        //Insertar Datos en Distrito
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID ) VALUES (1, SanAntonio, 1)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (2, Sanchez, 2)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (3, SanMiguel, 3)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (4, SanAntonio, 4)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (5, Ochomogo, 5)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (6, TresRios, 6)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (7, Guápiles, 7)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (8, Corina, 8)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (9, Miramar,9)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (10, Palmar,10)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (11, CañaDulce,11)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (12, LasJuntas,12)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (13, Guacima, 13)");


        //Insertar Datos en Direccion

        //Insertar Datos en Persona


    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
