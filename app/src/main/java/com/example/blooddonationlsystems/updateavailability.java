package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class updateavailability extends AppCompatActivity {
    EditText Name, City, Phone;
    CheckBox APos, ANeg, BPos, BNeg, ABPos, ABNeg, OPos, ONeg;
    Button Update;
    DatabaseReference dbRef;
    Hospital hospital;
    String key;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateavailability);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        key = intent.getStringExtra(AvailabilityListSource.key);

        Name = findViewById(R.id.UA_ET_name);
        City = findViewById(R.id.UA_ET_city);
        Phone = findViewById(R.id.UA_ET_phone);

        APos = findViewById(R.id.checkBoxAPosU);
        ANeg = findViewById(R.id.checkBoxANegU);
        BPos = findViewById(R.id.checkBoxBPosU);
        BNeg = findViewById(R.id.checkBoxBNegU);
        ABPos = findViewById(R.id.checkBoxABPosU);
        ABNeg = findViewById(R.id.checkBoxABNegU);
        OPos = findViewById(R.id.checkBoxOPosU);
        ONeg = findViewById(R.id.checkBoxONegU);

        Update = findViewById(R.id.UA_B_update);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Hospital").child(key);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hospital = snapshot.getValue(Hospital.class);

                List<String> Types = (ArrayList<String>) snapshot.child("types").getValue();
                String text = "";
                for (String Type : Types) {
                    text += Types + "";

                    if (Type.equals("A+")) {
                        APos.setChecked(true);
                    }
                    if (Type.equals("A-")) {
                        ANeg.setChecked(true);
                    }
                    if (Type.equals("B+")) {
                        BPos.setChecked(true);
                    }
                    if (Type.equals("B-")) {
                        BNeg.setChecked(true);
                    }if (Type.equals("AB+")) {
                        ABPos.setChecked(true);
                    }
                    if (Type.equals("AB-")) {
                        ABNeg.setChecked(true);
                    }
                    if (Type.equals("O+")) {
                        OPos.setChecked(true);
                    }
                    if (Type.equals("O-")) {
                        ONeg.setChecked(true);
                    }else {
                        Name.setText(hospital.getName());
                        City.setText(hospital.getCity());
                        Phone.setText(hospital.getPhone());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> Type = new ArrayList<>();

                if(APos.isChecked()) {
                    Type.add("A+");
                }
                if(ANeg.isChecked()) {
                    Type.add("A-");
                }
                if(BPos.isChecked()) {
                    Type.add("B+");
                }
                if(BNeg.isChecked()) {
                    Type.add("B-");
                }
                if(ABPos.isChecked()) {
                    Type.add("AB+");
                }
                if(ABNeg.isChecked()) {
                    Type.add("AB-");
                }
                if(OPos.isChecked()) {
                    Type.add("O+");
                }
                if(ONeg.isChecked()) {
                    Type.add("O-");
                }
                hospital.setName(Name.getText().toString().trim());
                hospital.setCity(City.getText().toString().trim());
                hospital.setPhone(Phone.getText().toString().trim());
                hospital.setTypes(Type);

                dbRef.setValue(hospital);
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}