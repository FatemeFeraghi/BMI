package com.fateme.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private Button calculateButton;
    private TextView resultTest;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();

    }

    private void findViews() {
        resultTest = findViewById(R.id.text_view_result);

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchText = inchEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchText);
        int weight = Integer.parseInt(weightText);


        int totalInches = (feet * 12) + inches;

        double heightIMeters = totalInches * 0.0254;

        return weight / (heightIMeters * heightIMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResults = myDecimalFormatter.format(bmi);

        //Convert a double value to String
        //String bmiTextResults= String.valueOf(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiTextResults + " - You are underweight";
        } else if (bmi > 25) {
            fullResultString = bmiTextResults + " - You are overweight";
        } else {
            fullResultString = bmiTextResults + " - You are a healthy weight";
        }

        resultTest.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResults = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()) {
            fullResultString = bmiTextResults + " - As you are under 18, please consult with your doctor for the healthy range for boys.";
        } else if (femaleButton.isChecked()) {
            fullResultString = bmiTextResults + " - As you are under 18, please consult with your doctor for the healthy range for girls.";
        } else {
            fullResultString = bmiTextResults + " - As you are under 18, please consult with your doctor for the healthy range.";
        }
        resultTest.setText(fullResultString);
    }
}