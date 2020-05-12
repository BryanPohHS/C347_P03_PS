package com.bryanpoh.c347_p03_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("P03 - ClassJournal");

        lv = findViewById(R.id.listViewModule);


        al = new ArrayList<String>();
        al.add("C347");

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedModule = al.get(position);

                Intent i = new Intent(MainActivity.this, ModuleInformationActivity.class);
                i.putExtra("moduleCode", selectedModule);
                startActivity(i);
            }
        });

    }
}
