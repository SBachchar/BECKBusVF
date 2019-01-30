package com.example.android.beckbus;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChauffeurActivity extends AppCompatActivity {

    private Button MonProfil, Controler, Deconnexion, Pannes , Envoyer;
    private TextView IdBus , Description , NbrePassagers, Localisation;
    private EditText IdBusEdit, DescriptionEdit, NbrePassagersEdit, LocalisationEdit;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chauffeur);

        MonProfil = (Button) findViewById(R.id.profil);
        Controler = (Button) findViewById(R.id.controle);
        Deconnexion = (Button) findViewById(R.id.deconnexion);
        Pannes = (Button) findViewById(R.id.pannes);
        Envoyer = (Button) findViewById(R.id.envoyer);

        IdBus = (TextView) findViewById(R.id.id_bus);
        Description = (TextView) findViewById(R.id.description);
        NbrePassagers = (TextView) findViewById(R.id.nbre_passagers);
        Localisation = (TextView) findViewById(R.id.localisation);

        IdBusEdit = (EditText) findViewById(R.id.id_bus_edit);
        DescriptionEdit = (EditText) findViewById(R.id.description_edit);
        NbrePassagersEdit = (EditText) findViewById(R.id.nbre_passagers_edit);
        LocalisationEdit = (EditText) findViewById(R.id.localisation_edit);

        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        MonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent settingsIntent = new Intent(ChauffeurActivity.this,SettingsActivity.class);
                    startActivity(settingsIntent);
                }
        });

        Controler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri addressUri = Uri.parse("https://www.the-qrcode-generator.com/scan");
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
                startActivity(intent);
            }
        });

        Deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent loginIntent = new Intent(ChauffeurActivity.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });

        Pannes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonProfil.setVisibility(View.INVISIBLE);
                Controler.setVisibility(View.INVISIBLE);
                Deconnexion.setVisibility(View.INVISIBLE);
                Pannes.setVisibility(View.INVISIBLE);
                IdBus.setVisibility(View.VISIBLE);
                IdBusEdit.setVisibility(View.VISIBLE);
                Description.setVisibility(View.VISIBLE);
                DescriptionEdit.setVisibility(View.VISIBLE);
                NbrePassagers.setVisibility(View.VISIBLE);
                NbrePassagersEdit.setVisibility(View.VISIBLE);
                Localisation.setVisibility(View.VISIBLE);
                LocalisationEdit.setVisibility(View.VISIBLE);
                Envoyer.setVisibility(View.VISIBLE);
                setTitle("Déclaration de panne");
            }
        });

        Envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setBusID = IdBusEdit.getText().toString();
                String setDescription = DescriptionEdit.getText().toString();
                String setNbrePassagers = NbrePassagersEdit.getText().toString();
                String setLocalisation = LocalisationEdit.getText().toString();

                if((TextUtils.isEmpty(setBusID)) || (TextUtils.isEmpty(setDescription)) || (TextUtils.isEmpty(setNbrePassagers)) || (TextUtils.isEmpty(setLocalisation))){
                    Toast.makeText(ChauffeurActivity.this, "Veuillez remplir toutes les cases, svp!", Toast.LENGTH_SHORT).show();
                }

                else{
                    HashMap<String,Object> profileMap = new HashMap<>();
                    profileMap.put("ID de bus",setBusID);
                    profileMap.put("Description de panne",setDescription);
                    profileMap.put("Nombre de passagers",setNbrePassagers);
                    profileMap.put("Localisation de bus",setLocalisation);
                    RootRef.child("Pannes").child(setBusID).setValue(profileMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ChauffeurActivity.this, "Panne a été déclarée!", Toast.LENGTH_SHORT).show();
                                        MonProfil.setVisibility(View.VISIBLE);
                                        Controler.setVisibility(View.VISIBLE);
                                        Deconnexion.setVisibility(View.VISIBLE);
                                        Pannes.setVisibility(View.VISIBLE);
                                        IdBus.setVisibility(View.INVISIBLE);
                                        IdBusEdit.setVisibility(View.INVISIBLE);
                                        Description.setVisibility(View.INVISIBLE);
                                        DescriptionEdit.setVisibility(View.INVISIBLE);
                                        NbrePassagers.setVisibility(View.INVISIBLE);
                                        NbrePassagersEdit.setVisibility(View.INVISIBLE);
                                        Localisation.setVisibility(View.INVISIBLE);
                                        LocalisationEdit.setVisibility(View.INVISIBLE);
                                        Envoyer.setVisibility(View.INVISIBLE);
                                        setTitle("Espace Chauffeur");
                                    }
                                    else{
                                        String message = task.getException().toString();
                                        Toast.makeText(ChauffeurActivity.this, "Error: "+ message, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
