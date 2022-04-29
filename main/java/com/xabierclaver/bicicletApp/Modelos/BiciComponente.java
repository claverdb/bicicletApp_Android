package com.xabierclaver.bicicletApp.Modelos;

public class BiciComponente {

    private Bicicleta bicicleta;
    private Componente componente;
    private int cantidadNecesaria;


    public BiciComponente(Bicicleta bicicleta, Componente componente, int cantidadNecesaria) {
        this.bicicleta = bicicleta;
        this.componente = componente;
        this.cantidadNecesaria = cantidadNecesaria;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public Componente getComponente() {
        return componente;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    @Override
    public String toString() {
        return componente.getTipo() + " " + componente.getMarca() + ", Cantidad: " + cantidadNecesaria;
    }
}
