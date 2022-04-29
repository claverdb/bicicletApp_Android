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

public class ListadoComponentesBici extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_componentes_bici);

        ListView listView = (ListView) findViewById(R.id.listado_componentes_bici);
        ApplicationController ac = (ApplicationController) getApplicationContext();

        int position = getIntent().getIntExtra("position", -1);

        ComponenteBiciAdapter adapter = new ComponenteBiciAdapter(this, R.layout.row_layout_componente_bicicleta, ac.componentesDeUnaBici(ac.bicicletas.get(position)));
        listView.setAdapter(adapter);

    }
}