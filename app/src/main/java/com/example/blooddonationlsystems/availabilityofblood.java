package com.example.blooddonationlsystems;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class availabilityofblood extends AppCompatActivity {
    TextView Name, City, Phone;
    CheckBox APos, ANeg, BPos, BNeg, ABPos, ABNeg, OPos, ONeg;
    Button Post,vol_1;
    DatabaseReference dbRef;
    Hospital hospital = new Hospital();
    int i= 0;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabilityofblood);
        getSupportActionBar().hide();

        Name = findViewById(R.id.AB_ET_name);
        City = findViewById(R.id.AB_ET_city);
        Phone = findViewById(R.id.AB_ET_phone);

        APos = findViewById(R.id.checkBoxABPos);
        ANeg = findViewById(R.id.checkBoxANeg);
        BPos = findViewById(R.id.checkBoxBPos);
        BNeg = findViewById(R.id.checkBoxABNeg);
        ABPos = findViewById(R.id.checkBoxABPos);
        ABNeg = findViewById(R.id.checkBoxABNeg);
        OPos = findViewById(R.id.checkBoxOPos);
        ONeg = findViewById(R.id.checkBoxONeg);

        Post = findViewById(R.id.AB_B_post);
        vol_1 = findViewById(R.id.vol_1);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Hospital");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> Types = new ArrayList<>();

                if(APos.isChecked()) {
                    Types.add("A+");
                }
                if(ANeg.isChecked()) {
                    Types.add("A-");
                }
                if(BPos.isChecked()) {
                    Types.add("B+");
                }
                if(BNeg.isChecked()) {
                    Types.add("B-");
                }
                if(ABPos.isChecked()) {
                    Types.add("AB+");
                }
                if(ABNeg.isChecked()) {
                    Types.add("AB-");
                }
                if(OPos.isChecked()) {
                    Types.add("O+");
                }
                if(ONeg.isChecked()) {
                    Types.add("O-");
                }
                try {
                    if ((TextUtils.isEmpty(Name.getText().toString()))){
                        Toast.makeText(availabilityofblood.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    }else if ((TextUtils.isEmpty(City.getText().toString()))){
                        Toast.makeText(availabilityofblood.this, "Enter City", Toast.LENGTH_SHORT).show();
                    }else if ((TextUtils.isEmpty(Phone.getText().toString()))){
                        Toast.makeText(availabilityofblood.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                    }else {
                        hospital.setName(Name.getText().toString().trim());
                        hospital.setCity(City.getText().toString().trim());
                        hospital.setPhone(Phone.getText().toString().trim());
                        hospital.setTypes(Types);

                        dbRef.push().setValue(hospital);
                        Toast.makeText(getApplicationContext(),"Available Blood Posted", Toast.LENGTH_LONG).show();
                        loadHospitalDetails();
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });

        vol_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(availabilityofblood.this,volumblood.class); //Must view donor list
                //startActivity(intent);
            }
        });

    }
    private void loadHospitalDetails() {
        Intent intent = new Intent(this, AvailabilityListSource.class);
        startActivity(intent);
    }
}