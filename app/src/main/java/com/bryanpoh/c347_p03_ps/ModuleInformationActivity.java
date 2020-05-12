package com.bryanpoh.c347_p03_ps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class ModuleInformationActivity extends AppCompatActivity {
    ListView lv;
    TextView tvGrade, tvWeekNum;
    ArrayList<DailyGrade> dailyGrade;
    ArrayAdapter aa;

    Button btnInfo, btnAdd, btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_information);

        Intent i = getIntent();
        final String moduleCode = i.getStringExtra("moduleCode");
        setTitle("Info for " + moduleCode);

        lv = findViewById(R.id.listViewWeekly);
        tvGrade = findViewById(R.id.textViewGrade);
        tvWeekNum = findViewById(R.id.tvWeek);

        // Buttons
        btnInfo = findViewById(R.id.buttonInfo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnEmail = findViewById(R.id.buttonEmail);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to display data
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                // Set the URL to be used.

                if(moduleCode.equals("C347")){
                    rpIntent.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C347"));
                    startActivity(rpIntent);
                } else {
                    rpIntent.setData(Uri.parse("http://www.rp.edu.sg"));
                    startActivity(rpIntent);
                }


            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(ModuleInformationActivity.this, AddDataActivity.class);

                // Loop  through week
                if(aa != null){
                    addIntent.putExtra("newWeekNum", dailyGrade.size()+1);
                }

                startActivityForResult(addIntent, 1);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // The action you want this intent to do;
                // ACTION_SEND is used to indicate sending text
                Intent email = new Intent(Intent.ACTION_SEND);
                // Put essentials like email address, subject & body text
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Test Email from C347");

                String msg = "Hi faci, \n I am Bryan Poh \n Please see my remarks so far, thank you! \n\n ";


                // Loop  through week
                if(aa != null){
                    for(int i = 0; i < dailyGrade.size(); i++){

                        // position = index of row that lv is requesting. Get food at same index
                        DailyGrade currDG = dailyGrade.get(i);

                        msg += currDG.getWeek() + ": DG " + currDG.getGrade() + "\n";
                    }
                    email.putExtra(Intent.EXTRA_TEXT,msg);
                }
                // This MIME type indicates email
                email.setType("message/rfc822");
                // createChooser shows user a list of app that can handle
                // this MIME type, which is, email
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });





        dailyGrade = new ArrayList<DailyGrade>();
        String c302 = "C302";

        if(moduleCode.equals(c302)){
            dailyGrade.add(new DailyGrade("C", "Week 1"));
            dailyGrade.add(new DailyGrade("A", "Week 2"));
        } else {
            dailyGrade.add(new DailyGrade("F", "Week 1"));
            dailyGrade.add(new DailyGrade("F", "Week 2"));
        }


        aa = new DailyGradeAdapter(this, R.layout.weekly_row, dailyGrade);
        lv.setAdapter(aa);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String newWeekStr  = "Week " + String.valueOf(data.getIntExtra("newWeek", 0));
                DailyGrade newDG = new DailyGrade(data.getStringExtra("newGrade"), newWeekStr);
                dailyGrade.add(newDG);
                aa.notifyDataSetChanged();
            }
        }
    }
}
