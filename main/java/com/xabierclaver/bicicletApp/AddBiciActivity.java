package com.xabierclaver.bicicletApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.xabierclaver.bicicletApp.Modelos.Bicicleta;

public class AddBiciActivity extends AppCompatActivity {

    Button btnEnviar;
    ApplicationController ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bici);

        ac = (ApplicationController) getApplicationContext();

        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarBici(view);
            }
        });

    }

    public void guardarBici(View view) {
        int resultcode = 0;
        EditText editModelo = (EditText) findViewById(R.id.txtModelo);
        EditText editDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        EditText editPrecio = (EditText) findViewById(R.id.txtPrecio);
        EditText editTiempo = (EditText) findViewById(R.id.txtTiempo);

        if (!editModelo.getText().toString().matches("") && !editDescripcion.getText().toString().matches("") && !editPrecio.getText().toString().matches("") && !editTiempo.getText().toString().matches("")){
            Bicicleta bici = new Bicicleta(editModelo.getText().toString(), editDescripcion.getText().toString());
            double precio = ac.tryParseDouble(editPrecio.getText().toString(), -1);
            double tiempo = ac.tryParseDouble(editTiempo.getText().toString(), -1);
            if(precio >= 0){
                bici.setPrecio(precio);
            } else {
                resultcode = -1;
            }
            if(tiempo >= 0){
                bici.setTiempoproduccion(tiempo);
            } else {
                resultcode = -1;
            }
            bici.setDisponibilidad(ac.biciDisponible(bici));
            ac.bicicletas.add(bici);
            resultcode = 1;
        } else {
            resultcode = -1;
        }
        setResult(resultcode);
        finish();
    }
}