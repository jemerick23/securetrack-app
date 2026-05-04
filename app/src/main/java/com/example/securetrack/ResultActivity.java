package com.example.securetrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvRiskLevel;
    TextView tvRecommendation;
    Button btnRestart;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvRiskLevel = findViewById(R.id.tvRiskLevel);
        tvRecommendation = findViewById(R.id.tvRecommendation);
        btnRestart = findViewById(R.id.btnRestart);
        btnShare = findViewById(R.id.btnShare);

        String risk = getIntent().getStringExtra("RISK_LEVEL");

        if (risk == null) {
            risk = "Unknown";
        }

        String recommendation;

        if (risk.equals("Low Risk")) {
            recommendation = "Great job! Your security habits are strong.";
            tvRiskLevel.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        } else if (risk.equals("Medium Risk")) {
            recommendation = "Consider improving your security practices.";
            tvRiskLevel.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        } else if (risk.equals("High Risk")) {
            recommendation = "Your security is at risk. Take action immediately.";
            tvRiskLevel.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            recommendation = "Unable to determine risk. Please try again.";
            tvRiskLevel.setTextColor(getResources().getColor(android.R.color.white));
        }

        tvRiskLevel.setText("Your Risk Level: " + risk);
        tvRecommendation.setText(recommendation);

        final String shareMessage = "My SecureTrack result:\n\n"
                + "Risk Level: " + risk + "\n"
                + recommendation;

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                sendIntent.setType("text/plain");

                startActivity(Intent.createChooser(sendIntent, "Share via"));
            }
        });
    }
}