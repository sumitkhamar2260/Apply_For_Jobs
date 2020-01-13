package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

public class person_education extends AppCompatActivity {
    LinearLayout parentLinearLayout;
    MaterialButton savebtn,addbtn;
    int Flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_education);
      parentLinearLayout=findViewById(R.id.parent_linear_layout);
      savebtn=findViewById(R.id.savebtn);
      addbtn=findViewById(R.id.addbtn);


      addbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //TODO:save details to firebase
          }
      });


    }

}
