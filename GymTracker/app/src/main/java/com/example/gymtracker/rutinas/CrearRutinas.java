package com.example.gymtracker.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import resources.Ejercicio;
import resources.Record;
import resources.Rutina;
import resources.RutinaArrayAdapter;

public class CrearRutinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutinas);
        Button guardarEjer = findViewById(R.id.button);
        Button guardarRutina = findViewById(R.id.button2);
        EditText nombreRutina = findViewById(R.id.editTextTextPersonName4);
        EditText nombreEjer =findViewById(R.id.editTextTextPersonName5);
        EditText numeroRepes =findViewById(R.id.editTextTextPersonName6);
        ListView lv = findViewById(R.id.lv_crearRutinas);
        ArrayList<Ejercicio>ejercicios = new ArrayList<>();
        RutinaArrayAdapter adr =  new  RutinaArrayAdapter(getApplicationContext(),R.layout.listviewejercicios,ejercicios);
        lv.setAdapter(adr);


        guardarEjer.setOnClickListener(v->{
            adr.add(new Ejercicio(nombreEjer.getText().toString(),numeroRepes.getText().toString()));
            Toast.makeText(this, "Ejercicio Registrado", Toast.LENGTH_SHORT).show();
        });
        guardarRutina.setOnClickListener(v->{

            Map<String,Integer>lista = new HashMap<>();
            for(Ejercicio e:ejercicios){
                lista.put(e.getNombre(),Integer.parseInt(e.getRepeticiones()));
            }
            Rutina rutina = new Rutina(nombreRutina.getText().toString(),lista,"Usuario");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
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
}