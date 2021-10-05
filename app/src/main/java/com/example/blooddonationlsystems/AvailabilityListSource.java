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

import java.util.List;

public class AvailabilityListSource extends AppCompatActivity {
    TextView Name, City, Phone, Types;
    Button Update, Delete;
    DatabaseReference dbRef;
    Hospital hospital = new Hospital();
    final static String key ="";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_list_source);
        getSupportActionBar().hide();

        Name = findViewById(R.id.HL_ET_name);
        City = findViewById(R.id.HL_ET_city);
        Phone = findViewById(R.id.HL_ET_phone);
        Types = findViewById(R.id.HL_ET_types);

        Update = findViewById(R.id.HL_B_update);
        Delete = findViewById(R.id.HL_B_delete);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvailabilityListSource.this, updateavailability.class);
                String id = hospital.getKey();
                intent.putExtra(key, id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Redirecting to Update page... ", Toast.LENGTH_LONG).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference showRef = FirebaseDatabase.getInstance().getReference().child("Hospital");
                showRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("-MkqEMsIo3U170Uahg6W")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Hospital").child("-MkqEMsIo3U170Uahg6W");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                            loadHospitalPage();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Hospital").child("-MkqEMsIo3U170Uahg6W");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    hospital = snapshot.getValue(Hospital.class);
                    hospital.setKey(snapshot.getKey());

                    Name.setText(hospital.getName().toString());
                    City.setText(hospital.getCity().toString());
                    Phone.setText(hospital.getPhone().toString());

                    List<String> types = hospital.getTypes();
                    if (types != null) {
                        String text = "";
                        for (String Types : types) {
                            text += Types + " ";
                        }
                        Types.setText(text);
                    }else {
                        Toast.makeText(getApplicationContext(), "Details Not Displayed", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void loadHospitalPage() {
        Intent intent = new Intent(this, hospitalmanagement.class);
        startActivity(intent);
    }
}