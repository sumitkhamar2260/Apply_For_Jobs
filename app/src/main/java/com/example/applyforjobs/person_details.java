package com.example.applyforjobs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class person_details extends Homepage {
    TextInputEditText Fname,Address,Email,Mobnum,Date;
    TextInputLayout namelay,addresslay,statelay,citylay,emaillay,datelay,mobilelay;
    MaterialButton savebtn;
    Spinner Statespinner;
    Calendar c;
    DatePickerDialog dpd;
    AutoCompleteTextView City;
    ArrayAdapter stateadpt,cityadapt;
    Resources res;
    Context cnt;
    String selected_state="Andhra";
    ArrayList<String> cities;
    DrawerLayout drawerLayout;
    RecyclerView rv;
    //NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_person_details,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
    ////////setContentView(R.layout.activity_person_details);
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
        savebtn=(MaterialButton)findViewById(R.id.saveperdetailbtn);
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
        Statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id1) {
                selected_state=Statespinner.getSelectedItem().toString();
                String[] s1=selected_state.split(" ",2);
                int id=res.getIdentifier(s1[0],"array",person_details.this.getPackageName() );
                System.out.println("id and selected state  "+id+"\t"+s1[0]);
                //LayoutInflater
                cityadapt=ArrayAdapter.createFromResource(getApplicationContext(),id,android.R.layout.simple_list_item_1);
                //cityadapt.getAutofillOptions();
                City.setAdapter(cityadapt);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
        Log.d("city is",city);// "city is "+city);
        String date=Date.getText().toString().trim();
        if(fname.isEmpty()){
            namelay.setError("Name can't be empty");
        }
        /*else if{
            namelay.setError(null);
        }*/
        else if(address.isEmpty()){
            namelay.setError("");
            addresslay.setError("Address can't be empty");
        }
        /*else{
            addresslay.setError(null);
        }*/
        else if(selected_state.isEmpty()){
            addresslay.setError("");
            statelay.setError("state can't be empty");
        }
        /*else{
            statelay.setError(null);
        }*/
        else if(city.isEmpty()){
            statelay.setError("");
            citylay.setError("city can't be empty");
        }
        /*else{
            citylay.setError(null);
        }*/
        else if(email.isEmpty()){
            citylay.setError("");
            emaillay.setError("Email can't be empty");
        }
        /*else{
            emaillay.setError(null);
        }*/
        else if(mobnum.isEmpty()){
            emaillay.setError("");
            mobilelay.setError("Mobile Number can't be empty");
        }
        /*else{
            mobilelay.setError(null);
        }*/
        else if(mobnum.length()!= 10){
            mobilelay.setError("");
            mobilelay.setError("Invalid length of Mobile Number");
        }
        /*else{
            mobilelay.setError(null);
        }*/
        else if(date.isEmpty()){
            mobilelay.setError("");
            datelay.setError("Name can't be empty");
        }
        /*else{
            datelay.setError(null);
        }*/
        else
        {
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            //person_details_pojo ob=new person_details_pojo(fname,address,selected_state,city,mobnum,email,date);
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());
            Map<Object,String> personaldetails = new HashMap<>();
            personaldetails.put("Name",fname);
            personaldetails.put("Address",address);
            personaldetails.put("State",selected_state);
            personaldetails.put("City",city);
            personaldetails.put("Mobile No",mobnum);
            personaldetails.put("Email",email);
            personaldetails.put("DOB",date);
            ref.child("Personal details").setValue(personaldetails);
            //ref.child("Personal details").push().setValue(ob);
            Intent intent=new Intent(person_details.this,Homepage.class);
            startActivity(intent);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(person_details.this,Homepage.class));
    }
}



