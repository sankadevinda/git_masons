package com.example.blooddonationlsystems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestBlood extends AppCompatActivity {
    Button LoginAsReceiver, LoginAsDonor,hos_1,camp_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);
        getSupportActionBar().hide();

        LoginAsReceiver = findViewById(R.id.RB_Receiver_Login_Btn);
        LoginAsDonor = findViewById(R.id.RB_Donor_Login_Btn);
        hos_1 = findViewById(R.id.hos_1);
        camp_2 = findViewById(R.id.camp_2);

        LoginAsReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(RequestBlood.this, UserLogin.class);
                //startActivity(intent);
            }
        });

        LoginAsDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestBlood.this,DonorLogin.class); //Must view donor list
                startActivity(intent);
            }
        });


        hos_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(RequestBlood.this,hospitalmanagement.class); //Must view donor list
                //startActivity(intent);
            }
        });

        camp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(RequestBlood.this,hospitalmanagement.class); //Must view donor list
                //startActivity(intent);
            }
        });

    }
}