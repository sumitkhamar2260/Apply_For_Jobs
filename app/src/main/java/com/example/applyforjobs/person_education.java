package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class person_education extends Homepage {
    LinearLayout parentLinearLayout;
    MaterialButton savebtn,addbtn;
    AutoCompleteTextView degree,fieldofstudy;
    TextInputLayout degreelay,foslay;
    Resources res;
    DrawerLayout drawerLayout;
    RecyclerView rv;
    int Flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_person_education,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
   rv=findViewById(R.id.rv);
   rv.setVisibility(View.GONE);
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
        degreelay=findViewById(R.id.degreelay);
        foslay=findViewById(R.id.foslay);

      savebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String deg=degree.getText().toString().trim();
              if(deg.equals("Bachelor of Engineering"))
                  deg="BE";
              if(deg.equals("Master of Engineering"))
                  deg="ME";
              String field=fieldofstudy.getText().toString().trim();
              if(deg.isEmpty()){
                    degreelay.setError("Select Degree");
              }
              else if(field.isEmpty()){
                  foslay.setError("Select the field os study");
              }
              else{
                  Map<Object,String > person_education=new HashMap<>();
                  person_education.put("Degree",deg);
                  person_education.put("Field Of Study",field);
                  FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                  DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());

                  ref.child("Education").push().setValue(person_education);
                  startActivity(new Intent(person_education.this,show_edu_detail.class));
              }
              //TODO:save details to firebase
          }
      });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(person_education.this,Homepage.class));
            }
}
