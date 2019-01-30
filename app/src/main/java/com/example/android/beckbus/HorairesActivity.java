package com.example.android.beckbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HorairesActivity extends AppCompatActivity {
    private TextView PremierTemps1, DernierTemps1, FrequenceTemps1;
    private TextView PremierTemps2, DernierTemps2, FrequenceTemps2;
    private Spinner Ligne;
    private int Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);

        PremierTemps1 = (TextView) findViewById(R.id.premier_temps1);
        DernierTemps1 = (TextView) findViewById(R.id.dernier_temps1);
        FrequenceTemps1 = (TextView) findViewById(R.id.frequence_temps1);
        PremierTemps2 = (TextView) findViewById(R.id.premier_temps2);
        DernierTemps2 = (TextView) findViewById(R.id.dernier_temps2);
        FrequenceTemps2 = (TextView) findViewById(R.id.frequence_temps2);
        Ligne = (Spinner) findViewById(R.id.ligne_spinner);

        Ligne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(Ligne.getSelectedItemPosition()) {
                    case(0):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("35 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("35 min");
                        break;

                    case(1):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("30 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("30 min");
                        break;

                    case(2):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("20:40");
                        FrequenceTemps1.setText("55 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("55 min");
                        break;

                    case(3):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("35 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("35 min");
                        break;

                    case(4):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("50 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("50 min");
                        break;

                    case(5):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("20:40");
                        FrequenceTemps1.setText("55 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("55 min");
                        break;

                    case(6):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("45 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("45 min");
                        break;

                    case(7):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("35 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("35 min");
                        break;

                    case(8):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("20:40");
                        FrequenceTemps1.setText("55 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("55 min");
                        break;

                    case(9):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("50 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("50 min");
                        break;

                    case(10):
                        PremierTemps1.setText("7:10");
                        DernierTemps1.setText("20:00");
                        FrequenceTemps1.setText("45 min");
                        DernierTemps2.setText("7:40");
                        DernierTemps2.setText("21:00");
                        FrequenceTemps2.setText("45 min");
                        break;

                    case(11):
                        PremierTemps1.setText("7:00");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("35 min");
                        DernierTemps2.setText("7:30");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("35 min");
                        break;
                    case(12):
                        PremierTemps1.setText("7:05");
                        DernierTemps1.setText("21:00");
                        FrequenceTemps1.setText("25 min");
                        DernierTemps2.setText("7:35");
                        DernierTemps2.setText("20:30");
                        FrequenceTemps2.setText("25 min");
                        break;
                    default:
                        Toast.makeText(HorairesActivity.this,"Default",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.d("never1","onStart");
    }
}
