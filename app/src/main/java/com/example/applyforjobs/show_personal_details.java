package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class show_personal_details extends Homepage {
   DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 // setContentView(R.layout.activity_show_personal_details);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_personal_details,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
    }
}
