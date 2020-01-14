package com.example.applyforjobs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class person_details extends AppCompatActivity {
    TextInputEditText Fname,Address,Email,Mobnum,date;
    TextInputLayout namelay,addresslay,statelay,citylay,emaillay,datelay;
    MaterialButton savebtn;
    Spinner statespinner;
    Calendar c;
    DatePickerDialog dpd;
    AutoCompleteTextView city;
    ArrayAdapter stateadpt;
    Resources res;
    Context cnt;
    String[] cities;
    ArrayAdapter<String> unit_adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        Fname=findViewById(R.id.fname);
        Address=findViewById(R.id.address);
        Email=findViewById(R.id.email);
        Mobnum=findViewById(R.id.mobnum);
        namelay=findViewById(R.id.namelay);
        addresslay=findViewById(R.id.addresslay);
        statelay=findViewById(R.id.statelay);
        citylay=findViewById(R.id.citylay);
        emaillay=findViewById(R.id.emaillay);
        statespinner=findViewById(R.id.spinner);
        city=findViewById(R.id.cityauto);
        date=findViewById(R.id.date);
        datelay=findViewById(R.id.datelay);
        savebtn=findViewById(R.id.savebtn);
        stateadpt = ArrayAdapter.createFromResource(
                this, R.array.state, android.R.layout.simple_spinner_item);
        stateadpt.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        statespinner.setAdapter(stateadpt);
        //TODO:add adpter to city autocomplete view
        res=getResources();
        cnt=getApplicationContext();

        statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  setspinner(parent,view,position,id);
                 }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
///getting date from user
        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                getdateofbirth();
            }
        });

    }
  //get D.O.B. using calendar
   private void getdateofbirth(){
            c=Calendar.getInstance();
            int day=c.get(Calendar.DAY_OF_MONTH);
            int month=c.get(Calendar.MONTH);
            int year=c.get(Calendar.YEAR);
            dpd = new DatePickerDialog(person_details.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                    date.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                }
            },year,month+1,day);
            dpd.show();
        }

   private void setspinner(AdapterView<?> parent, View view, int position, long id){
       int iden = parent.getId();

       if (iden == R.id.spinner) {
           int flag=0;
           String state1 = parent.getSelectedItem().toString();
           int end=state1.indexOf(" ");
           if(end>0) {
               state1 = state1.substring(0, end);
           }
           res=getResources();
           int id1=res.getIdentifier(state1,"array",cnt.getPackageName());
           cities=res.getStringArray(id1);
           unit_adapter = new ArrayAdapter<String>
                   (parent.getContext(),android.R.layout.simple_dropdown_item_1line,cities);
           for(int i=0;i<cities.length;i++)
           {
               if (cities[i].equals(city.getText().toString())) {
                   flag++;
                   break;
               }
           }
           if(flag==0)
               citylay.setError("Invalid city");
       }
       citylay.setError("Select City");
       city.setAdapter(unit_adapter);
    }
}



