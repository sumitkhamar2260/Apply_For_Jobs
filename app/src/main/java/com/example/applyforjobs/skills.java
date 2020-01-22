package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class skills extends Homepage {
    AutoCompleteTextView skill;
    TextInputLayout skilllay;
     String[] skills;
     MaterialButton maddbtn;
     MaterialCardView mcard;
     LinearLayout parentLinearLayout;
     DrawerLayout drawerLayout;
    TextView tv;
    int count=0;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         LayoutInflater inflater = (LayoutInflater) this
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View contentView = inflater.inflate(R.layout.activity_skills,null,false);
         drawerLayout=findViewById(R.id.drawerlay);
         drawerLayout.addView(contentView, 0);

         skilllay=findViewById(R.id.skilllay);
        parentLinearLayout=findViewById(R.id.parent_linear_layout2);
        skill=findViewById(R.id.autoskill);
        maddbtn=findViewById(R.id.addbtn);

        Resources res= getResources();
        skills=res.getStringArray(R.array.skillset);
        ArrayAdapter<String> skilladapter=
                new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,skills);
        skill.setThreshold(1);
        skill.setAdapter(skilladapter);
        maddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddskill();
            }
        });
    }
  public void onAddskill(){
         for(String ele:skills){
             if(ele.equals(skill.getText().toString())){
                 tv=new TextView(this);
                 mcard=new MaterialCardView(this);
                 mcard.setId(++count);
                 tv.setId(++count);
                 tv.setText(skill.getText());
                 mcard.addView(tv);
                 mcard.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                 parentLinearLayout.addView(mcard);
             }
         }


  }
}
