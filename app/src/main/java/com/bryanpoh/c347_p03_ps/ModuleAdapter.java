package com.bryanpoh.c347_p03_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<Module> {

    private ArrayList<Module> module;
    private Context context;
    private TextView tvModuleName, tvModuleCode;

    public ModuleAdapter(Context context, int resource, ArrayList<Module> objects){
        super(context, resource, objects);

        // Store the food that is passed into this adapter
        module = objects;
        this.context = context;
    }

    // getView() is the method Listview will call to get the view object everytime listview needs a row
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        // inflate xml into view object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the row.xml as layout for view object
        View rowView = inflater.inflate(R.layout.activity_enhancement, parent, false);

        // Get the tv object
        tvModuleName = rowView.findViewById(R.id.tvModuleName);
        tvModuleCode = rowView.findViewById(R.id.tvModuleCode);

        // position = index of row that lv is requesting.
        Module currMod = module.get(position);

        // Set tv to show
        tvModuleName.setText(currMod.getName());
        tvModuleCode.setText(currMod.getCode());
        // Return the nicely done up View to the ListView
        return rowView;

    }
}
