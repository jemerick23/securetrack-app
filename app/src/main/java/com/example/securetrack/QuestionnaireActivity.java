package com.example.securetrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaireActivity extends AppCompatActivity {

    RadioGroup question1Group;
    RadioGroup question2Group;
    RadioGroup question3Group;
    RadioGroup question4Group;
    RadioGroup question5Group;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        question1Group = findViewById(R.id.question1Group);
        question2Group = findViewById(R.id.question2Group);
        question3Group = findViewById(R.id.question3Group);
        question4Group = findViewById(R.id.question4Group);
        question5Group = findViewById(R.id.question5Group);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question1Group.getCheckedRadioButtonId() == -1 ||
                        question2Group.getCheckedRadioButtonId() == -1 ||
                        question3Group.getCheckedRadioButtonId() == -1 ||
                        question4Group.getCheckedRadioButtonId() == -1 ||
                        question5Group.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(QuestionnaireActivity.this,
                            "Please answer all questions.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int riskScore = 0;

                if (question1Group.getCheckedRadioButtonId() == R.id.q1Yes) {
                    riskScore++;
                }

                if (question2Group.getCheckedRadioButtonId() == R.id.q2No) {
                    riskScore++;
                }

                if (question3Group.getCheckedRadioButtonId() == R.id.q3No) {
                    riskScore++;
                }

                if (question4Group.getCheckedRadioButtonId() == R.id.q4Yes) {
                    riskScore++;
                }

                if (question5Group.getCheckedRadioButtonId() == R.id.q5Yes) {
                    riskScore++;
                }

                String riskLevel;

                if (riskScore <= 1) {
                    riskLevel = "Low Risk";
                } else if (riskScore <= 3) {
                    riskLevel = "Medium Risk";
                } else {
                    riskLevel = "High Risk";
                }

                Intent intent = new Intent(QuestionnaireActivity.this, ResultActivity.class);
                intent.putExtra("RISK_LEVEL", riskLevel);
                startActivity(intent);
            }
        });
    }
}