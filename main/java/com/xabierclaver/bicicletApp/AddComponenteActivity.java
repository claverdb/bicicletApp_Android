package com.xabierclaver.bicicletApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.xabierclaver.bicicletApp.Modelos.Bicicleta;
import com.xabierclaver.bicicletApp.Modelos.Componente;

import org.w3c.dom.Text;

public class AddComponenteActivity extends AppCompatActivity {

    private Button btnEnviar;
    private ApplicationController ac;
    private int position;
    private EditText editTipo;
    private EditText editMarca;
    private EditText editPrecio;
    private EditText editLocalizacion;
    private EditText editCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_componente);

        ac = (ApplicationController) getApplicationContext();

        position = getIntent().getIntExtra("position", -1);

        btnEnviar = (Button) findViewById(R.id.btnEnviarComponente);

        if(position != -1){

            //Editar componente
            TextView titulo = (TextView) findViewById(R.id.textComp);
            titulo.setText("Editar componente");

            editTipo = (EditText) findViewById(R.id.txtTipo);
            editMarca = (EditText) findViewById(R.id.txtMarca);
            editPrecio = (EditText) findViewById(R.id.txtPrecioComponente);
            editLocalizacion = (EditText) findViewById(R.id.txtLocalizacion);
            editCantidad = (EditText) findViewById(R.id.txtCantidad);

            editTipo.setText(ac.componentes.get(position).getTipo());
            editMarca.setText(ac.componentes.get(position).getMarca());
            editPrecio.setText(Double.toString(ac.componentes.get(position).getPrecio()));
            editLocalizacion.setText(ac.componentes.get(position).getLocalizacion());
            editCantidad.setText(Integer.toString(ac.componentes.get(position).getCantidad()));

            btnEnviar.setText("EDITAR COMPONENTE");
        }

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarComponente(view, position);
            }
        });
    }

    //La posicion, es la posicion del componente en la lista
    //Si es -1, el componente es nuevo
    public void guardarComponente(View view, int position) {
        editTipo = (EditText) findViewById(R.id.txtTipo);
        editMarca = (EditText) findViewById(R.id.txtMarca);
        editPrecio = (EditText) findViewById(R.id.txtPrecioComponente);
        editLocalizacion = (EditText) findViewById(R.id.txtLocalizacion);
        editCantidad = (EditText) findViewById(R.id.txtCantidad);

        if (!editTipo.getText().toString().matches("") && !editMarca.getText().toString().matches("") && !editPrecio.getText().toString().matches("")){
            Componente componente;
            if (position == -1){
                componente = new Componente(editTipo.getText().toString(), editMarca.getText().toString(), editLocalizacion.getText().toString());
            } else {
                componente = ac.componentes.get(position);
                componente.setTipo(editTipo.getText().toString());
                componente.setMarca(editMarca.getText().toString());
                componente.setLocalizacion(editLocalizacion.getText().toString());
            }
            double precio = ac.tryParseDouble(editPrecio.getText().toString(), -1);
            if(precio >= 0){
                componente.setPrecio(precio);
            } else {
                setResult(-1);
                finish();
            }
            int cantidad = ac.tryParseInt(editCantidad.getText().toString(), -1);
            if(cantidad >= 0){
                componente.setCantidad(cantidad);
            } else {
                setResult(-1);
                finish();
            }
            if(position == -1) {
                ac.componentes.add(componente);
            }
            setResult(1);
            ac.actualizarDisponibilidad();
        } else {
            setResult(-1);
        }
        finish();
    }
}