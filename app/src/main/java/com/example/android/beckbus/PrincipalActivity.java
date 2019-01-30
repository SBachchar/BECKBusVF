package com.example.android.beckbus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmapactivity();
            }
        });

        setTitle();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private  void openmapactivity(){
        Intent intent = new Intent(PrincipalActivity.this,MapsActivity.class);
        startActivity(intent);}

    @Override
    protected void onStart() {
        super.onStart();
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().equals("Chauffeur")){
                    SendUserToChauffeurActivity();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SendUserToSettingsActivity();
        }

        if (id == R.id.action_logout) {
            mAuth.signOut();
            SendUserToMainActivity();
            Toast.makeText(PrincipalActivity.this, "Logged Out Successfully !", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void SendUserToMainActivity() {
        Intent loginIntent = new Intent(PrincipalActivity.this,MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    private void SendUserToSettingsActivity() {
        Intent settingsIntent = new Intent(PrincipalActivity.this,SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.lignes) {
            Intent ligneIntent = new Intent(PrincipalActivity.this, LignesActivity.class);
            startActivity(ligneIntent);
        } else if (id == R.id.tarifs) {
            Intent tarifIntent = new Intent(PrincipalActivity.this, TarifsActivity.class);
            startActivity(tarifIntent);
        } else if (id == R.id.horaire) {
            Intent horaireIntent = new Intent(PrincipalActivity.this, HorairesActivity.class);
            startActivity(horaireIntent);
        }else if (id == R.id.abonnements) {
            Intent abonnementIntent = new Intent(PrincipalActivity.this, AbonnementActivity.class);
            startActivity(abonnementIntent);
        } else if (id == R.id.info) {
            Intent infoIntent = new Intent(PrincipalActivity.this, InformationsActivity.class);
            startActivity(infoIntent);
        } else if (id == R.id.etab_pub) {
            Intent etablissementIntent = new Intent(PrincipalActivity.this, EtablissementActivity.class);
            startActivity(etablissementIntent);
        } else if (id == R.id.reserve) {
            Intent reserveIntent = new Intent(PrincipalActivity.this, ReservationActivity.class);
            startActivity(reserveIntent);
        } else if (id == R.id.reclamation) {
            Intent reclamationIntent = new Intent(PrincipalActivity.this, ReclamationsActivity.class);
            startActivity(reclamationIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void SendUserToChauffeurActivity(){
        Intent chauffeurIntent = new Intent(PrincipalActivity.this,ChauffeurActivity.class);
        chauffeurIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(chauffeurIntent);
        finish();
    }
    private void setTitle(){
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().equals("Etudiant")){
                    setTitle("Espace Etudiant");
                }
                else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                    setTitle("Espace Fonctionnaire");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
