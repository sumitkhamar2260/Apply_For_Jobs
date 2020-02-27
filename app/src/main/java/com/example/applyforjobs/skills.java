package com.example.applyforjobs;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class skills extends Homepage {
    AutoCompleteTextView skill;
    TextInputLayout skilllay;
     String[] skills;
     MaterialButton maddbtn;
     ListView sklv;
     DrawerLayout drawerLayout;
    ArrayList<String> skillarrayList;
    ArrayAdapter<String> skilladapter,skillautoadapter;
    RecyclerView rv;
    skilllistadapter adapter;
    TextView tv;
     Button b1;
     FirebaseAuth firebaseAuth;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View contentView = inflater.inflate(R.layout.activity_skills,null,false);
         drawerLayout=findViewById(R.id.drawerlay);
         drawerLayout.addView(contentView, 0);
         rv=findViewById(R.id.rv);
         rv.setVisibility(View.GONE);
         b1=findViewById(R.id.submitskill);
         Resources res= getResources();
         firebaseAuth=FirebaseAuth.getInstance();
        ///////////////////////////
         skilllay=findViewById(R.id.skilllay);
        skill=findViewById(R.id.autoskill);
        maddbtn=findViewById(R.id.addbtn);
        sklv=findViewById(R.id.skilllistview);
         ///Listview for added skill
         skillarrayList = new ArrayList<String>();
        // skilladapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, skillarrayList);
          adapter = new skilllistadapter(skillarrayList, this);
         sklv.setAdapter(adapter);
         //
         skills=res.getStringArray(R.array.skillset);
        skill.setThreshold(1);
        skillautoadapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,res.getStringArray(R.array.skillset));
        skill.setAdapter( skillautoadapter);
        maddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddskill();
            }
        });
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getUid()).child("Skills");
               int i=1;

                 for(String rs: skillarrayList)
                 {

                     rootRef.child("Skill "+i).setValue(rs);
                     i++;
                 }
                 startActivity(new Intent(skills.this,Homepage.class));
             }
         });
     }
  public void onAddskill(){
         for(String ele:skills){
             if(ele.equals(skill.getText().toString()) && !skillarrayList.contains(skill.getText().toString())){
                 skillarrayList.add(skill.getText().toString());
                  adapter.notifyDataSetChanged();
                 skill.setText("");

             }
         }

 }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(skills.this,Homepage.class));
    }
}
