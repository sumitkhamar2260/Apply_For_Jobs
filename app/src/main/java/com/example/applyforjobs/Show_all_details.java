package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Show_all_details extends AppCompatActivity {
    RecyclerView exp_rv;
    ArrayList<String> jobtitle,company;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_details);
        jobtitle=new ArrayList<>();
        exp_rv=findViewById(R.id.exprv);
        company=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users");
        ref.child(firebaseAuth.getUid());
        ref.child("Experience");

       // jobtitle.clear();
        //company.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              for(DataSnapshot childab:dataSnapshot.getChildren()){
                  Map<String,String> cob =(Map) childab.getValue();
                  String compan=cob.get("Company Name");
                  String jobtitl=cob.get("Job Title:");
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
}
