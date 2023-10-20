package com.example.gymtracker.record;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymtracker.R;
import com.example.gymtracker.aboutme.AboutMe;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.databinding.ActivityRecordsBinding;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.rutinas.*;

import androidx.appcompat.app.AppCompatActivity;

import android.view.*;
import android.widget.*;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.*;

import resources.*;

public class Records extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityRecordsBinding binding;


    private ListView listarecords;
    private ArrayList<Record> records;
    private RecordArrayAdapter adr;

    private Button btRegistrar;
    private EditText etNombreEjer;
    private EditText etPesoEjer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_records);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //Asociacion elementos de clase con elementos de la interfaz
        listarecords = findViewById(R.id.lv_listaRecords);
        btRegistrar = findViewById(R.id.btRegistrarRecord);
        etNombreEjer = findViewById(R.id.et_nombreEjercicioRecord);
        etPesoEjer = findViewById(R.id.et_pesoEjercicioRecord);
        records =new ArrayList<>();
        adr = new RecordArrayAdapter(getApplicationContext(),R.layout.listviewrecords,records);
        listarecords.setAdapter(adr);

        btRegistrar.setOnClickListener(view -> {
            adr.add(new Record(etNombreEjer.getText().toString(),Double.parseDouble( etPesoEjer.getText().toString())));
            Toast.makeText(this, "Record Registrado", Toast.LENGTH_SHORT).show();
        });
        listarecords.setOnItemClickListener((parent, view, position, id) -> {
            Record rec = adr.getItem(position);
            Toast.makeText(this, "Se ha eliminado el record de "+rec.getEjercicio(), Toast.LENGTH_SHORT).show();
            adr.remove(adr.getItem(position));        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_records);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //COPIAR ESTO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }


    //Redireccionar a la actividad necesaria desde el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent test;
        switch (item.getTitle().toString()){
            case "Records":
                test= new Intent(getApplicationContext(), Records.class);
                startActivity(test);
                finish();
                break;
            case "Settings":
                test = new Intent(getApplicationContext(),Ajustes.class);
                startActivity(test);
                finish();
                break;
            case "Entrenamiento":
                test = new Intent(getApplicationContext(), Entrenamiento.class);
                startActivity(test);
                finish();
                break;
            case "Rutinas":
                test = new Intent(getApplicationContext(), OpcionesRutinas.class);
                startActivity(test);
                finish();
                break;
            default:
                test = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(test);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //HASTA AQUI
}