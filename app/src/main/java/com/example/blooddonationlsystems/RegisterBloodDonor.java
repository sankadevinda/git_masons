package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
//import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.lang.reflect.Field;

public class RegisterBloodDonor extends AppCompatActivity {
    EditText National_Id, Name, DOB, Email, Phone, Address, City, Password;
    RadioButton Male, Female;
    Spinner Blood_Type;
    DatabaseReference dbRef;
    Button Register;
    Donor donor;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_blood_donor);
        getSupportActionBar().hide();

        National_Id = findViewById(R.id.DR_ET_national_id);
        Name = findViewById(R.id.DR_ET_name);
        DOB = findViewById(R.id.DR_ET_dob);
        Email = findViewById(R.id.DR_ET_email);
        Phone = findViewById(R.id.DR_ET_phone);
        Address = findViewById(R.id.DR_ET_Address);
        City = findViewById(R.id.DR_ET_city);
        Password = findViewById(R.id.DR_ET_password);

        Blood_Type = findViewById(R.id.DR_spinner_blood_type);

        Male = findViewById(R.id.DR_RB_male);
        Female = findViewById(R.id.DR_RB_female);

        Register = findViewById(R.id.DR_B_register);

        donor = new Donor();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Donor");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    i = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(National_Id.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter National Id", Toast.LENGTH_LONG).show();



                    }else if (TextUtils.isEmpty(Name.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(DOB.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter DOB", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Email.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Phone.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Address.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(City.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter City", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(RegisterBloodDonor.this, "Enter Password", Toast.LENGTH_LONG).show();
                    }else {
                        donor.setNationalId(National_Id.getText().toString().trim());
                        donor.setName(Name.getText().toString().trim());
                        donor.setDob(DOB.getText().toString().trim());
                        if(Male.isChecked()) {
                            donor.setGender(Male.getText().toString());
                        }else if (Female.isChecked()) {
                            donor.setGender(Female.getText().toString());
                        }
                        donor.setBloodType(Blood_Type.getSelectedItem().toString().trim());
                        donor.setEmail(Email.getText().toString().trim());
                        donor.setPhone(Phone.getText().toString().trim());
                        donor.setAddress(Address.getText().toString().trim());
                        donor.setCity(City.getText().toString().trim());
                        donor.setPassword(Password.getText().toString().trim());

                        dbRef.push().setValue(donor);
                        Toast.makeText(getApplicationContext(),"Registration Successful", Toast.LENGTH_LONG).show();
                        loadDonorProfile();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void loadDonorProfile() {
        //Intent intent = new Intent(this, DonorProfile.class);
        //startActivity(intent);
    }
}