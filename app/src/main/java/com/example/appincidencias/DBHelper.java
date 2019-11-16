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
        db.execSQL("CREATE TABLE Incidencia (IncidenciaID INT, UsuarioID INT, Descripcion TEXT, Ubicacion TEXT)");


        //Insertar Datos en Provincia
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (1, 'San Jose')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (2, 'Heredia')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (3, 'Cartago')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (4, 'Limón')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (5, 'Puntarenas')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (6, 'Guanacaste')");
        db.execSQL("INSERT INTO Provincia (ProvinciaID, Nombre) VALUES (7, 'Alajuela')");

        //Insertar Datos en Canton
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (1, 'Escazú', 1)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (2, 'Curridabat', 1)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (3, 'Santo Domingo',2)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (4, 'Belén', 2)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (5, 'Cartago', 3)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (6, 'La Unión', 3)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (7, 'Pococí', 4)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (4, 'Matina', 4)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (8, 'Montes De Oro',5)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (9, 'Osa',5)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (10, 'Liberia',6)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (11, 'Abangares',6)");

        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (12, 'Grecia', 7)");
        db.execSQL("INSERT INTO Canton (CantonID, Nombre, ProvinciaID) VALUES (13, 'Alajuela', 7)");

        //Insertar Datos en Distrito
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID ) VALUES (1, 'San Antonio', 1)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (2, 'Sanchez', 2)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (3, 'San Miguel', 3)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (4, 'SanA ntonio', 4)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (5, 'Ochomogo', 5)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (6, 'Tres Rios', 6)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (7, 'Guápiles', 7)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (8, 'Corina', 8)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (9, 'Miramar',9)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (10, 'Palmar',10)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (11, 'Caña Dulce',11)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (12, 'Las Juntas',12)");
        db.execSQL("INSERT INTO Distrito (DistritoID , Nombre, CantonID) VALUES (13, 'Guacima', 13)");


        //Insertar Datos en Direccion
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (1, 'San Antonio de Escazú', 1)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (2, 'Sanchez de Curridabat', 2)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (3, 'San Miguel de Santo Domingo', 3)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (4, 'San Antonio de Belén', 4)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (5, 'Ochomogo de Cartago', 5)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (6, 'Tres Ríos de La Unión',6)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (7, 'Guápiles de Pococí', 7)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (8, 'Corina de Matina', 8)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (9, 'Miramar de Montes de Oro', 9)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (10, 'Palmar de Osa', 10)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (11, 'Caña Dulce de Liberia', 11)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (12, 'Las Juntas de Abangares', 12)");
        db.execSQL("INSERT INTO Direccion (DireccionID , Anotaciones, DistritoID ) VALUES (13, 'Guácima de Grecia', 13)");



        //Insertar Datos en Persona

        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo, Telefono, Contrasenia ) " +
                "VALUES (117410991, 'Katherine','Martínez','Chacón', 'San Antonio de Escazú', 'kathemart0427@gmail.com', 888888888, 'Katherinemc')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (114410990, 'Kevin','Martínez','Chacón', 'Sanchez de Curridabat', 'kevin@gmail.com', 888888888,  'Kevinmc')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (112410992, 'Cristhian','Martínez','Jimenez', 'San Miguel de Santo Domingo', 'cristhian@gmail.com',888888888, 'Cristhianmj')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (112410993, 'Jose','Solorzano','Murillo', 'Ochomogo de Cartago', 'josesolorzano@gmail.com', 888888888,'Josesm')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (113410994, 'Mariana','Leiva','Carvajal', 'Tres Ríos de La Unión', 'marianac@gmail.com', 888888888,'Marianalc')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (115410995, 'Diego','Ramirez','Rojas',  'Guápiles de Pococí', 'diegoramirez@gmail.com', 888888888,'Diegorr')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (116410996, 'Luis','Solano','Romero','Corina de Matina', 'luisol@gmail.com', 888888888,'Luissr')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (119410997, 'Angie','Gomez','Aguilar', 'Miramar de Montes de Oro', 'angiegg@gmail.com', 888888888,'Angiega')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (114410998, 'Valeria','Cortes','Chacón', 'Palmar de Osa', 'valeriach@gmail.com', 888888888,'Valeriacc')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (115410999, 'Jorge','Soto','Rojas', 'Caña Dulce de Liberia', 'jorgesot@gmail.com', 888888888,'Jorgesr')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (116410981, 'Laura','Jimenez','Sanchez', 'Las Juntas de Abangares', 'laurajims@gmail.com', 888888888,'Laurajs')");
        db.execSQL("INSERT INTO Persona (Cedula , Nombre, PrimerApellido, SegundoApellido, DireccionID, Correo,Telefono, Contrasenia ) " +
                "VALUES (114410971, 'Daniel','Marchena','Cespedes', 'Guácima de Grecia', 'danielmarch@gmail.com', 888888888,'Danielmc')");



        //Insertar Datos en Incidencias
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (1,1,'Hueco en calle','Escazú, Santa Teresa')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (2,2,'Innundacion en calle','San Jose, Dos Cercas')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (3,3,'Choque en calle','Limon, Cocori')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (4,4,' Puente caido','Cartago, Tres Rios')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (5,5,'Hueco en calle','San Jose, Curridabat')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (6,6,'Hueco en calle','Escazu, Santa Ana')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (7,7,'Vuelco en calle','San Jose, Zapote')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (8,8,'Cables caidos en calle','Perez Zeledon, San Jose')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (9,9,'Presa en calle','San Jose, San Jose')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (10,10,'Innundacion en calle','Guanacaste, Liberia')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (11,11,'Vuelco en calle','San Jose, Calle Blancos')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (12,12,'Hueco en calle','Alajuela, Alajuela')");
        db.execSQL("INSERT INTO Incidencia (IncidenciaID , UsuarioID, Descripcion, Ubicacion) VALUES (13,13,'Atropello','Desamparados, Gravilias')");


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
