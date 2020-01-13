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
    TextInputEditText Fname,Address,Email,Mobnum,Date;
    TextInputLayout namelay,addresslay,statelay,citylay,emaillay,datelay,mobilelay;
    MaterialButton savebtn;
    Spinner Statespinner;
    Calendar c;
    DatePickerDialog dpd;
    AutoCompleteTextView City;
    ArrayAdapter stateadpt;
    Resources res;
    Context cnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        Fname=findViewById(R.id.fname);
        Address=findViewById(R.id.address);
        Email=findViewById(R.id.email);
        Mobnum=findViewById(R.id.mobnum);
        City=findViewById(R.id.cityauto);
        Date=findViewById(R.id.date);
        namelay=findViewById(R.id.namelay);
        addresslay=findViewById(R.id.addresslay);
        statelay=findViewById(R.id.statelay);
        citylay=findViewById(R.id.citylay);
        emaillay=findViewById(R.id.emaillay);
        mobilelay=findViewById(R.id.mobilelay);
        Statespinner=findViewById(R.id.spinner);
        datelay=findViewById(R.id.datelay);
        savebtn=findViewById(R.id.savebtn);
        stateadpt = ArrayAdapter.createFromResource(
                this, R.array.state, android.R.layout.simple_spinner_item);
        stateadpt.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Statespinner.setAdapter(stateadpt);
        res=getResources();
        cnt=getApplicationContext();

        ///getting date from user
        Date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                getdateofbirth();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDetails();
            }
        });

    }

    private void SaveDetails() {
        String fname=Fname.getText().toString().trim();
        String address=Address.getText().toString().trim();
        String email=Email.getText().toString().trim();
        String mobnum=Mobnum.getText().toString().trim();
        String city=City.getText().toString().trim();
        String state=Statespinner.getSelectedItem().toString().trim();
        String date=Date.getText().toString().trim();
        if(fname.isEmpty()){
            namelay.setError("Name can't be empty");
        }
        if(address.isEmpty()){
            namelay.setError("");
            addresslay.setError("Address can't be empty");
        }
        if(state.isEmpty()){
            addresslay.setError("");
            statelay.setError("state can't be empty");
        }
        if(city.isEmpty()){
            statelay.setError("");
            citylay.setError("city can't be empty");
        }
        if(email.isEmpty()){
            citylay.setError("");
            emaillay.setError("Email can't be empty");
        }
        if(mobnum.isEmpty()){
            emaillay.setError("");
            mobilelay.setError("Mobile Number can't be empty");
        }
        if(mobnum.length()!= 10){
            mobilelay.setError("");
            mobilelay.setError("Invalid length of Mobile Number");
        }
        if(date.isEmpty()){
            mobilelay.setError("");
            datelay.setError("Name can't be empty");
        }

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
                    Date.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                }
            },year,month+1,day);
            dpd.show();
        }


}



