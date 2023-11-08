package com.example.gymtracker.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gymtracker.R;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.record.Records;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Map;

import resources.Rutina;

public class SeleccionarRutinaModificar extends AppCompatActivity {
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_rutina_modificar);
        ListView lv = findViewById(R.id.lv_modificarRutina_seleccion);
        ArrayList<Rutina>rutinas = new ArrayList<>();
        ad = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,rutinas);
        lv.setAdapter(ad);
        llenar();
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
            case "Settings":
                test = new Intent(getApplicationContext(), Ajustes.class);
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
    private void llenar(){
        ArrayList<Rutina> rutinas = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Rutinas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Integer> ejercicios = (Map<String, Integer>) document.get("ejercicios");
                                Rutina r = new Rutina((String) document.get("nombre"), ejercicios);
                                Log.e("RUTINA", r.toString() );
                                Log.e("CONSULTA", "Referencia: "+document.getId()+" ejer"+document.get("ejercicio"));
                                r.setReferencia(document.getId());
                                ad.add(r);
                                Log.d("EEOOOO",  document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("BDD", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}