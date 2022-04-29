package com.xabierclaver.bicicletApp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private ApplicationController ac;

    //Se activará cuando se cierre una actividad secundaria
    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Comprobamos que la actividad se ha cerrado correctamente
                    if(result.getResultCode() == 1){
                        ac.adapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == 2){
                        ac.adapter.notifyDataSetChanged();
                        Snackbar.make(findViewById(R.id.mainActivity), "Componente añadiddo correctamente", Snackbar.LENGTH_LONG).show();
                    } else if (result.getResultCode() == 3){
                        ac.adapter.notifyDataSetChanged();
                        Snackbar.make(findViewById(R.id.mainActivity), "Bicicleta añadida a productos producidos correctamente", Snackbar.LENGTH_LONG).show();
                    } else if (result.getResultCode() == -2){
                        Snackbar.make(findViewById(R.id.mainActivity), "Ha ocurrido un error al añadir un componente a una bici", Snackbar.LENGTH_LONG).show();
                    } else if (result.getResultCode() == -1){
                        Snackbar.make(findViewById(R.id.mainActivity), "Ha ocurrido un error al añadir la bici", Snackbar.LENGTH_LONG).show();
                    } else if (result.getResultCode() == -3){
                        Snackbar.make(findViewById(R.id.mainActivity), "No se ha podido producir la bici por falta de materiales", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lista);
        ac = (ApplicationController)getApplicationContext();
        ac.adapter = new BicicletaAdapter(this, R.layout.row_layout, ac.bicicletas);
        listView.setAdapter(ac.adapter);

        //Actualizamos la disponibilidad para mostrarla
        ac.actualizarDisponibilidad();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                abrirBiciPopup(position);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBiciActivity.class);
                activityLauncher.launch(intent);
            }
        });


    }

    private void abrirBiciPopup(int position) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup_bici, null);

        TextView modeloBici = (TextView) popupView.findViewById(R.id.txtModeloBici);
        modeloBici.setText(ac.bicicletas.get(position).getModelo());
        TextView descripcionBici = (TextView) popupView.findViewById(R.id.txtDescripcionBici);
        descripcionBici.setText(ac.bicicletas.get(position).getDescripcion());
        TextView precioBici = (TextView) popupView.findViewById(R.id.lblPrecio);
        precioBici.setText("Precio: " + Double.toString(ac.bicicletas.get(position).getPrecio()) + " euros");
        TextView cantidadBici = (TextView) popupView.findViewById(R.id.txtCantidadBici);
        cantidadBici.setText(Integer.toString(ac.bicicletas.get(position).getCantidad()));
        TextView tiempoBici = (TextView) popupView.findViewById(R.id.lblTiempo);
        tiempoBici.setText("Tiempo de producción: " + Double.toString(ac.bicicletas.get(position).getTiempoproduccion()) + " horas");

        Button btnComponentes = (Button) popupView.findViewById(R.id.btnComponentesBici);
        Button btnProduccion = (Button) popupView.findViewById(R.id.btnProduccionBici);

        btnComponentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListadoComponentesBici.class);
                intent.putExtra("position", position);
                activityLauncher.launch(intent);
            }
        });

        btnProduccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProduccionActivity.class);
                intent.putExtra("position", position);
                dialog.dismiss();
                activityLauncher.launch(intent);
            }
        });

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.abrirListadoComp: {
                Intent intent = new Intent(ac, ListadoComponentes.class);
                activityLauncher.launch(intent);
                break;
            }
            case R.id.abrirAñadirCompBici: {
                Intent intent = new Intent(ac, AddComponenteBici.class);
                activityLauncher.launch(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}