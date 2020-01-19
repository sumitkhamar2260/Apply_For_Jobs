package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Field;
import java.util.Calendar;

public class person_experience extends AppCompatActivity {
    TextInputEditText startdate,enddate;
    int FLAG;
    CheckBox cbox;
    AutoCompleteTextView jobtitle,companylocation,companyname;
    Resources res;
    Spinner jobtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_experience);
        res=getResources();

        jobtitle=findViewById(R.id.titleauto);

        ArrayAdapter<String> jobtitleadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.job_title));
        jobtitle.setThreshold(1);
        jobtitle.setAdapter(jobtitleadpter);

        jobtype=findViewById(R.id.jobtypespinner);

        ArrayAdapter<String> jobtypeadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.job_type));
        jobtype.setAdapter(jobtypeadpter);

        companyname=findViewById(R.id.companynameauto);

        ArrayAdapter<String> companynameadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.company_name));
        companyname.setThreshold(1);
        companyname.setAdapter(companynameadpter);

        companylocation=findViewById(R.id.companylocauto);

        ArrayAdapter<String> companylocadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.cities));
        companylocation.setThreshold(1);
        companylocation.setAdapter(companylocadpter);

        cbox=findViewById(R.id.mcheck);
        startdate = findViewById(R.id.startdate);
        enddate= findViewById(R.id.enddate);


        startdate.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {
                FLAG=0;
                createDialogWithoutDateField(FLAG);
            }
        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cbox.isChecked()){
                FLAG=1;
                createDialogWithoutDateField(FLAG); }
            }
        });
        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(cbox.isChecked()){
                   enddate.setText("Present");
                }
            }
        });

    }



    private void createDialogWithoutDateField(final int Flag) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                       if(Flag ==0) {
                           startdate.setText((monthOfYear + 1) + "-" + year);
                       }else if(Flag==1){
                           enddate.setText((monthOfYear + 1) + "-" + year);
                       }

                    }
                }, mYear, mMonth, mDay);
        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.show();
    }
}