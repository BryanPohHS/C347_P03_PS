package com.bryanpoh.c347_p03_ps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddDataActivity extends AppCompatActivity {
    TextView tvWeek;

    Button btnSubmit;

    RadioGroup radioGroup;

    private int WEEK_NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSubmit = findViewById(R.id.buttonSubmit);
        tvWeek = findViewById(R.id.tvWeek);
        radioGroup = findViewById(R.id.radioGroup);

        // Get intent for week number and set to title & textview
        Intent i = getIntent();
        final int weekNumber = i.getIntExtra("newWeekNum", 0);
        setTitle("Add data for Week " + weekNumber);
        tvWeek.setText("Week " + weekNumber);
        WEEK_NUMBER = weekNumber;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the radio button id
                int selectedRadioID = radioGroup.getCheckedRadioButtonId();

                RadioButton radioButton = findViewById(selectedRadioID);

                // Get radiobutton value
                CharSequence gradeValue = radioButton.getText();

                Intent intent = new Intent();
                intent.putExtra("newGrade", gradeValue);
                intent.putExtra("newWeek", WEEK_NUMBER);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}