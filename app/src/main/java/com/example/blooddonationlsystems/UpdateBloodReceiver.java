package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class UpdateBloodReceiver extends AppCompatActivity {
    EditText National_Id, Name, DOB, Email, Phone, Address, City, Password;
    RadioButton Male, Female;
    Spinner Blood_Type;
    Button Update;
    Receiver receiver;
    DatabaseReference dbRef;
    String key;

    ArrayAdapter<CharSequence> bloodTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_blood_receiver);
        getSupportActionBar().hide();

        Intent intent = getIntent();
      //  key = intent.getStringExtra(ReceiverProfile.key);

        National_Id = findViewById(R.id.RU_ET_national_id);
        Name = findViewById(R.id.RU_ET_name);
        DOB = findViewById(R.id.RU_ET_dob);
        Email = findViewById(R.id.RU_ET_email);
        Phone = findViewById(R.id.RU_ET_phone);
        Address = findViewById(R.id.RU_ET_address);
        City = findViewById(R.id.RU_ET_city);
        Password = findViewById(R.id.RU_ET_password);

        Blood_Type = findViewById(R.id.RU_spinner_blood_type);

        Male = findViewById(R.id.RU_RB_male);
        Female = findViewById(R.id.RU_RB_female);

        bloodTypeAdapter = ArrayAdapter.createFromResource(UpdateBloodReceiver.this, R.array.receiver_profile_blood_type_array, android.R.layout.simple_spinner_item);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Receiver").child(key);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                receiver = dataSnapshot.getValue(Receiver.class);

                National_Id.setText(receiver.getNationalId());
                Name.setText(receiver.getName());
                DOB.setText(receiver.getDob());
                Email.setText(receiver.getEmail());
                if(Male.isChecked()) {
                    receiver.setGender(Male.getText().toString());
                }else if (Female.isChecked()) {
                    receiver.setGender(Female.getText().toString());
                }
                int index = bloodTypeAdapter.getPosition(receiver.getBloodType());
                Blood_Type.setSelection(index);
                Phone.setText(receiver.getPhone());
                Address.setText(receiver.getAddress());
                City.setText(receiver.getCity());
                Password.setText(receiver.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Update = findViewById(R.id.RU_B_update);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receiver.setNationalId(National_Id.getText().toString().trim());
                receiver.setName(Name.getText().toString().trim());
                receiver.setDob(DOB.getText().toString().trim());
                receiver.setBloodType(Blood_Type.getSelectedItem().toString().trim());
                if(Male.isChecked()) {
                    receiver.setGender(Male.getText().toString());
                }else if (Female.isChecked()) {
                    receiver.setGender(Female.getText().toString());
                };
                receiver.setEmail(Email.getText().toString().trim());
                receiver.setPhone(Phone.getText().toString().trim());
                receiver.setAddress(Address.getText().toString().trim());
                receiver.setCity(City.getText().toString().trim());
                receiver.setPassword(Password.getText().toString().trim());
                dbRef.setValue(receiver);
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}