package com.xabierclaver.bicicletApp.Modelos;

public class Bicicleta {
     private String modelo;
     private String descripcion;
     private double precio;
     private int cantidad;
     private double tiempoproduccion;
     private String disponibilidad;


    public Bicicleta(String modelo, String descripcion, double precio, double tiempoproduccion) {
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoproduccion = tiempoproduccion;
        cantidad = 0;

    }

    public Bicicleta(String modelo, String descripcion) {
        this.modelo = modelo;
        this.descripcion = descripcion;
        cantidad = 0;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTiempoproduccion() {
        return tiempoproduccion;
    }

    public void setTiempoproduccion(double tiempoproduccion) {
        this.tiempoproduccion = tiempoproduccion;
    }

    @Override
    public String toString() {
        return modelo;
    }
}
