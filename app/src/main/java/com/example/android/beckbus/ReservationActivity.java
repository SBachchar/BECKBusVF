package com.example.android.beckbus;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity {

    private TextView NbrBusTexte, prix, dateTexte, De, Vers;
    private EditText Date, Depart, Destination;
    private Spinner NbrBus;
    private Button ReserveButton, MesReservations, ReserveBusButton;
    private String CINPassager, currentUserID, user_rsrv_key ;

    int cmpt=0;

    ListView lvReservation;
    ArrayList<String> listReservation = new ArrayList<String>();
    ArrayAdapter arrayAdpt;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef,dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        lvReservation = (ListView) findViewById(R.id.lvReservation);
        arrayAdpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listReservation);
        lvReservation.setAdapter(arrayAdpt);

        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        currentUserID = mAuth.getCurrentUser().getUid();

        NbrBusTexte = (TextView) findViewById(R.id.Nbr_bus);
        prix = (TextView) findViewById(R.id.prix_bus);
        dateTexte = (TextView) findViewById(R.id.date);
        De = (TextView) findViewById(R.id.de);
        Vers = (TextView) findViewById(R.id.vers);


        Date = (EditText) findViewById(R.id.date_choose);
        Depart = (EditText) findViewById(R.id.res_dep);
        Destination = (EditText) findViewById(R.id.res_dest);

        NbrBus = (Spinner) findViewById(R.id.NbrBus_spinner);

        ReserveButton = (Button) findViewById(R.id.reserve_btn);
        MesReservations = (Button) findViewById(R.id.reservations);
        ReserveBusButton = (Button) findViewById(R.id.rsrv_bus_btn);
        NbrBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nbr = NbrBus.getSelectedItem().toString();
                if(nbr.equals("1")){
                    prix.setText("400 Dhs");
                }else if(nbr.equals("2")){
                    prix.setText("800 Dhs");
                }else{
                    prix.setText("1200 Dhs");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getUserCIN();

        ReserveBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReserveBusButton.setVisibility(View.INVISIBLE);
                MesReservations.setVisibility(View.INVISIBLE);
                NbrBusTexte.setVisibility(View.VISIBLE);
                prix.setVisibility(View.VISIBLE);
                dateTexte.setVisibility(View.VISIBLE);
                De.setVisibility(View.VISIBLE);
                Vers.setVisibility(View.VISIBLE);
                Date.setVisibility(View.VISIBLE);
                Depart.setVisibility(View.VISIBLE);
                Destination.setVisibility(View.VISIBLE);
                NbrBus.setVisibility(View.VISIBLE);
                ReserveButton.setVisibility(View.VISIBLE);
                setTitle("Réservation de Bus");

            }
        });

        dbr = RootRef.child("Reservations");

        ReserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> map = new HashMap<String, Object>();
                user_rsrv_key = dbr.push().getKey();
                dbr.updateChildren(map);

                DatabaseReference dbr2 = dbr.child(CINPassager).child(user_rsrv_key);
                Map<String, Object> map2 = new HashMap<String, Object>();

                String setNombre = NbrBus.getSelectedItem().toString();
                String setPrix = prix.getText().toString();
                String setDate = Date.getText().toString();
                String setDepart = Depart.getText().toString();
                String setDestination = Destination.getText().toString();


                if((TextUtils.isEmpty(setNombre)) || (TextUtils.isEmpty(setDate)) || (TextUtils.isEmpty(setDepart)) || (TextUtils.isEmpty(setDestination))){
                    Toast.makeText(ReservationActivity.this, "Veuillez remplir toutes les cases, svp!", Toast.LENGTH_SHORT).show();
                }else{

                    map2.put("Nombre de bus",setNombre);
                    map2.put("Prix Total",setPrix);
                    map2.put("Date",setDate);
                    map2.put("Depart",setDepart);
                    map2.put("Destination",setDestination);

                    dbr2.updateChildren(map2);

                    ReserveBusButton.setVisibility(View.VISIBLE);
                    MesReservations.setVisibility(View.VISIBLE);
                    NbrBusTexte.setVisibility(View.INVISIBLE);
                    prix.setVisibility(View.INVISIBLE);
                    dateTexte.setVisibility(View.INVISIBLE);
                    De.setVisibility(View.INVISIBLE);
                    Vers.setVisibility(View.INVISIBLE);
                    Date.setVisibility(View.INVISIBLE);
                    Depart.setVisibility(View.INVISIBLE);
                    Destination.setVisibility(View.INVISIBLE);
                    NbrBus.setVisibility(View.INVISIBLE);
                    ReserveButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(ReservationActivity.this, "votre réservation a été bien enregistrée.", Toast.LENGTH_LONG).show();
                }
            }
        });

        MesReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootRef.child("Reservations").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if((dataSnapshot.exists()) && (dataSnapshot.hasChild(CINPassager))){
                            ReserveBusButton.setVisibility(View.INVISIBLE);
                            MesReservations.setVisibility(View.INVISIBLE);
                            lvReservation.setVisibility(View.VISIBLE);
                            setTitle("Mes réservations");
                            dbr.child(CINPassager).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    updateReservations(dataSnapshot);
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    updateReservations(dataSnapshot);
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else{
                            Toast.makeText(ReservationActivity.this, "Vous n'avez pas de réservations", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    private void updateReservations(DataSnapshot dataSnapshot){
        String nbre, prix, date, depart, destination, reservation;
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            cmpt++;
            arrayAdpt.insert("Réservation "+cmpt, arrayAdpt.getCount());
            date = (String) ((DataSnapshot)i.next()).getValue();
            depart = (String) ((DataSnapshot)i.next()).getValue();
            destination = (String) ((DataSnapshot)i.next()).getValue();
            nbre = (String) ((DataSnapshot)i.next()).getValue();
            prix = (String) ((DataSnapshot)i.next()).getValue();

            reservation = "Date: \t\t"+ date + "\nDépart: \t\t"+ depart + "\nDestination: \t\t"+ destination + "\nNombre de bus: \t\t"+ nbre + "\nPrix: \t\t"+ prix + "\n" ;
            arrayAdpt.insert(reservation, arrayAdpt.getCount());
            arrayAdpt.notifyDataSetChanged();
        }

    }

    private void getUserCIN(){
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().equals("Etudiant")){
                    RootRef.child("Utilisateurs").child("Passagers").child("Etudiants").child(currentUserID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("CIN"))){
                                CINPassager = dataSnapshot.child("CIN").getValue().toString();
                            }
                            else{
                                Toast.makeText(ReservationActivity.this, "Vous ne disposez pas de CIN", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                    RootRef.child("Utilisateurs").child("Passagers").child("Fonctionnaires").child(currentUserID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("CIN"))){
                                CINPassager = dataSnapshot.child("CIN").getValue().toString();
                            }
                            else{
                                Toast.makeText(ReservationActivity.this, "Vous ne disposez pas de CIN", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
