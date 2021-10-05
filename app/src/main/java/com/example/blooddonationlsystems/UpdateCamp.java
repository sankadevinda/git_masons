package com.example.blooddonationlsystems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateCamp extends AppCompatActivity {
    EditText name, date, location;
    Button update;
    DatabaseReference dbRef;
    Camp camp;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_camp);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        key = intent.getStringExtra(CampList.key);

        name = findViewById(R.id.UC_ET_camp_name);
        date = findViewById(R.id.UC_ET_camp_date);
        location = findViewById(R.id.UC_ET_camp_location);

        update = findViewById(R.id.UC_BT_Update_Camp);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Camp").child(key);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                camp = dataSnapshot.getValue(Camp.class);

                name.setText(camp.getName());
                date.setText(camp.getDate());
                location.setText(camp.getLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camp.setName(name.getText().toString().trim());
                camp.setDate(date.getText().toString().trim());
                camp.setLocation(location.getText().toString().trim());

                dbRef.setValue(camp);
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}