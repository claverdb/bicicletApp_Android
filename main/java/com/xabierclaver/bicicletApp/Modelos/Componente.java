package com.xabierclaver.bicicletApp.Modelos;

public class Componente {
    private String tipo;
    private String marca;
    private String localizacion;
    private double precio;
    private int cantidad;

    public Componente(String tipo, String marca, int cantidad) {
        this.tipo = tipo;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public Componente(String tipo, String marca, double precio) {
        this.tipo = tipo;
        this.marca = marca;
        this.precio = precio;
        cantidad = 0;
    }

    public Componente(String tipo, String marca, String localizacion) {
        this.tipo = tipo;
        this.marca = marca;
        this.localizacion = localizacion;
        cantidad = 0;
    }

    public Componente(String tipo, String marca, String localizacion, double precio, int cantidad) {
        this.tipo = tipo;
        this.marca = marca;
        this.localizacion = localizacion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return tipo + " " + marca;
    }
}
