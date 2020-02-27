package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class show_personal_details extends Homepage {
   DrawerLayout drawerLayout;
   FirebaseAuth fauth;
   FirebaseDatabase fd;
   DatabaseReference ref;
   String name,dob,city,state,email,phonenumber,address;
   AppCompatTextView nametv,citytv,statetv,addresstv,dobtv,emailtv,mobnotv;
   Button addpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 // setContentView(R.layout.activity_show_personal_details);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_personal_details,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
        nametv=findViewById(R.id.name_show);
        citytv=findViewById(R.id.city_show);
        statetv=findViewById(R.id.state_show);
        emailtv=findViewById(R.id.emai_show);
        dobtv=findViewById(R.id.date_show);
        mobnotv=findViewById(R.id.mobile_show);
        addresstv=findViewById(R.id.address_show);
        addpd=findViewById(R.id.addpersondetails);
        fauth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users").child(fauth.getUid()).child("Personal details");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  Map<String,String> pd=(Map)dataSnapshot.getValue(Map.class);
                name = (String)dataSnapshot.child("Name").getValue();
                address =(String) dataSnapshot.child("Address").getValue();// pd.get("Address");
                dob = (String)dataSnapshot.child("DOB").getValue();//pd.get("DOB");
                city = (String)dataSnapshot.child("City").getValue();//pd.get("City");
                state = (String)dataSnapshot.child("State").getValue();//pd.get("State");
                phonenumber = (String)dataSnapshot.child("Mobile No").getValue();//pd.get("Mobile No");
                email = (String)dataSnapshot.child("Email").getValue();//pd.get("Email");
                System.out.println("Name is "+name);
                if(!(name == null))
                {
                nametv.setText(name);
                addresstv.setText(address);
                dobtv.setText(dob);
                citytv.setText(city);
                statetv.setText(state);
                mobnotv.setText(phonenumber);
                emailtv.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        addpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(show_personal_details.this,person_details.class);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed() {
        startActivity(new Intent(show_personal_details.this,Homepage.class));

    }
}
