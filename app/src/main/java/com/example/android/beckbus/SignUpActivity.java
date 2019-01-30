package com.example.android.beckbus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.internal.Util;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText UserEmail, UserPassword;
    private Spinner UtilisateurSpinner;
    private CheckBox BoxEtd , BoxFct;
    private RadioGroup Boxes;
    private String currentUserID;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        loadingBar = new ProgressDialog(this);

        CreateAccountButton = (Button) findViewById(R.id.register_button);
        UserEmail = (EditText) findViewById(R.id.register_email);
        UserPassword = (EditText) findViewById(R.id.register_password);
        UtilisateurSpinner = (Spinner) findViewById(R.id.utilisateur_spinner);
        BoxEtd = (CheckBox) findViewById(R.id.box_etudiant);
        BoxFct = (CheckBox) findViewById(R.id.box_fonctionnaire);
        Boxes = (RadioGroup) findViewById(R.id.boxes);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

        UtilisateurSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(UtilisateurSpinner.getSelectedItem().toString().equals("Passager")){
                    Boxes.setVisibility(View.VISIBLE);
                }else{
                    Boxes.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        BoxEtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BoxEtd.isChecked()){
                    BoxFct.setChecked(false);
                }
            }
        });
        BoxFct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BoxFct.isChecked()){
                    BoxEtd.setChecked(false);
                }
            }
        });
    }

    private void CreateNewAccount() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Saisir votre email", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Saisir un mot de passe", Toast.LENGTH_LONG).show();
        }
        else{
            loadingBar.setTitle("Création d'un nouveau compte");
            loadingBar.setMessage("Veuillez attendre SVP ...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            if(UtilisateurSpinner.getSelectedItem().toString().equals("Chauffeur")){
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String Email = mAuth.getCurrentUser().getEmail();
                            final HashMap<String,Object> donnees = new HashMap<>();
                            donnees.put("uid",currentUserID);
                            donnees.put("Email",Email);
                            donnees.put("Occupation","Chauffeur");
                            String currentUserID = mAuth.getCurrentUser().getUid();
                            RootRef.child("Users").child(currentUserID).setValue(donnees);
                            SendUserToChauffeurActivity();
                            Toast.makeText(SignUpActivity.this, "Compte créé avec succés", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                        else{
                            String message = task.getException().toString();
                            Toast.makeText(SignUpActivity.this, "Erreur : " + message, Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                });
            }else{
                if(BoxEtd.isChecked()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String Email = mAuth.getCurrentUser().getEmail();
                                final HashMap<String,Object> donnees = new HashMap<>();
                                donnees.put("uid",currentUserID);
                                donnees.put("Email",Email);
                                donnees.put("Occupation","Etudiant");
                                String currentUserID = mAuth.getCurrentUser().getUid();
                                RootRef.child("Users").child(currentUserID).setValue(donnees);
                                SendUserToSettingsActivity();
                                Toast.makeText(SignUpActivity.this, "Compte créé avec succés", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else{
                                String message = task.getException().toString();
                                Toast.makeText(SignUpActivity.this, "Erreur : " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
                }else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String Email = mAuth.getCurrentUser().getEmail();
                                final HashMap<String,Object> donnees = new HashMap<>();
                                donnees.put("uid",currentUserID);
                                donnees.put("Email",Email);
                                donnees.put("Occupation","Fonctionnaire");
                                String currentUserID = mAuth.getCurrentUser().getUid();
                                RootRef.child("Users").child(currentUserID).setValue(donnees);
                                SendUserToSettingsActivity();
                                Toast.makeText(SignUpActivity.this, "Compte créé avec succés", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else{
                                String message = task.getException().toString();
                                Toast.makeText(SignUpActivity.this, "Erreur : " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
                }
            }
        }
    }

    private void SendUserToChauffeurActivity(){
        Intent chauffeurIntent = new Intent(SignUpActivity.this,ChauffeurActivity.class);
        chauffeurIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(chauffeurIntent);
        finish();
    }
    private void SendUserToSettingsActivity() {
        Intent profilIntent = new Intent(SignUpActivity.this, SettingsActivity.class);
        profilIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profilIntent);
        finish();
    }
}