package com.example.android.beckbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private Button Modifier;
    private EditText userNom, userPrenom, userAge, CINValue;
    private TextView CINText, statutView;
    private String currentUserID;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        Modifier = (Button) findViewById(R.id.valider_button);
        userNom = (EditText) findViewById(R.id.set_nom);
        userPrenom = (EditText) findViewById(R.id.set_prenom);
        userAge = (EditText) findViewById(R.id.set_age);
        CINValue = (EditText) findViewById(R.id.cin_value);
        CINText = (TextView) findViewById(R.id.cin_text);
        statutView = (TextView) findViewById(R.id.set_status);

        setStatut();

        Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSettings();
            }
        });
        RetrieveUserInfo();
    }

    private void RetrieveUserInfo() {
        RootRef.child("Utilisateurs").child("Chauffeurs").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("nom"))){
                    String retrieveUserNom = dataSnapshot.child("nom").getValue().toString();
                    String retrieveUserPrenom = dataSnapshot.child("prenom").getValue().toString();
                    String retrieveAge = dataSnapshot.child("age").getValue().toString();
                    String retrieveCIN = dataSnapshot.child("CIN").getValue().toString();

                    userNom.setText(retrieveUserNom);
                    userPrenom.setText(retrieveUserPrenom);
                    userAge.setText(retrieveAge);
                    CINValue.setText(retrieveCIN);

                }
                else{
                    Toast.makeText(SettingsActivity.this, "Veuillez Compléter votre profil!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RootRef.child("Utilisateurs").child("Passagers").child("Fonctionnaires").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("nom"))){
                    String retrieveUserNom = dataSnapshot.child("nom").getValue().toString();
                    String retrieveUserPrenom = dataSnapshot.child("prenom").getValue().toString();
                    String retrieveAge = dataSnapshot.child("age").getValue().toString();
                    String retrieveCIN = dataSnapshot.child("CIN").getValue().toString();

                    userNom.setText(retrieveUserNom);
                    userPrenom.setText(retrieveUserPrenom);
                    userAge.setText(retrieveAge);
                    CINValue.setText(retrieveCIN);

                }
                else{
                    Toast.makeText(SettingsActivity.this, "Veuillez Compléter votre profil!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RootRef.child("Utilisateurs").child("Passagers").child("Etudiants").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("nom"))){
                    String retrieveUserNom = dataSnapshot.child("nom").getValue().toString();
                    String retrieveUserPrenom = dataSnapshot.child("prenom").getValue().toString();
                    String retrieveAge = dataSnapshot.child("age").getValue().toString();
                    String retrieveCIN = dataSnapshot.child("CIN").getValue().toString();

                    userNom.setText(retrieveUserNom);
                    userPrenom.setText(retrieveUserPrenom);
                    userAge.setText(retrieveAge);
                    CINValue.setText(retrieveCIN);

                }
                else{
                    Toast.makeText(SettingsActivity.this, "Veuillez Compléter votre profil!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void UpdateSettings() {
        final HashMap<String,Object> profileMap = new HashMap<>();
        final String setNom = userNom.getText().toString();
        final String setPrenom = userPrenom.getText().toString();
        final String setAge = userAge.getText().toString();
        final String setCIN = CINValue.getText().toString();
        profileMap.put("uid",currentUserID);
        profileMap.put("nom",setNom);
        profileMap.put("prenom",setPrenom);
        profileMap.put("age",setAge);
        profileMap.put("CIN",setCIN);

        if((TextUtils.isEmpty(setNom)) || (TextUtils.isEmpty(setPrenom))){
            Toast.makeText(this, "Entrez votre nom complet, svp!", Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(setCIN)){
            Toast.makeText(this, "Entrez votre CIN, svp!", Toast.LENGTH_LONG).show();
        }else{
            RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue().equals("Chauffeur")){

                        RootRef.child("Utilisateurs").child("Chauffeurs").child(currentUserID).setValue(profileMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SettingsActivity.this, "Profil a été bien mis à jour", Toast.LENGTH_SHORT).show();
                                            SendUserToPrincipalActivity();
                                        }
                                        else{
                                            String message = task.getException().toString();
                                            Toast.makeText(SettingsActivity.this, "Erreur: "+ message, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    }
                    else if(dataSnapshot.getValue().equals("Etudiant")){

                        RootRef.child("Utilisateurs").child("Passagers").child("Etudiants").child(currentUserID).setValue(profileMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SettingsActivity.this, "Profil a été bien mis à jour", Toast.LENGTH_SHORT).show();
                                            SendUserToPrincipalActivity();
                                        }
                                        else{
                                            String message = task.getException().toString();
                                            Toast.makeText(SettingsActivity.this, "Error: "+ message, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                    else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                        RootRef.child("Utilisateurs").child("Passagers").child("Fonctionnaires").child(currentUserID).setValue(profileMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SettingsActivity.this, "Profil a été bien mis à jour", Toast.LENGTH_SHORT).show();
                                            SendUserToPrincipalActivity();
                                        }
                                        else{
                                            String message = task.getException().toString();
                                            Toast.makeText(SettingsActivity.this, "Erreur: "+ message, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(SettingsActivity.this, "Veuillez Compléter votre profil!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    private void setStatut(){
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().equals("Chauffeur")){
                    statutView.setText("Chauffeur");
                }
                else if(dataSnapshot.getValue().equals("Etudiant")){
                    statutView.setText("Etudiant");
                }
                else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                    statutView.setText("Fonctionnaire");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SendUserToPrincipalActivity() {
        Intent principalIntent = new Intent(SettingsActivity.this,PrincipalActivity.class);
        startActivity(principalIntent);
    }


}
