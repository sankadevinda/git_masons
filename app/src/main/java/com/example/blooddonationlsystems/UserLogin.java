package com.example.blooddonationlsystems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {
    EditText Username, Password;
    Button Login, Register,age_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().hide();

        Username= findViewById(R.id.UL_ET_username);
        Password= findViewById(R.id.UL_ET_password);

        Login= findViewById(R.id.UL_B_login);
        Register= findViewById(R.id.UL_B_Register);
        age_1= findViewById(R.id.age_1);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(Username.getText().toString())) {
                        Toast.makeText(UserLogin.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(UserLogin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }else if(Username.getText().length() != 0 && Password.getText().length() != 0) {
                        loadHomePage();   //Loads the homepage when successfully logged in
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(UserLogin.this, RegisterBloodReceiver.class);
                //startActivity(intent);
            }
        });


        age_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(UserLogin.this,agecalculation.class);
                //startActivity(intent);
            }
        });


    }

    private void loadHomePage() {
        //Intent intent = new Intent(this, ReceiverProfile.class);
        //startActivity(intent);
    }
}