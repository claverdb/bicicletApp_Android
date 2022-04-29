package com.xabierclaver.bicicletApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xabierclaver.bicicletApp.Modelos.Componente;

import java.util.List;

public class ComponenteBiciAdapter extends ArrayAdapter<Componente> {
    private Context mcontext;
    int mResource;

    /**
     * Default constructor for the ComponenteBiciAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ComponenteBiciAdapter(@NonNull Context context, int resource, @NonNull List<Componente> objects) {
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
        double precio = getItem(position).getPrecio();
        String localizacion = getItem(position).getLocalizacion();
        Componente componente = new Componente(tipo, marca, localizacion, precio, cantidad);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtTipo = (TextView) convertView.findViewById(R.id.txtTipo_cb);
        TextView txtMarca = (TextView) convertView.findViewById(R.id.txtMarca_cb);
        TextView txtCant = (TextView) convertView.findViewById(R.id.txtCantActual_cb);
        TextView txtLocalizacion = (TextView) convertView.findViewById(R.id.txtLocalizacion_cb);
        TextView txtPrecio = (TextView) convertView.findViewById(R.id.txtPrecio_cb);

        txtTipo.setText(tipo);
        txtMarca.setText(marca);
        txtCant.setText("Cantidad: " + Integer.toString(cantidad));
        txtPrecio.setText(Double.toString(precio) + " euros");
        txtLocalizacion.setText(localizacion);

        return convertView;
    }
}
