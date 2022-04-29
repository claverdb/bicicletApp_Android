package com.xabierclaver.bicicletApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.xabierclaver.bicicletApp.Modelos.BiciComponente;
import com.xabierclaver.bicicletApp.Modelos.Bicicleta;
import com.xabierclaver.bicicletApp.Modelos.Componente;

import java.util.ArrayList;
import java.util.List;

public class ProduccionActivity extends AppCompatActivity {

    private List<BiciComponente> bicicomponentes = new ArrayList<>();
    private Spinner spComponentes;
    private ApplicationController ac;
    private ArrayAdapter<BiciComponente> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produccion);

        ListView listView = (ListView) findViewById(R.id.lista_produccion);
        ac = (ApplicationController) getApplicationContext();
        Button btnAnadirEliminar = (Button) findViewById(R.id.btnAnadirEliminar);
        Button btnProducir = (Button) findViewById(R.id.btnProducir);

        int position = getIntent().getIntExtra("position", -1);
        bicicomponentes = ac.componentesDeUnaBiciCantidades(ac.bicicletas.get(position));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bicicomponentes);
        listView.setAdapter(adapter);

        ArrayAdapter<Componente> spAdapater = new ArrayAdapter<Componente>(this, android.R.layout.simple_spinner_item, ac.componentes);
        spAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spComponentes = (Spinner) findViewById(R.id.spAnadirEliminar);

        spComponentes.setAdapter(spAdapater);

        btnAnadirEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirEliminar(ac.bicicletas.get(position));
            }
        });

        btnProducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producirBicicleta(ac.bicicletas.get(position));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int pos_spComponentes = ac.componentes.indexOf(bicicomponentes.get(position).getComponente());
                spComponentes.setSelection(pos_spComponentes);
            }
        });

    }

    private void producirBicicleta(Bicicleta bici) {
        int resultcode = 3;

        //Primero comprobamos las cantidades
        for (BiciComponente bcomponente: bicicomponentes) {
            if (bcomponente.getComponente().getCantidad() < bcomponente.getCantidadNecesaria()) {
                resultcode = -3;
                break;
            }
        }

        if(resultcode != -3){
            for (BiciComponente bcomponente: bicicomponentes) {
                bcomponente.getComponente().setCantidad(bcomponente.getComponente().getCantidad() - bcomponente.getCantidadNecesaria());
            }
            bici.setCantidad(bici.getCantidad() + 1);
        }

        ac.actualizarDisponibilidad();
        setResult(resultcode);
        finish();
    }

    private void anadirEliminar(Bicicleta bici) {

        Componente componenteelegido = null;
        componenteelegido = (Componente) spComponentes.getSelectedItem();
        if (componenteelegido != null){

            boolean contenido = false;
            int index = -1;
            for (BiciComponente bcomponente: bicicomponentes) { //Buscar componente
                if(bcomponente.getComponente().equals(componenteelegido)){
                    contenido = true;
                    index = bicicomponentes.indexOf(bcomponente);
                }
            }

            if(contenido && index != -1){ //Remove
                bicicomponentes.remove(bicicomponentes.get(index));
            } else { //Add
                EditText edCantidad = (EditText) findViewById(R.id.edCantidadNuevo);
                int cantidad = ac.tryParseInt(edCantidad.getText().toString(), -1);
                if(cantidad != -1 && cantidad <= componenteelegido.getCantidad()){
                    bicicomponentes.add(new BiciComponente(bici, componenteelegido, cantidad));
                    edCantidad.setText("1");
                    Snackbar.make(findViewById(R.id.activity_produccion), "Nuevo componente añadido", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(findViewById(R.id.activity_produccion), "Ha ocurrido un error con la cantidades y no se ha añadido", Snackbar.LENGTH_LONG).show();
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}