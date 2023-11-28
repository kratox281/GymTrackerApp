package com.example.gymtracker.entrenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.aboutme.AboutMe;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.record.Records;
import com.example.gymtracker.rutinas.OpcionesRutinas;
import com.example.gymtracker.rutinas.Rutinas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import resources.Rutina;

public class Entrenamiento extends AppCompatActivity {
    private int ejercicio =0;
 private   String actual ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);

        //Obtener Rutina
        Rutina rutina = (Rutina) getIntent().getSerializableExtra("rutina");
        Log.e("ENTRENAMIENTO", rutina.toString()+rutina.getEjercicios() );
       Map<String,Integer>ejercicios = rutina.getEjercicios();
        ArrayList<String> lista = new ArrayList<>(ejercicios.keySet());
        Object[]ejers =  lista.toArray();
         actual= (String) ejers[ejercicio];
        String siguiente = (String)ejers[ejercicio+1];
        TextView ejerActual = findViewById(R.id.tv_ejercicioActual);
        TextView siguienteEjercicio = findViewById(R.id.tv_siguienteEjercio);
        ejerActual.setText(actual);
        siguienteEjercicio.setText(siguiente);
        //Cambiar entre ejercicios
        findViewById(R.id.bt_siguienteSerie).setEnabled(true);
        findViewById(R.id.bt_siguienteSerie).setOnClickListener(v -> {
            TextView contador =findViewById(R.id.tv_contadorRepes);
            contador.getText();
            Integer serie = Integer.parseInt(contador.getText().toString());
            serie++;

            if (serie>=5){
                    contador.setText("1");
                    ejercicio++;
                Toast.makeText(this, "Siguiente Ejercicio", Toast.LENGTH_SHORT).show();
                if((ejercicio)==ejers.length-1){
                    actual = (String) ejers[ejercicio];
                    ejerActual.setText(actual);
                    siguienteEjercicio.setText("");
                }else if((ejercicio)==ejers.length){
                    ejercicio=0;
                    actual = (String) ejers[ejercicio];
                    ejerActual.setText("Se acabo el entrenamiento");
                    siguienteEjercicio.setText("");
                    findViewById(R.id.bt_siguienteSerie).setEnabled(false);
                }else{
                    actual = (String) ejers[ejercicio];
                    ejerActual.setText(actual.toString());
                    siguienteEjercicio.setText((String)ejers[ejercicio+1]);
               }
            }else {
                String nuevoTexto = ""+serie;
                contador.setText(nuevoTexto);
            }
        });
    }


    //COPIAR ESTO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entrenamiento, menu);
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
    //HASTA AQUI
}