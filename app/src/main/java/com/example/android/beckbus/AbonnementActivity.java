package com.example.android.beckbus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.w3c.dom.Text;

import java.io.File;

public class AbonnementActivity extends AppCompatActivity {

    private CheckBox box1,box2,box3,box4,box5,box6,box7,boxAccepter;
    private TextView textTalib , textMowadaf;
    private TextView conditions,date;
    private TextView textRDV, dateRDV;
    private TextView prix,prixTotal,dhs;
    private Button rdvBtn;
    private ScrollView allBoxes;
    private int mCount = 0;
    private ImageView ImageQR;

    private Button AbonneBtn , NonAbonneBtn;
    Bitmap bitmap;

    private String currentUserID, CINPassager;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonnement);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();


        AbonneBtn = (Button) findViewById(R.id.abonne_btn);
        NonAbonneBtn = (Button) findViewById(R.id.non_abonne_btn);
        ImageQR = (ImageView) findViewById(R.id.img_code);

        box1 = (CheckBox) findViewById(R.id.checkBox1);
        box2 = (CheckBox) findViewById(R.id.checkBox2);
        box3 = (CheckBox) findViewById(R.id.checkBox3);
        box4 = (CheckBox) findViewById(R.id.checkBox4);
        box5 = (CheckBox) findViewById(R.id.checkBox5);
        box6 = (CheckBox) findViewById(R.id.checkBox6);
        box7 = (CheckBox) findViewById(R.id.checkBox7);
        textTalib = (TextView) findViewById(R.id.texte_talib);
        textMowadaf = (TextView) findViewById(R.id.texte_mowadaf);

        textRDV = (TextView) findViewById(R.id.texte_rdv);
        dateRDV = (TextView) findViewById(R.id.date1);

        boxAccepter = (CheckBox) findViewById(R.id.accepter);
        prix = (TextView) findViewById(R.id.prix);
        prixTotal = (TextView) findViewById(R.id.prix_total);
        dhs = (TextView) findViewById(R.id.dhs);
        conditions = (TextView) findViewById(R.id.conditions);
        rdvBtn = (Button) findViewById(R.id.rdv);
        allBoxes = (ScrollView) findViewById(R.id.scroll);
        date = (TextView) findViewById(R.id.date);

        AbonneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootRef.child("Abonnements").child(CINPassager).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.getValue().toString().equals("false"))) {
                            try {
                                String etatAbonne = "N'est pas abonné(e)";
                                bitmap = TextToImageEncode(etatAbonne);
                                ImageQR.setImageBitmap(bitmap);
                                ImageQR.setVisibility(View.VISIBLE);


                            } catch (WriterException e) {
                                e.printStackTrace();
                            }

                        }else if((dataSnapshot.exists()) && (dataSnapshot.getValue().toString().equals("true"))){
                            try {
                                String etatAbonne = "Abonné(e)";
                                bitmap = TextToImageEncode(etatAbonne);

                                ImageQR.setImageBitmap(bitmap);
                                ImageQR.setVisibility(View.VISIBLE);


                            } catch (WriterException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(AbonnementActivity.this, "Veuillez prendre un RDV, SVP!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        NonAbonneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                definirTexte();
                allBoxes.setVisibility(View.VISIBLE);
                prix.setVisibility(View.VISIBLE);
                prixTotal.setVisibility(View.VISIBLE);
                dhs.setVisibility(View.VISIBLE);
                conditions.setVisibility(View.VISIBLE);
                boxAccepter.setVisibility(View.VISIBLE);
                NonAbonneBtn.setVisibility(View.INVISIBLE);
                AbonneBtn.setVisibility(View.INVISIBLE);

            }
        });

        conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telecharger();
            }
        });

        getUserCIN();

        boxAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boxAccepter.isChecked()){
                    date.setVisibility(View.VISIBLE);
                    rdvBtn.setVisibility(View.VISIBLE);
                } else{
                    date.setVisibility(View.INVISIBLE);
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        rdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jour = dateRDV.getText().toString();
                if(box1.isChecked() || box2.isChecked() || box3.isChecked() || box4.isChecked() || box5.isChecked() || box6.isChecked() || box7.isChecked()){
                    if(date.getText().toString().equals("Choisir une date")){
                        Toast.makeText(AbonnementActivity.this, "Veuillez choisir une date !", Toast.LENGTH_SHORT).show();
                    }else{
                        allBoxes.setVisibility(View.INVISIBLE);
                        textMowadaf.setVisibility(View.INVISIBLE);
                        textTalib.setVisibility(View.INVISIBLE);
                        prixTotal.setVisibility(View.INVISIBLE);
                        prix.setVisibility(View.INVISIBLE);
                        dhs.setVisibility(View.INVISIBLE);
                        boxAccepter.setVisibility(View.INVISIBLE);
                        conditions.setVisibility(View.INVISIBLE);
                        rdvBtn.setVisibility(View.INVISIBLE);
                        date.setVisibility(View.INVISIBLE);
                        textRDV.setVisibility(View.VISIBLE);
                        dateRDV.setVisibility(View.VISIBLE);
                        ImageQR.setVisibility(View.INVISIBLE);
                        RootRef.child("Rendez-Vous").child(jour).child("CIN").setValue(CINPassager);
                        RootRef.child("Abonnements").child(CINPassager).setValue("false");
                    }

                }else{
                    Toast.makeText(AbonnementActivity.this, "Veuillez choisir une ligne !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        definirPrix();
    }

    private void telecharger() {
        Uri webpage = Uri.parse("https://firebasestorage.googleapis.com/v0/b/beckbus-898e4.appspot.com/o/Conditions.pdf?alt=media&token=4dad033c-ad0c-4959-87ae-ca3696b26894");
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);
    }

    private void ajouterPrixTalib() {
        mCount=mCount+80;
        prix.setText(Integer.toString(mCount));
    }
    private void retrancherPrixTalib() {
        mCount=mCount-80;
        prix.setText(Integer.toString(mCount));
    }
    private void ajouterPrixMowadaf() {
        mCount=mCount+100;
        prix.setText(Integer.toString(mCount));
    }
    private void retrancherPrixMowadaf() {
        mCount=mCount-100;
        prix.setText(Integer.toString(mCount));
    }

    private void definirTexte(){
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().equals("Etudiant")){
                    textTalib.setVisibility(View.VISIBLE);
                    textMowadaf.setVisibility(View.INVISIBLE);
                }
                else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                    textTalib.setVisibility(View.INVISIBLE);
                    textMowadaf.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void definirPrix(){
        RootRef.child("Users").child(currentUserID).child("Occupation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().equals("Etudiant")){
                    box1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box1.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box2.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box3.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box4.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box5.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box6.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                    box7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box7.isChecked()){
                                ajouterPrixTalib();
                            } else{
                                retrancherPrixTalib();
                            }
                        }
                    });
                }
                else if(dataSnapshot.getValue().equals("Fonctionnaire")){
                    box1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box1.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box2.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box3.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box4.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box5.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box6.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                    box7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(box7.isChecked()){
                                ajouterPrixMowadaf();
                            } else{
                                retrancherPrixMowadaf();
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                                Toast.makeText(AbonnementActivity.this, "Vous ne disposez pas de CIN", Toast.LENGTH_SHORT).show();

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
                                Toast.makeText(AbonnementActivity.this, "Vous ne disposez pas de CIN", Toast.LENGTH_SHORT).show();

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


    private Bitmap TextToImageEncode(String s) throws WriterException{
        BitMatrix bitMatrix;
        try{
            bitMatrix = new MultiFormatWriter().encode(
                    s,
                    BarcodeFormat.QR_CODE,
                    500,500,null);
        } catch (IllegalArgumentException Illegalargumentexception){
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

        return bitmap;
    }
}
