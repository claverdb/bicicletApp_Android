package com.xabierclaver.bicicletApp;

import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xabierclaver.bicicletApp.Modelos.Bicicleta;

import java.util.List;

public class BicicletaAdapter extends ArrayAdapter<Bicicleta> {

    private Context mcontext;
    int mResource;

    /**
     * Default constructor for the BicicletaAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public BicicletaAdapter(@NonNull Context context, int resource, @NonNull List<Bicicleta> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String modelo = getItem(position).getModelo();
        String disponibilidad = getItem(position).getDisponibilidad();
        Bicicleta bici = new Bicicleta(modelo, disponibilidad);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtModelo = (TextView) convertView.findViewById(R.id.listaModelo);
        TextView txtDisponibilidad = (TextView) convertView.findViewById(R.id.listaDisponibilidad);

        txtDisponibilidad.setText(disponibilidad);
        txtModelo.setText(modelo);

        return convertView;
    }
}
