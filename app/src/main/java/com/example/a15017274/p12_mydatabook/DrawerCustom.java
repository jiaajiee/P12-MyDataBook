package com.example.a15017274.p12_mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017274 on 10/8/2017.
 */

public class DrawerCustom extends ArrayAdapter<String> {
    private ArrayList<String> drawer;
    private Context context;
    private TextView tvName;
    private ImageView ivIcon;

    public DrawerCustom(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        drawer = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_drawer, parent, false);

        // Get the TextView object
        tvName = (TextView) rowView.findViewById(R.id.tvName);
        // Get the ImageView object
        ivIcon = (ImageView) rowView.findViewById(R.id.ivIcon);


        String currentHoliday = drawer.get(position);
        tvName.setText(currentHoliday);

        if (currentHoliday.equalsIgnoreCase("Bio")) {
            ivIcon.setImageResource(android.R.drawable.ic_dialog_info);
        } else if (currentHoliday.equalsIgnoreCase("Vaccination")) {
            ivIcon.setImageResource(android.R.drawable.ic_menu_edit);
        } else if (currentHoliday.equalsIgnoreCase("Anniversary")) {
            ivIcon.setImageResource(android.R.drawable.ic_menu_today);
        } else if (currentHoliday.equalsIgnoreCase("About Us")) {
            ivIcon.setImageResource(android.R.drawable.star_big_on);
        }
        return rowView;
    }

}
