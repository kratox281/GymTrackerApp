package com.example.gymtracker.apartadoajustes;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymtracker.aboutme.AboutMe;
import com.example.gymtracker.databinding.ActivityAjustesBinding;
import com.example.gymtracker.entrenamiento.ElegirEntrenamiento;
import com.example.gymtracker.entrenamiento.Entrenamiento;
import com.example.gymtracker.main.MainActivity;
import com.example.gymtracker.record.Records;
import com.example.gymtracker.rutinas.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.*;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.gymtracker.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import resources.Record;
import resources.Usuario;

public class Ajustes extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAjustesBinding binding;
    private String referencia ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAjustesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_ajustes);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        rellenar();
        findViewById(R.id.bt_guardarAjustes).setOnClickListener(v -> {
            actualizacionUsuario();
        });

    }

    private void rellenar() {
        EditText et = findViewById(R.id.etNombreAjustes);
        EditText etr = findViewById(R.id.etRepeticionesAjustes);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nombre = (String) document.get("nombre");
                                Long repeticiones = (Long) document.get("repeticiones");
                                String repes = String.valueOf(repeticiones);
                                //Log.e("MIRAAAA", "nombre "+nombre+" repes "+repeticiones );
                                et.setText(nombre);
                                etr.setText(repes);
                                referencia = document.getId();

                            }
                        } else {
                            Log.w("BDD", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    //COPIAR ESTO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajustes, menu);
        return true;
    }


    //Redireccionar a la actividad necesaria desde el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent test;
        switch (item.getTitle().toString()){
            case "Records":
                test= new Intent(getApplicationContext(), Records.class);
                actualizacionUsuario();
                startActivity(test);
                finish();
                break;
            case "Settings":
                test = new Intent(getApplicationContext(),Ajustes.class);
                actualizacionUsuario();
                startActivity(test);
                finish();
                break;
            case "Entrenamiento":
                test = new Intent(getApplicationContext(), ElegirEntrenamiento.class);
                actualizacionUsuario();
                startActivity(test);
                finish();
                break;
            case "Rutinas":
                test = new Intent(getApplicationContext(), OpcionesRutinas.class);
                actualizacionUsuario();
                startActivity(test);
                finish();
                break;
            default:
                test = new Intent(getApplicationContext(), MainActivity.class);
                actualizacionUsuario();
                startActivity(test);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //HASTA AQUI

    private void actualizacionUsuario(){
        EditText et = findViewById(R.id.etNombreAjustes);
        EditText etr = findViewById(R.id.etRepeticionesAjustes);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (!referencia.equals("")) {
            db.collection("Usuario").document(referencia).delete();
        }
        db.collection("Usuario")
                .add(new Usuario(et.getText().toString(),Integer.parseInt(etr.getText().toString())))
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

    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_ajustes);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}