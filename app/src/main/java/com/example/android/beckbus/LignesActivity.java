package com.example.android.beckbus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LignesActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lignes);

        btn1 = (Button) findViewById(R.id.un);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation(v);
            }
        });

        btn2 = (Button) findViewById(R.id.douze);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation1(v);
            }
        });

        btn3 = (Button) findViewById(R.id.dix);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                openLocation2(v); }});

        btn4 = (Button) findViewById(R.id.deux);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation3(v);
            }
        });

        btn5 = (Button) findViewById(R.id.huit);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation4(v);
            }
        });

        btn6 = (Button) findViewById(R.id.neuf);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation5(v);
            }
        });

        btn7 = (Button) findViewById(R.id.cinq);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation6(v);
            }
        });

        btn8 = (Button) findViewById(R.id.quatre);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation7(v);
            }
        });

        btn9 = (Button) findViewById(R.id.trois);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation8(v);
            }
        });

        btn10 = (Button) findViewById(R.id.six);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation9(v);
            }
        });

        btn11 = (Button) findViewById(R.id.sept);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation10(v);
            }
        });

        btn13 = (Button) findViewById(R.id.onze);
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                openLocation11(v); }});
    }

    public void openLocation(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Riad+Salam,+Beni-Mellal/Bd+Ibn+Khaldoun,+Beni-Mellal/Boulevard+Mohammed+V,+B%C3%A9ni+Mellal/Facult%C3%A9+polydisciplinaire+sultan+moulay+slimane/@32.3630602,-6.3553078,14z/data=!4m26!4m25!1m5!1m1!1s0xda3871028505699:0xa49444a250355b20!2m2!1d-6.3797902!2d32.3182522!1m5!1m1!1s0xda387abb00d5811:0x35ff6ce00ddeb322!2m2!1d-6.3690608!2d32.3362127!1m5!1m1!1s0xda38706d5fb43bb:0xa333cd7ad4e3267e!2m2!1d-6.3563877!2d32.3357076!1m5!1m1!1s0xda385fee999f689:0x4e7fbccedbb34938!2m2!1d-6.318748!2d32.3753585!3e0");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation1(View view) {
        Uri addressUri1 = Uri.parse("https://www.google.com/maps/dir/Afourar/Faculty+Of+Science+And+Technics,+B%C3%A9ni+Mellal/@32.2305756,-6.517597,13z/data=!4m44!4m43!1m35!1m1!1s0xda47ddc6e7c23b7:0x58cb188f0fc6f6!2m2!1d-6.5!2d32.216667!3m4!1m2!1d-6.3628484!2d32.3347971!3s0xda38654e265d29d:0x337789aa7cedf2ac!3m4!1m2!1d-6.3596691!2d32.3347111!3s0xda38655d532b1d5:0xdc2d7f04e13cb21!3m4!1m2!1d-6.360431!2d32.3348777!3s0xda38655d532b1d5:0xdc2d7f04e13cb21!3m4!1m2!1d-6.3584699!2d32.3344191!3s0xda386566731394d:0x313a3787fb56304!3m4!1m2!1d-6.357282!2d32.3344763!3s0xda3865651721b95:0x120b12863d9c32eb!3m4!1m2!1d-6.3431049!2d32.3420947!3s0xda38667904319cd:0xb913f63b5a825bc5!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!3e0");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, addressUri1);
        startActivity(intent1);
    }

    public void openLocation2(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Tagzert/Faculty+Of+Science+And+Technics,+Mghilat,+B%C3%A9ni+Mellal/El+Harboulia/EST+Beni+Mellal,+Boulevard+Mohammed+V,+Beni-Mellal/32.3390384,-6.3464317/@32.337656,-6.3467107,17z/data=!4m27!4m26!1m5!1m1!1s0xda391a9af7181eb:0xdac48ee60c0589b2!2m2!1d-6.2004383!2d32.4358139!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!1m5!1m1!1s0xda386110a0e5e33:0xf3a4b5965f225e88!2m2!1d-6.3362333!2d32.3631293!1m5!1m1!1s0xda386688c1d15c1:0x98df21e060a29a97!2m2!1d-6.3407096!2d32.345481!1m0!3e03");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation3(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Fquih+Ben+Salah/Facult%C3%A9+des+Lettres+et+des+Sciences+Humaines+Sultan+Moulay+Slimane,+Bd+Ibn+Khaldoun,+Beni-Mellal/32.3387484,-6.346952/@32.3340663,-6.3571766,17z/data=!4m15!4m14!1m5!1m1!1s0xda46b0a6c9b61ff:0x71456f2b90188215!2m2!1d-6.6905458!2d32.5009216!1m5!1m1!1s0xda387abea158417:0x8f89399b852e79ad!2m2!1d-6.369409!2d32.335797!1m0!3e0");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation4(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Foum+El-Ansar/Faculty+Of+Science+And+Technics,+Beni-Mellal/El+Harboulia/Marjane,+Beni-Mellal/ISTA+B%C3%A9ni+Mellal+Ofppt,+Beni-Mellal/Riad+Salam,+Beni-Mellal/@32.3510363,-6.3584896,13z/data=!3m1!4b1!4m38!4m37!1m5!1m1!1s0xda3856776a7ba7b:0x84e4ff5c8d95dae8!2m2!1d-6.2614927!2d32.3735609!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!1m5!1m1!1s0xda386110a0e5e33:0xf3a4b5965f225e88!2m2!1d-6.3362333!2d32.3631293!1m5!1m1!1s0xda386527697cd75:0xa53022af0d653cfc!2m2!1d-6.3610702!2d32.3433308!1m5!1m1!1s0xda387a8c8bb9b73:0xfe669bbacc526b2!2m2!1d-6.3741953!2d32.3362312!1m5!1m1!1s0xda3871028505699:0xa49444a250355b20!2m2!1d-6.3797902!2d32.3182522!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation5(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/ait+tislit,+Beni-Mellal/Barid+Al-Maghrib+(Poste+Beni+Mellal+Sidi+Abdelhalim),+Boulevard+de+2+Mars,+Beni-Mellal/EST+Beni+Mellal,+Boulevard+Mohammed+V,+Beni-Mellal/ISTA+NTIC+BENI+MELLAL,+Beni-Mellal/Faculty+Of+Science+And+Technics,+Beni-Mellal/@32.3435134,-6.3857454,13z/data=!3m1!4b1!4m32!4m31!1m5!1m1!1s0xda387162d8504e3:0x739e841bf51835e5!2m2!1d-6.3786592!2d32.3091003!1m5!1m1!1s0xda3865ec443d667:0x42073044862406a9!2m2!1d-6.3482952!2d32.3363102!1m5!1m1!1s0xda386688c1d15c1:0x98df21e060a29a97!2m2!1d-6.3407096!2d32.345481!1m5!1m1!1s0xda3866c8b3d4ae5:0x60d67c2ebcfa467e!2m2!1d-6.3373967!2d32.3530187!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation6(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Ouled+Ayad/ISTA+B%C3%A9ni+Mellal+Ofppt,+Beni-Mellal/CAF%C3%89+OUMNIA,+Bd+Ibn+Khaldoun,+Beni-Mellal/Cafe+CRYSTAL,+Beni-Mellal/Cafe+Galaxy,+Boulevard+Mohammed+V,+Beni-Mellal/Hotel+Gharnata%D8%8C+Beni-Mellal%E2%80%AD/32.3387526,-6.3468368/@32.3369851,-6.3546906,16z/data=!4m39!4m38!1m5!1m1!1s0xda45f0b80fc2e85:0x194b4fcffd39b80f!2m2!1d-6.797592!2d32.2018894!1m5!1m1!1s0xda387a8c8bb9b73:0xfe669bbacc526b2!2m2!1d-6.3741953!2d32.3362312!1m5!1m1!1s0xda387ab91486359:0xcaea1ade2508f515!2m2!1d-6.368741!2d32.336138!1m5!1m1!1s0xda386552182d543:0x22ae012df1bdc834!2m2!1d-6.3639848!2d32.3342924!1m5!1m1!1s0xda3865564e61155:0x34cbdfe7e3aea184!2m2!1d-6.3625988!2d32.3327864!1m5!1m1!1s0xda38656677b35d1:0x78b888254dfcf54e!2m2!1d-6.3585275!2d32.3342966!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation7(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Sidi+Jaber/Facult%C3%A9+des+Lettres+et+des+Sciences+Humaines+Sultan+Moulay+Slimane,+Bd+Ibn+Khaldoun,+Beni-Mellal/32.3387526,-6.3468368/@32.3363818,-6.3614675,16z/data=!4m15!4m14!1m5!1m1!1s0xda477faf1ee859f:0xf78b7741f7844fbc!2m2!1d-6.4796528!2d32.3794719!1m5!1m1!1s0xda387abea158417:0x8f89399b852e79ad!2m2!1d-6.369409!2d32.335797!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation8(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Souk+El+Had+Des+Bradia/Facult%C3%A9+des+Lettres+et+des+Sciences+Humaines+Sultan+Moulay+Slimane,+Bd+Ibn+Khaldoun,+Beni-Mellal/32.3387526,-6.3468368/@32.3914168,-6.5099342,12z/data=!3m1!4b1!4m15!4m14!1m5!1m1!1s0xda4717869611713:0xea0652e482de8ef!2m2!1d-6.5329775!2d32.4500274!1m5!1m1!1s0xda387abea158417:0x8f89399b852e79ad!2m2!1d-6.369409!2d32.335797!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation9(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/El+Ksiba/Faculty+Of+Science+And+Technics,+Beni-Mellal/EST+Beni+Mellal,+Boulevard+Mohammed+V,+Beni-Mellal/32.3387642,-6.3468185/@32.3393354,-6.3513031,16z/data=!4m21!4m20!1m5!1m1!1s0xda3bf83b937142d:0x11983def3771f500!2m2!1d-6.0329775!2d32.565169!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!1m5!1m1!1s0xda386688c1d15c1:0x98df21e060a29a97!2m2!1d-6.3407096!2d32.345481!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation10(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Faculty+Of+Science+And+Technics,+Beni-Mellal/Gare+Routi%C3%A8re,+Beni-Mellal/amria,+Beni-Mellal/32.3354646,-6.3637199/@32.3352317,-6.3643435,20z/data=!4m21!4m20!1m5!1m1!1s0xda3865768e5fd2f:0xa9155034f61854d4!2m2!1d-6.3201665!2d32.3767388!1m5!1m1!1s0xda3864e1f76da27:0x92cadd99ec735898!2m2!1d-6.358673!2d32.3434831!1m5!1m1!1s0xda38653d920e56b:0xffbe04a9b23a1ca4!2m2!1d-6.3600111!2d32.3394331!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void openLocation11(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Zaouiat+Cheikh/EST+Beni+Mellal,+Boulevard+Mohammed+V,+Beni-Mellal/32.3387526,-6.3468368/@32.4345826,-6.3433636,14z/data=!4m15!4m14!1m5!1m1!1s0xda3c63f07f0c809:0xa8aeb971d3e49d06!2m2!1d-5.9171977!2d32.6438724!1m5!1m1!1s0xda386688c1d15c1:0x98df21e060a29a97!2m2!1d-6.3407096!2d32.345481!1m0!3e0");
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }
}
