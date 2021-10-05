package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateBloodDonor extends AppCompatActivity {
    private static final String TAG = "UpdateBloodDonor";

    EditText National_Id, Name, DOB, Email, Phone, Address, City, Password;
    RadioButton Male, Female;
    Spinner Blood_Type;
    Button Update;
    Donor donor;
    DatabaseReference dbRef;
    String key;

    ArrayAdapter<CharSequence> bloodTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_blood_donor);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        //key = intent.getStringExtra(DonorProfile.key);

        National_Id = findViewById(R.id.DU_ET_national_id);
        Name = findViewById(R.id.DU_ET_name);
        DOB = findViewById(R.id.DU_ET_dob);
        Email = findViewById(R.id.DU_ET_email);
        Phone = findViewById(R.id.DU_ET_phone);
        Address = findViewById(R.id.DU_ET_address);
        City = findViewById(R.id.DU_ET_city);
        Password = findViewById(R.id.DU_ET_password);

        Blood_Type = findViewById(R.id.DU_spinner_blood_type);

        Male = findViewById(R.id.DU_RB_male);
        Female = findViewById(R.id.DU_RB_female);



        bloodTypeAdapter = ArrayAdapter.createFromResource(UpdateBloodDonor.this, R.array.donor_profile_blood_type_array, android.R.layout.simple_spinner_item);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Donor").child(key);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donor = dataSnapshot.getValue(Donor.class);

                National_Id.setText(donor.getNationalId());
                Name.setText(donor.getName());
                DOB.setText(donor.getDob());
                Email.setText(donor.getEmail());
//                gender
                int index = bloodTypeAdapter.getPosition(donor.getBloodType());
                Blood_Type.setSelection(index);
                Phone.setText(donor.getPhone());
                Address.setText(donor.getAddress());
                City.setText(donor.getCity());
                Password.setText(donor.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Update = findViewById(R.id.DU_B_update);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donor.setNationalId(National_Id.getText().toString().trim());
                donor.setName(Name.getText().toString().trim());
                donor.setDob(DOB.getText().toString().trim());
                donor.setBloodType(Blood_Type.getSelectedItem().toString().trim());
                donor.setEmail(Email.getText().toString().trim());
                donor.setPhone(Phone.getText().toString().trim());
                donor.setAddress(Address.getText().toString().trim());
                donor.setCity(City.getText().toString().trim());

                dbRef.setValue(donor);
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}