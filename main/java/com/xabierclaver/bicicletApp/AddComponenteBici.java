package com.xabierclaver.bicicletApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.xabierclaver.bicicletApp.Modelos.BiciComponente;
import com.xabierclaver.bicicletApp.Modelos.Bicicleta;
import com.xabierclaver.bicicletApp.Modelos.Componente;

import org.w3c.dom.Text;

public class AddComponenteBici extends AppCompatActivity {

    private ApplicationController ac;
    private Spinner spBicicletas;
    private Spinner spComponentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_componente_bici);

        ac = (ApplicationController) getApplicationContext();

        ArrayAdapter<Bicicleta> biciAdapter = new ArrayAdapter<Bicicleta>(this, android.R.layout.simple_spinner_item, ac.bicicletas);
        ArrayAdapter<Componente> compAdapter = new ArrayAdapter<Componente>(this, android.R.layout.simple_spinner_item, ac.componentes);

        // Drop down list
        biciAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        compAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spBicicletas = (Spinner) findViewById(R.id.spBicicletas);
        spComponentes = (Spinner) findViewById(R.id.spComponentes);

        // attaching data adapter to spinner
        spBicicletas.setAdapter(biciAdapter);
        spComponentes.setAdapter(compAdapter);


        Button btnJuntar;
        btnJuntar = (Button) findViewById(R.id.btnJuntar);

        btnJuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarEnviar(view);
            }
        });
    }

    private void comprobarEnviar(View view) {
        int resultnum = -2;
        spBicicletas = (Spinner) findViewById(R.id.spBicicletas);
        spComponentes = (Spinner) findViewById(R.id.spComponentes);
        Bicicleta bicielegida = null;
        Componente componenteelegido = null;
        try{
            bicielegida = (Bicicleta) spBicicletas.getSelectedItem();
            componenteelegido = (Componente) spComponentes.getSelectedItem();
            if (bicielegida != null && componenteelegido != null){
                resultnum = 2;
            }
        } catch (NullPointerException e){
            resultnum = -2;
        }

        TextView txcantidad = (TextView) findViewById(R.id.numspcomp);
        int cantidad = ac.tryParseInt(txcantidad.getText().toString(), -2);

        if(cantidad == -2){
            resultnum = -2;
        }

        for (BiciComponente biciComponente: ac.biciComponentes) {
            if(biciComponente.getComponente().equals(componenteelegido) && biciComponente.getBicicleta().equals(bicielegida)){
                resultnum = -2;
                break;
            }
        }

        if(resultnum == 2){
            BiciComponente biciComp = new BiciComponente(bicielegida, componenteelegido, cantidad);
            ac.biciComponentes.add(biciComp);
            ac.actualizarDisponibilidad();
        }

        setResult(resultnum);
        finish();
    }
}