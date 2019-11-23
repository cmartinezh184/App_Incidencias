package com.example.appincidencias;

public class Persona {

    private int Cedula;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private int DireccionID;
    private String Correo;
    private int Telefono;
    private String Contrasenia;

    public Persona(int cedula, String nombre, String primerApellido, String segundoApellido, int direccionID, String correo, int telefono, String contrasenia) {
        Cedula = cedula;
        Nombre = nombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        DireccionID = direccionID;
        Correo = correo;
        Telefono = telefono;
        Contrasenia = contrasenia;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        PrimerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public int getDireccionID() {
        return DireccionID;
    }

    public void setDireccionID(int direccionID) {
        DireccionID = direccionID;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }
}
