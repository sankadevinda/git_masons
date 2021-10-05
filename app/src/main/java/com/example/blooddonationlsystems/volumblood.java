package com.example.blooddonationlsystems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class volumblood extends AppCompatActivity {
    EditText et_weight;
    EditText et_height;
    RadioButton rd_btn_P;
    RadioButton rd_btn_S;
    Button btn_calculate;
    TextView tv_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumblood);

        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        rd_btn_P = findViewById(R.id.rd_btn_P);
        rd_btn_S = findViewById(R.id.rd_btn_S);
        btn_calculate = findViewById(R.id.btn_calculate);
        tv_answer = findViewById(R.id.tv_answer);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });

    }

    private void calculateAnswer() {
        volumcal cal = new volumcal();
        String value = et_weight.getText().toString();
        String value2 = et_height.getText().toString();

        if(TextUtils.isEmpty(value )) {
            Toast.makeText(this, "Enter the Weight", Toast.LENGTH_SHORT).show();
        }else {
            Float weight = Float.parseFloat(value);
            if(rd_btn_P.isChecked()){
                weight = cal.menBloodVolum(weight);
            } else if(rd_btn_S.isChecked()){
                weight = cal.womenBloodVolum(weight);
            } else {
                Toast.makeText(this, "Select the radio button", Toast.LENGTH_SHORT).show();
                weight = 0.0f;
            }

            tv_answer.setText(new Float(weight).toString());
        }

    }


}
