package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show_skills extends Homepage {
    RecyclerView rv1;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    ArrayList<String> skillsarr;
    Button addskill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_skills);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_skills,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
        rv1=findViewById(R.id.skills_recycler);
        addskill=findViewById(R.id.addskillbtn);
        skillsarr=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users").child(firebaseAuth.getUid()).child("Skills");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=(int) dataSnapshot.getChildrenCount();
                skillsarr.clear();
                for(int i=1;i<count+1;i++){
                    skillsarr.add(dataSnapshot.child("Skill "+i).getValue().toString());
                }
                if(!skillsarr.isEmpty()) {
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rv1.setLayoutManager(mLayoutManager);
                    skilladapter skillsada = new skilladapter(getApplicationContext(),skillsarr);
                    rv1.setAdapter(skillsada);
                }
                    addskill.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(show_skills.this,skills.class));
                        }
                    });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
