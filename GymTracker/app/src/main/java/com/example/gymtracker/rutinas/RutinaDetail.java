package com.example.gymtracker.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gymtracker.R;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.entrenamiento.ElegirEntrenamiento;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.record.Records;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import resources.Ejercicio;
import resources.Rutina;
import resources.RutinaArrayAdapter;

public class RutinaDetail extends AppCompatActivity {
    private RutinaArrayAdapter adr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_detail);
        Rutina rutina = (Rutina) getIntent().getSerializableExtra("rutina");
        TextView tv = findViewById(R.id.tv_nombreDetail);
        ListView lv = findViewById(R.id.lv_rutinaDetail);
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        tv.setText(rutina.getNombre());
        adr = new RutinaArrayAdapter(getApplicationContext(),R.layout.listviewejercicios,ejercicios);
        lv.setAdapter(adr);
        llenar(rutina);

    }
    //COPIAR ESTO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rutinas, menu);
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
            case "Ajustes":
                test = new Intent(getApplicationContext(),Ajustes.class);
                startActivity(test);
                //finish();
                break;
            case "Entrenamiento":
                test = new Intent(getApplicationContext(), ElegirEntrenamiento.class);
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

    private void llenar(Rutina rutina){
        Map<String,Integer>mapa = rutina.getEjercicios();
        Set<String>ejers = mapa.keySet();
        for (String s:ejers){
            Ejercicio e = new Ejercicio(s,mapa.get(s)+"");
            adr.add(e);
        }
    }
}