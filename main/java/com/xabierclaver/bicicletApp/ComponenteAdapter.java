package com.xabierclaver.bicicletApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xabierclaver.bicicletApp.Modelos.Bicicleta;
import com.xabierclaver.bicicletApp.Modelos.Componente;

import java.util.List;

public class ComponenteAdapter extends ArrayAdapter<Componente> {

    private Context mcontext;
    int mResource;

    /**
     * Default constructor for the ComponenteAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ComponenteAdapter(@NonNull Context context, int resource, @NonNull List<Componente> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String tipo = getItem(position).getTipo();
        String marca = getItem(position).getMarca();
        int cantidad = getItem(position).getCantidad();
        Componente componente = new Componente(tipo, marca, cantidad);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtTipo = (TextView) convertView.findViewById(R.id.listaTipo);
        TextView txtMarca = (TextView) convertView.findViewById(R.id.listaMarca);
        TextView txtCant = (TextView) convertView.findViewById(R.id.txtCantActual);

        txtTipo.setText(tipo);
        txtMarca.setText(marca);
        txtCant.setText("Cantidad: " + cantidad);


        return convertView;
    }
}
