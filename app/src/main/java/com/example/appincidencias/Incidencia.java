package com.example.appincidencias;

import android.location.Location;

public class Incidencia {

    private int idIncidencia, idUsuario;
    private String descripcion;
    private String latitud;
    private String longitud;

    public Incidencia(int idIncidencia, int idUsuario, String descripcion, String latitud, String longitud) {
        this.idIncidencia = idIncidencia;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Incidencia(){}

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
