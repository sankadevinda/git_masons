package com.example.blooddonationlsystems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class hospitalmanagement extends AppCompatActivity {
    Button CreateCamp, ViewCamp, CreateBlood, ViewBloodTypes;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalmanagement);

        CreateCamp = findViewById(R.id.DR_B_create_camp);
        ViewCamp = findViewById(R.id.DR_B_view_camp);
        CreateBlood = findViewById(R.id.DR_B_create_blood);
        ViewBloodTypes = findViewById(R.id.DR_B_view_blood_type);

        CreateCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(hospitalmanagement.this, com.example.blooddonationsystem.CreateCamp.class);
                //startActivity(intent);
            }
        });

        ViewCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(hospitalmanagement.this, CampList.class);
                //startActivity(intent);
            }
        });

        CreateBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(hospitalmanagement.this, availabilityofblood.class);
               // startActivity(intent);
            }
        });

        ViewBloodTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(hospitalmanagement.this, AvailabilityListSource.class);
               // startActivity(intent);
            }
        });
    }
}