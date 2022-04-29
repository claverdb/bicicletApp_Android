package com.xabierclaver.bicicletApp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ListadoComponentes extends AppCompatActivity {

    private ListView listView;
    private ComponenteAdapter adapter;
    private ApplicationController ac;

    //Se activará cuando se cierre una actividad secundaria
    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Comprobamos que la actividad se ha cerrado correctamente
                    if(result.getResultCode() == 1){
                        adapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == -1){
                        Snackbar.make(findViewById(R.id.listadoComponentes), "Ha ocurrido un error al añadir el componente", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_componentes);

        listView = (ListView) findViewById(R.id.lista_componentes);
        ac = (ApplicationController)getApplicationContext();
        adapter = new ComponenteAdapter(this, R.layout.row_layout_componente, ac.componentes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddComponenteActivity.class);
                intent.putExtra("position", position);
                activityLauncher.launch(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_componentes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddComponenteActivity.class);
                activityLauncher.launch(intent);
            }
        });
    }
}