package com.example.gymtracker.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.entrenamiento.ElegirEntrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.record.Records;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import resources.Ejercicio;
import resources.Rutina;
import resources.RutinaArrayAdapter;

public class ModificarRutinas extends AppCompatActivity {
    private RutinaArrayAdapter adr;
    private Rutina rutina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_rutinas);
        rutina = (Rutina) getIntent().getSerializableExtra("rutina");
        TextView tv = findViewById(R.id.textView12);
        ListView lv = findViewById(R.id.lv_rutina_modificar);
        Button guardarEjer = findViewById(R.id.bt_meterModificarutina);
        Button guardarRutina = findViewById(R.id.bt_GuardarModificacion);
        TextView nombreEjer = findViewById(R.id.editTextTextPersonName2);
        TextView numeroRepes = findViewById(R.id.editTextTextPersonName3);
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        tv.setText(rutina.getNombre());
        adr = new RutinaArrayAdapter(getApplicationContext(),R.layout.listviewejercicios,ejercicios);
        lv.setAdapter(adr);
        llenar(rutina);
        lv.setOnItemClickListener((parent, view, position, id) -> {

            adr.remove(adr.getItem(position));
        });
        guardarEjer.setOnClickListener(v->{
            adr.add(new Ejercicio(nombreEjer.getText().toString(),numeroRepes.getText().toString()));
            Toast.makeText(this, "Ejercicio Registrado", Toast.LENGTH_SHORT).show();
        });
        guardarRutina.setOnClickListener(v->{

            Map<String,Integer>lista = new HashMap<>();
            for(Ejercicio e:ejercicios){
                lista.put(e.getNombre(),Integer.parseInt(e.getRepeticiones()));
            }


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Toast.makeText(this, rutina.getReferencia(), Toast.LENGTH_SHORT).show();
            db.collection("Rutinas").document(rutina.getReferencia()).delete();
            rutina = new Rutina(tv.getText().toString(),lista,"Usuario");
            db.collection("Rutinas")
                    .add(rutina)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("BDD", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("BDD", "Error adding document", e);
                        }
                    });

        });
    }

    private void llenar(Rutina rutina){
        Map<String,Integer> mapa = rutina.getEjercicios();
        Set<String> ejers = mapa.keySet();
        for (String s:ejers){
            Ejercicio e = new Ejercicio(s,mapa.get(s)+"");
            adr.add(e);
        }
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
}