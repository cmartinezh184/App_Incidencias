package com.example.appincidencias;

import android.location.Location;

public class Incidencia {

    private int idIncidencia, idUsuario, direccionId;
    private String descripcion;
    private Location ubicacion;

    public Incidencia(int idIncidencia, int idUsuario, int direccionId, String descripcion, Location ubicacion) {
        this.idIncidencia = idIncidencia;
        this.idUsuario = idUsuario;
        this.direccionId = direccionId;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

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

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Location getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Location ubicacion) {
        this.ubicacion = ubicacion;
    }
}
