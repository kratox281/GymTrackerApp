package com.example.gymtracker.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.gymtracker.R;
import com.example.gymtracker.aboutme.AboutMe;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.entrenamiento.ElegirEntrenamiento;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.record.Records;

public class OpcionesRutinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_rutinas);
        Button crear = findViewById(R.id.bt_crearRutina);
        Button ver = findViewById(R.id.bt_verRutinas);
        Button modificar = findViewById(R.id.bt_modificarRutina);

        crear.setOnClickListener(v -> {
            Intent cambio = new Intent(getApplicationContext(),CrearRutinas.class);
            startActivity(cambio);

                }
        );
        ver.setOnClickListener(v -> {
                    Intent cambio = new Intent(getApplicationContext(),Rutinas.class);
                    startActivity(cambio);

                }
        );
        modificar.setOnClickListener(v -> {
                    Intent cambio = new Intent(getApplicationContext(),SeleccionarRutinaModificar.class);
                    startActivity(cambio);

                }
        );
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
                //finish();
                break;
            case "Ajustes":
                test = new Intent(getApplicationContext(),Ajustes.class);
                startActivity(test);
                //finish();
                break;
            case "Entrenamiento":
                test = new Intent(getApplicationContext(), ElegirEntrenamiento.class);
                startActivity(test);
                //finish();
                break;
            case "Rutinas":
                test = new Intent(getApplicationContext(), OpcionesRutinas.class);
                startActivity(test);
                finish();
                break;
            default:
                test = new Intent(getApplicationContext(), AboutMe.class);
                startActivity(test);
                //finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//HASTA AQUI
}