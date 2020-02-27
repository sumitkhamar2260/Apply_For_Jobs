package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class person_experience extends AppCompatActivity {
    TextInputEditText startdate,enddate;
    int FLAG;
    CheckBox cbox;
    AutoCompleteTextView jobtitle,companylocation,companyname;
    Resources res;
    Spinner jobtype,companytype;
    MaterialButton savebutton;
    TextInputLayout jobtitlelay,jobtypelay,companynamelay,companyloclay,startdatelay,enddatelay,companytypelay;
    TextInputEditText total_exp;
    String sdate,edate,job_title,company_location,company_name,job_type,company_type;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_person_experience,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
*/  setContentView(R.layout.activity_person_experience);
        res=getResources();

        jobtitle=findViewById(R.id.titleauto);

        ArrayAdapter<String> jobtitleadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.job_title));
        jobtitle.setThreshold(1);
        jobtitle.setAdapter(jobtitleadpter);

        jobtype=findViewById(R.id.jobtypespinner);
        companytype = findViewById(R.id.company_type);
        ArrayAdapter<String> jobtypeadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,res.getStringArray(R.array.job_type));
        jobtype.setAdapter(jobtypeadpter);

        ArrayList<String> companyType = new ArrayList<>();
        companyType.add("Product");
        companyType.add("Service");
        ArrayAdapter<String> companytypeadpter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,companyType);
        companytype.setAdapter(companytypeadpter);

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
                else{
                    enddate.setText(null);
                }
            }
        });
        jobtitlelay=findViewById(R.id.jobtitlelay);
        jobtypelay=findViewById(R.id.jobtypelay);
        companynamelay=findViewById(R.id.companynamelay);
        companyloclay=findViewById(R.id.companyloclay);
        companytypelay=findViewById(R.id.companytypelay);
        startdatelay=findViewById(R.id.startlay);
        enddatelay=findViewById(R.id.endlay);
        total_exp=findViewById(R.id.total_exp);
        savebutton=findViewById(R.id.savebtn);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                job_title=jobtitle.getText().toString().trim();
                job_type=jobtype.getSelectedItem().toString().trim();
                company_name=companyname.getText().toString().trim();
                company_type=companytype.getSelectedItem().toString().trim();
                company_location=companylocation.getText().toString().trim();
                sdate=startdate.getText().toString().trim();
                edate=enddate.getText().toString().trim();
                if(job_title.isEmpty()){
                    jobtitlelay.setError("Select job tile");
                }
                else if(job_type.isEmpty()){
                    jobtypelay.setError("Select job type");
                }
                else if(company_type.isEmpty()){
                    companytypelay.setError("Please Select Company Type");
                }
                else if (company_name.isEmpty()){
                    companynamelay.setError("Select company name");
                }
                else if (company_location.isEmpty()){
                    companyloclay.setError("Enter  company name");
                }
                else if(sdate.isEmpty()){
                    startdatelay.setError("Select start date");
                }
                else if(!cbox.isChecked() && edate.isEmpty()){
                    enddatelay.setError("Select end date");
                }
                else if(total_exp.getText().toString().trim().isEmpty()){
                    total_exp.setError("Please Enter Your Total Experience");
                }
                else {
                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());

                    //ref.child(key);
                    Map<Object,String> experience= new HashMap<>();
                    experience.put("Job Title",job_title);
                    experience.put("Job Type",job_type);
                    experience.put("Company Type",company_type);
                    experience.put("Company Name",company_name);
                    experience.put("Company Location",company_location);
                    experience.put("Start Date",sdate);
                    experience.put("End Date",edate);
                    String key = ref.push().getKey();
                    ref.child("Experience").child(firebaseAuth.getCurrentUser().getUid()).child(key).setValue(experience);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());
                    reference.child("Experience").child("Total Experience").setValue(total_exp.getText().toString().trim());
                    startActivity(new Intent(person_experience.this,Homepage.class));
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
    @Override
    public void onBackPressed() {
        startActivity(new Intent(person_experience.this, Show_exp_details.class));
    }
}