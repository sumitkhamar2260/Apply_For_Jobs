package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

public class person_education extends AppCompatActivity {
    LinearLayout parentLinearLayout;
    MaterialButton savebtn,addbtn;
    AutoCompleteTextView degree,fieldofstudy;
    Resources res;
    int Flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_education);
        res=getResources();
      degree=findViewById(R.id.degreeauto);
      ArrayAdapter<String> jobtitleadpter = new ArrayAdapter<String>
              (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.Degree));
      degree.setAdapter(jobtitleadpter);

      fieldofstudy=findViewById(R.id.fosauto);
        ArrayAdapter<String> fosadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.fieldofstudy));
        fieldofstudy.setAdapter(fosadpter);

        savebtn=findViewById(R.id.saveedubtn);



      savebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //TODO:save details to firebase
          }
      });


    }

}
