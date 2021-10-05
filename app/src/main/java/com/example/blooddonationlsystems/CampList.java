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

public class CampList extends AppCompatActivity {
    TextView Name, Date, Location;
    Button Update, Delete;
    DatabaseReference dbRef;
    final static String key="";
    Camp camp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);
        getSupportActionBar().hide();

        Name = findViewById(R.id.CL_TV_show_name);
        Date = findViewById(R.id.CL_TV_show_date);
        Location = findViewById(R.id.CL_TV_show_location);

        Update = findViewById(R.id.CL_B_update);
        Delete = findViewById(R.id.CL_B_delete);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CampList.this, UpdateCamp.class);
                String id = camp.getKey();
                intent.putExtra(key, id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Redirecting to update page...", Toast.LENGTH_SHORT).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference showRef = FirebaseDatabase.getInstance().getReference().child("Camp");
                showRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("-Ml8PXh8YBZubQIwEMLG")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Camp").child("-Ml8PXh8YBZubQIwEMLG");
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Deleted", Toast.LENGTH_SHORT).show();
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

        dbRef = FirebaseDatabase.getInstance().getReference().child("Camp").child("-Ml8PXh8YBZubQIwEMLG");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    camp = snapshot.getValue(Camp.class);
                    camp.setKey(snapshot.getKey());

                    Name.setText(camp.getName().toString());
                    Date.setText(camp.getDate().toString());
                    Location.setText(camp.getLocation().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "Details Not Displayed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}