package com.example.android.beckbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EtablissementActivity extends AppCompatActivity {
    private Button faculteBtn;
    private Button ofpptBtn;
    private Button hopitalBtn;
    private Button banqueBtn;
    private Button provinceBtn;
    private Button gareBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etablissement);

        faculteBtn = (Button) findViewById(R.id.faculte);
        ofpptBtn = (Button) findViewById(R.id.ofppt);
        hopitalBtn = (Button) findViewById(R.id.hopital);
        banqueBtn = (Button) findViewById(R.id.banque);
        provinceBtn = (Button) findViewById(R.id.province);
        gareBtn = (Button) findViewById(R.id.gare);

        faculteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, faculte.class);
                startActivity(l);
            }
        });


        ofpptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, ofppt.class);
                startActivity(l);
            }
        });
        hopitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, hopital.class);
                startActivity(l);
            }
        });

        provinceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, province.class);
                startActivity(l);
            }
        });

        banqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, banque.class);
                startActivity(l);
            }
        });

        gareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent( EtablissementActivity.this, gare.class);
                startActivity(l);
            }
        });
    }
}