package com.bryanpoh.c347_p03_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryanpoh.c347_p03_ps.DailyGrade;
import com.bryanpoh.c347_p03_ps.R;

import java.util.ArrayList;

public class DailyGradeAdapter extends ArrayAdapter<DailyGrade> {

    private ArrayList<DailyGrade> grade;
    private Context context;
    private TextView tvGrade, tvWeek;
    private ImageView imgView;

    public DailyGradeAdapter(Context context, int resource, ArrayList<DailyGrade> objects){
        super(context, resource, objects);

        // Store the food that is passed into this adapter
        grade = objects;
        this.context = context;
    }

    // getView() is the method Listview will call to get the view object everytime listview needs a row
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        // inflate xml into view object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the row.xml as layout for view object
        View rowView = inflater.inflate(R.layout.weekly_row, parent, false);

        // Get the tv object
        tvGrade = rowView.findViewById(R.id.textViewGrade);
        tvWeek = rowView.findViewById(R.id.tvWeek);
        imgView = rowView.findViewById(R.id.imageView);
        imgView.setImageResource(R.drawable.dg);

        // position = index of row that lv is requesting. Get food at same index
        DailyGrade currGrade = grade.get(position);

        // Set tv to show food
        tvGrade.setText(currGrade.getGrade());
        tvWeek.setText(currGrade.getWeek());
        // Return the nicely done up View to the ListView
        return rowView;

    }
}
