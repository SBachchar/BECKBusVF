package com.example.android.beckbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReclamationsActivity extends AppCompatActivity {

    private EditText Tel, Date, Ligne, Description;
    private Button Envoyer;
    private Spinner Anomalie;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private String currentUserID, statut,nomm,prenomm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamations);

        Tel = (EditText) findViewById(R.id.set_tel);
        Date = (EditText) findViewById(R.id.set_date);
        Ligne = (EditText) findViewById(R.id.set_ligne);
        Description = (EditText) findViewById(R.id.set_description);
        Envoyer = (Button) findViewById(R.id.envoyer_button);
        Anomalie = (Spinner) findViewById(R.id.anomalie_spinner);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();


        Envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reclamer();

            }
        });
    }




    private void Reclamer() {
        final HashMap<String,Object> reclamationMap = new HashMap<>();
        final String setTel = Tel.getText().toString();
        final String setDate = Date.getText().toString();
        final String setLigne = Ligne.getText().toString();
        final String setDescription = Description.getText().toString();
        final String setAnomalie = Anomalie.getSelectedItem().toString();
        reclamationMap.put("Téléphone",setTel);
        reclamationMap.put("Date",setDate);
        reclamationMap.put("Ligne",setLigne);
        reclamationMap.put("Description",setDescription);
        reclamationMap.put("Anomalie",setAnomalie);

        if((TextUtils.isEmpty(setTel)) || (TextUtils.isEmpty(setDate)) || (TextUtils.isEmpty(setLigne)) || (TextUtils.isEmpty(setDescription)) || (TextUtils.isEmpty(setAnomalie))){
            Toast.makeText(this, "Complétez tous les champs, svp!", Toast.LENGTH_LONG).show();
        }else{
            RootRef.child("Réclamations").child(currentUserID).setValue(reclamationMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ReclamationsActivity.this, "Votre réclamation a été bien envoyée!", Toast.LENGTH_SHORT).show();
                                SendUserToPrincipalActivity();
                            }
                            else{
                                String message = task.getException().toString();
                                Toast.makeText(ReclamationsActivity.this, "Error: "+ message, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }


    private void SendUserToPrincipalActivity() {
        Intent principalIntent = new Intent(ReclamationsActivity.this,PrincipalActivity.class);
        startActivity(principalIntent);
    }
}
