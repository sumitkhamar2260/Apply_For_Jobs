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
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class show_edu_detail extends Homepage {
    DrawerLayout drawerLayout;
    ImageView add_edu;
    ArrayList<String> degree,fos;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    RecyclerView edu_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_edu_detail);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_edu_detail,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);




        degree=new ArrayList<>();
        edu_rv=findViewById(R.id.edurv);
        fos=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users");
        ref=ref.child(firebaseAuth.getUid());
        ref=ref.child("Education");
        add_edu=findViewById(R.id.add_edu);
        add_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(show_edu_detail.this,person_education.class));
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childab:dataSnapshot.getChildren()){
                    Map<String,String> cob =(Map) childab.getValue();
                    String Degree=cob.get("Degree");
                    String FOS=cob.get("Field Of Study");
                    degree.add(Degree);
                    fos.add(FOS);
                }

                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                edu_rv.setLayoutManager(layoutManager);
                card_edu ob2=new card_edu(getApplicationContext(),degree,fos);
                edu_rv.setAdapter(ob2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(show_edu_detail.this,Homepage.class));
    }
}
