package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Show_exp_details extends Homepage{
    RecyclerView exp_rv;
    ImageView add_exp;
    ArrayList<String> jobtitle,company;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_show_all_details);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_all_details,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);




        jobtitle=new ArrayList<>();
        exp_rv=findViewById(R.id.exprv);
        company=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users");
        ref=ref.child(firebaseAuth.getUid());
        ref=ref.child("Experience").child(firebaseAuth.getUid());
         add_exp=findViewById(R.id.add_exp);
         add_exp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(Show_exp_details.this,person_experience.class));
             }
         });
       // jobtitle.clear();
        //company.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              for(DataSnapshot childab:dataSnapshot.getChildren()){
                  Map<String,String> cob =(Map) childab.getValue();
                  String compan=cob.get("Company Name");
                  String jobtitl=cob.get("Job Title");
                  jobtitle.add(jobtitl);
                  company.add(compan);
              }

                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                exp_rv.setLayoutManager(layoutManager);
                card_exp ob1=new card_exp(getApplicationContext(),jobtitle,company);
                exp_rv.setAdapter(ob1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

   }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Show_exp_details.this,Homepage.class));
    }
}
