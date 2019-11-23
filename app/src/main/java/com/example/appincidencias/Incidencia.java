package com.example.appincidencias;

import android.location.Location;

public class Incidencia {

    private int idIncidencia, idUsuario;
    private String descripcion;
    private String ubicacion;

    public Incidencia(int idIncidencia, int idUsuario, int direccionId, String descripcion, String ubicacion) {
        this.idIncidencia = idIncidencia;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
