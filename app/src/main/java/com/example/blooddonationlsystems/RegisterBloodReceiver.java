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

public class RegisterBloodReceiver extends AppCompatActivity {
    EditText National_Id, Name, DOB, Email, Phone, Address, City, Password;
    RadioButton Male, Female;
    Spinner Blood_Type;
    Button Register;
    DatabaseReference dbRef;
    Receiver receiver;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_blood_receiver);
        getSupportActionBar().hide();

        National_Id = findViewById(R.id.RR_ET_national_id);
        Name = findViewById(R.id.RR_ET_name);
        DOB = findViewById(R.id.RR_ET_dob);
        Email = findViewById(R.id.RR_ET_email);
        Phone = findViewById(R.id.RR_ET_phone);
        Address = findViewById(R.id.RR_ET_address);
        City = findViewById(R.id.RR_ET_city);
        Password = findViewById(R.id.RR_ET_password);

        Blood_Type = findViewById(R.id.RR_spinner_blood_type);

        Male = findViewById(R.id.RR_RB_male);
        Female = findViewById(R.id.RR_RB_female);

        Register = findViewById(R.id.RR_B_register);

        receiver = new Receiver();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Receiver");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i = (int) dataSnapshot.getChildrenCount();
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
                        Toast.makeText(RegisterBloodReceiver.this, "Enter National Id", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Name.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(DOB.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter DOB", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Email.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Phone.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Address.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(City.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter City", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(RegisterBloodReceiver.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }else {
                        receiver.setNationalId(National_Id.getText().toString().trim());
                        receiver.setName(Name.getText().toString().trim());
                        receiver.setDob(DOB.getText().toString().trim());
                        if(Male.isChecked()) {
                            receiver.setGender(Male.getText().toString());
                        }else if (Female.isChecked()) {
                            receiver.setGender(Female.getText().toString());
                        }
                        receiver.setBloodType(Blood_Type.getSelectedItem().toString().trim());
                        receiver.setEmail(Email.getText().toString().trim());
                        receiver.setPhone(Phone.getText().toString().trim());
                        receiver.setAddress(Address.getText().toString().trim());
                        receiver.setCity(City.getText().toString().trim());
                        receiver.setPassword(Password.getText().toString().trim());

                        dbRef.push().setValue(receiver);
                        Toast.makeText(getApplicationContext(),"Registration Successful", Toast.LENGTH_LONG).show();
                        loadReceiverProfile();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void loadReceiverProfile() {
        //Intent intent = new Intent(this, ReceiverProfile.class);
       // startActivity(intent);
    }
}