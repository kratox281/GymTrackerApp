package com.example.gymtracker.record;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymtracker.R;
import com.example.gymtracker.aboutme.AboutMe;
import com.example.gymtracker.apartadoajustes.Ajustes;
import com.example.gymtracker.databinding.ActivityRecordsBinding;
import com.example.gymtracker.entrenamiento.ElegirEntrenamiento;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.rutinas.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.checkerframework.checker.nullness.qual.NonNull;

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
        setTitle("");
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
        llenar();
        btRegistrar.setOnClickListener(view -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Toast.makeText(this, "Record Registrado", Toast.LENGTH_SHORT).show();

            db.collection("Records")
                    .add(new Record(etNombreEjer.getText().toString(),Double.parseDouble( etPesoEjer.getText().toString())))
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
            adr.clear();
            llenar();
        });
        listarecords.setOnItemClickListener((parent, view, position, id) -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Record rec = adr.getItem(position);
            db.collection("Records").document(rec.getReferencia()).delete();
            Toast.makeText(this, "Se ha eliminado el record de "+rec.getEjercicio(), Toast.LENGTH_SHORT).show();
            adr.remove(adr.getItem(position));


        });
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

   //Proxima actualizacion
    private void  existe(String nombre){
        Log.e("MetodoEXISTE", "nombre: "+nombre );
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Records")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Record record = new Record((String) document.get("ejercicio"), (Double) document.get("peso"));
                                Log.e("CONSULTA", "Referencia: "+document.getId()+" ejer"+document.get("ejercicio"));
                                record.setReferencia(document.getId());
                                if (record.getEjercicio().equalsIgnoreCase(nombre)){
                                    Log.e("MetodoEXISTE", "coincide el nombre" );
                                    db.collection("Records").document(record.getReferencia()).delete();
                                }
                            }
                        } else {
                            Log.w("BDD", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
    private void llenar(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Records")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Record r = new Record((String) document.get("ejercicio"), (Double) document.get("peso"));
                                Log.e("CONSULTA", "Referencia: "+document.getId()+" ejer"+document.get("ejercicio"));
                                r.setReferencia(document.getId());
                                adr.add(r);
                            }
                        } else {
                            Log.w("BDD", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}