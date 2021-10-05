package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorProfile extends AppCompatActivity {
    TextView National_id, Name, Blood_type, Gender, DOB, Phone, Email, Address, City;
    Button Update, Delete;
    DatabaseReference dbRef;
    final static String key = "";
    Donor donor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        getSupportActionBar().hide();

        National_id = findViewById(R.id.DP_TV_show_national_id);
        Name = findViewById(R.id.DP_TV_show_name);
        Blood_type = findViewById(R.id.DP_TV_show_blood_type);
        Gender = findViewById(R.id.DP_TV_show_gender);
        DOB = findViewById(R.id.DP_TV_show_dob);
        Phone = findViewById(R.id.DP_TV_show_phone);
        Email = findViewById(R.id.DP_TV_show_email);
        Address = findViewById(R.id.DP_TV_show_address);
        City = findViewById(R.id.DP_TV_show_city);

        Update = findViewById(R.id.DP_B_update);
        Delete = findViewById(R.id.DP_B_delete);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorProfile.this, UpdateBloodDonor.class);
                String id = donor.getKey();
                intent.putExtra(key, id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Redirecting to update page...", Toast.LENGTH_SHORT).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference showRef = FirebaseDatabase.getInstance("https://blood-donation-b2b8a-default-rtdb.firebaseio.com/").getReference().child("Donor");
                showRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("-MkqDLJ_WJsvZLAwNQw5")) {
                            dbRef = FirebaseDatabase.getInstance("https://blood-donation-b2b8a-default-rtdb.firebaseio.com/").getReference().child("Donor").child("-MkqDLJ_WJsvZLAwNQw5");
                            dbRef.removeValue();
                            navigateToRequestBlood();

                            Toast.makeText(getApplicationContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        dbRef = FirebaseDatabase.getInstance("https://blood-donation-b2b8a-default-rtdb.firebaseio.com/").getReference().child("Donor").child("-MkqDLJ_WJsvZLAwNQw5");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    donor = dataSnapshot.getValue(Donor.class);
                    donor.setKey(dataSnapshot.getKey());

                    National_id.setText(donor.getNationalId().toString());
                    Name.setText(donor.getName().toString());
                    DOB.setText(donor.getDob().toString());
                    Gender.setText(donor.getGender().toString());
                    Blood_type.setText(donor.getBloodType().toString());
                    Email.setText(donor.getEmail().toString());
                    Phone.setText(donor.getPhone().toString());
                    Address.setText(donor.getAddress().toString());
                    City.setText(donor.getCity().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Details Not Displayed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void navigateToRequestBlood() {
        Intent intent = new Intent(this, RequestBlood.class);
        startActivity(intent);
    }

}