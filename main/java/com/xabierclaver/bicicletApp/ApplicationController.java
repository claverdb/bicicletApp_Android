package com.xabierclaver.bicicletApp;

import android.app.Application;

import com.xabierclaver.bicicletApp.Modelos.BiciComponente;
import com.xabierclaver.bicicletApp.Modelos.Bicicleta;
import com.xabierclaver.bicicletApp.Modelos.Componente;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends Application{

    public List<Bicicleta> bicicletas = new ArrayList<>();
    public List<BiciComponente> biciComponentes = new ArrayList<>();
    public List<Componente> componentes = new ArrayList<>();
    public BicicletaAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarDatos();
    }

    //Para saber si una bici tiene materiales suficientes para estar disponible su construcción
    public String biciDisponible (Bicicleta bici){
        String noDisponibles = "Falta: ";
        for (BiciComponente bicicom:
                biciComponentes) {
            if(bicicom.getBicicleta().equals(bici)){
                if (bicicom.getComponente().getCantidad() < bicicom.getCantidadNecesaria()){
                    noDisponibles = noDisponibles + bicicom.getComponente().getTipo()+ ", ";
                }
            }
        }
        if(noDisponibles.equals("Falta: ")){
            return "Disponible";
        } else {
            return noDisponibles;
        }
    }

    //Actualiza la disponibilidad de fabricación de todas la bicis
    public void actualizarDisponibilidad(){
        for (Bicicleta bici: bicicletas) {
            String disponibilidad = biciDisponible(bici);
            bici.setDisponibilidad(disponibilidad);
        }
        adapter.notifyDataSetChanged();
    }

    //Lista de componentes de una bici dada
    public List<Componente> componentesDeUnaBici(Bicicleta bici){
        List<Componente> listaComponentes = new ArrayList<>();

        for (BiciComponente bicicom: biciComponentes) {
            if(!listaComponentes.contains(bicicom.getComponente()) && bicicom.getBicicleta().equals(bici)){
                listaComponentes.add(bicicom.getComponente());
            }
        }
        return listaComponentes;
    }

    //Lista para cantidades por componentes
    //A diferencia de componentesDeUnaBici permite saber la cantidad de componentes
    //que se necesita usar en la producción de la bici
    public List<BiciComponente> componentesDeUnaBiciCantidades(Bicicleta bici){
        List<BiciComponente> biciComponentes_p = new ArrayList<>();

        for (BiciComponente bicicom: biciComponentes) {
            if(bicicom.getBicicleta().equals(bici)){
                biciComponentes_p.add(bicicom);
            }
        }
        return biciComponentes_p;
    }

    //Intenta convertir un string a double
    public double tryParseDouble(String value, double defaultValue){
        try{
            return Double.parseDouble(value);
        }  catch (NumberFormatException e){
            return defaultValue;
        }
    }

    //Intenta convertir un string a integer
    public int tryParseInt(String value, int defaultValue){
        try{
            return Integer.parseInt(value);
        }  catch (NumberFormatException e){
            return defaultValue;
        }
    }

    //Inicializa los datosd de bicis y componentes
    private void inicializarDatos(){
        Bicicleta bici1 = new Bicicleta("BASIC", "Bicicleta económica con materiales básicos y de calidad aceptable", 150, 6);
        Bicicleta bici2 = new Bicicleta("PRO", "Bicicleta muy completa con las mejores calidades de montaña", 1020, 38);
        Bicicleta bici3 = new Bicicleta("PRO ROAD", "Bicicleta muy completa con las mejores calidades de carretera", 2160, 12);

        Componente comp1 = new Componente("Rueda", "ChinaTown", "Sector X, Piso 2, balda 1", 12, 34);
        Componente comp2 = new Componente("Rueda", "Massi", "Sector X, Piso 2, balda 2", 80, 10);
        Componente comp3 = new Componente("Rueda", "Roadx", "Sector X, Piso 2, balda 3", 120, 4);
        Componente comp11 = new Componente("Marco Hierro", "ChinaTown", "Sector X, Piso 1, balda 1", 80, 60);
        Componente comp22 = new Componente("Marco Aluminio", "Massi", "Sector X, Piso 1, balda 2", 300, 2);
        Componente comp33 = new Componente("Marco Carbono", "Roadx", "Sector X, Piso 1, balda 3", 600, 3);
        Componente comp111 = new Componente("Cambios", "ChinaTown", "Sector X, Piso 1, balda 4", 40, 59);
        Componente comp222 = new Componente("Cambios", "Mountainzone", "Sector X, Piso 1, balda 5", 200, 4);
        Componente comp333 = new Componente("Cambios", "Roadx", "Sector X, Piso 1, balda 6", 300, 8);
        Componente comp1111 = new Componente("Sillín", "ChinaTown", "Sector X, Piso 2, balda 8", 11, 22);
        Componente comp2222 = new Componente("Sillín", "Massi", "Sector X, Piso 2, balda 9", 49, 6);
        Componente comp3333 = new Componente("Sillín", "Massi", "Sector X, Piso 2, balda 10", 90, 3);
        Componente comp11111 = new Componente("Manillar", "ChinaTown", "Sector Y, Piso 0, balda 1", 28, 44);
        Componente comp22222 = new Componente("Manillar", "Massi", "Sector Y, Piso 0, balda 2", 80, 12);
        Componente comp33333 = new Componente("Manillar", "Massi", "Sector Y, Piso 0, balda 3", 99, 20);
        Componente pedales = new Componente("Pedal", "Massi", "Sector Y, Piso 0, balda 4", 49, 90);

        BiciComponente bicicomp1 = new BiciComponente(bici1, comp1, 2);
        BiciComponente bicicomp2 = new BiciComponente(bici1, comp11, 1);
        BiciComponente bicicomp3 = new BiciComponente(bici1, comp111, 1);
        BiciComponente bicicomp5 = new BiciComponente(bici1, comp1111, 1);
        BiciComponente bicicomp6 = new BiciComponente(bici1, comp11111, 1);
        BiciComponente bicicomp7 = new BiciComponente(bici1, pedales, 2);

        BiciComponente bicicomp11 = new BiciComponente(bici2, comp2, 2);
        BiciComponente bicicomp22 = new BiciComponente(bici2, comp22, 1);
        BiciComponente bicicomp33 = new BiciComponente(bici2, comp222, 1);
        BiciComponente bicicomp55 = new BiciComponente(bici2, comp2222, 1);
        BiciComponente bicicomp66 = new BiciComponente(bici2, comp22222, 1);
        BiciComponente bicicomp77 = new BiciComponente(bici2, pedales, 2);

        BiciComponente bicicomp111 = new BiciComponente(bici3, comp3, 2);
        BiciComponente bicicomp222 = new BiciComponente(bici3, comp33, 1);
        BiciComponente bicicomp333 = new BiciComponente(bici3, comp333, 1);
        BiciComponente bicicomp555 = new BiciComponente(bici3, comp3333, 1);
        BiciComponente bicicomp666 = new BiciComponente(bici3, comp33333, 1);
        BiciComponente bicicomp777 = new BiciComponente(bici3, pedales, 2);

        bicicletas.add(bici1);
        bicicletas.add(bici2);
        bicicletas.add(bici3);

        componentes.add(comp1);
        componentes.add(comp11);
        componentes.add(comp111);
        componentes.add(comp1111);
        componentes.add(comp11111);
        componentes.add(comp2);
        componentes.add(comp22);
        componentes.add(comp222);
        componentes.add(comp2222);
        componentes.add(comp22222);
        componentes.add(comp3);
        componentes.add(comp33);
        componentes.add(comp333);
        componentes.add(comp3333);
        componentes.add(comp33333);
        componentes.add(pedales);

        biciComponentes.add(bicicomp1);
        biciComponentes.add(bicicomp2);
        biciComponentes.add(bicicomp3);
        biciComponentes.add(bicicomp5);
        biciComponentes.add(bicicomp6);
        biciComponentes.add(bicicomp7);

        biciComponentes.add(bicicomp11);
        biciComponentes.add(bicicomp22);
        biciComponentes.add(bicicomp33);
        biciComponentes.add(bicicomp55);
        biciComponentes.add(bicicomp66);
        biciComponentes.add(bicicomp77);

        biciComponentes.add(bicicomp111);
        biciComponentes.add(bicicomp222);
        biciComponentes.add(bicicomp333);
        biciComponentes.add(bicicomp555);
        biciComponentes.add(bicicomp666);
        biciComponentes.add(bicicomp777);
    }
}
