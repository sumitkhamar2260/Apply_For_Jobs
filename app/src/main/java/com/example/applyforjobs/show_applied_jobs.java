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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class show_applied_jobs extends Homepage{
       RecyclerView appliedjobsrv;
    ArrayList<String> appliedjobtitle,appliedcompany,appliedscore,appliedjobid,appliedcompanyid,
            appliedexper,appliedsalary,appliedlocation,applieddesc,appliedsector;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_show_applied_jobs);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_show_applied_jobs,null,false);
        drawerLayout=findViewById(R.id.drawerlay);
        drawerLayout.addView(contentView, 0);
        RecyclerView rv=findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
        appliedjobtitle=new ArrayList<>();
        appliedscore=new ArrayList<>();
        appliedcompany=new ArrayList<>();
        appliedjobid=new ArrayList<>();
        appliedcompanyid=new ArrayList<>();
        appliedexper=new ArrayList<>();
        appliedsalary=new ArrayList<>();
        appliedlocation=new ArrayList<>();
        applieddesc=new ArrayList<>();
        appliedsector=new ArrayList<>();
        appliedjobsrv=findViewById(R.id.appliedjobsrv);

        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("users");
        ref=ref.child(firebaseAuth.getUid());
        ref=ref.child("Applied jobs");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childab:dataSnapshot.getChildren()){
                    appliedjobtitle.clear();
                    appliedcompany.clear();
                    appliedjobid.clear();
                    appliedscore.clear();
                    appliedcompanyid.clear();
                    appliedexper.clear();
                    appliedsalary.clear();
                    appliedlocation.clear();
                    applieddesc.clear();
                    appliedsector.clear();
                    Map<String,String> cob =(Map) childab.getValue();
                    appliedcompanyid.add(cob.get("company id"));
                    appliedcompany.add(cob.get("company name"));
                    applieddesc.add(cob.get("desc"));
                    appliedexper.add(cob.get("experience"));
                    appliedjobid.add(cob.get("jobid"));
                    appliedjobtitle.add(cob.get("jobtitle"));
                    appliedlocation.add(cob.get("location"));
                    appliedsalary.add(cob.get("salary"));
                    appliedscore.add(cob.get("score"));
                    appliedsector.add(cob.get("sector"));
                }

                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                appliedjobsrv.setLayoutManager(layoutManager);
                jobrow ob2=new jobrow(getApplicationContext(),appliedjobtitle,appliedcompany,appliedlocation,appliedexper,appliedsalary,appliedsector,applieddesc,appliedjobid,appliedcompanyid);
                appliedjobsrv.setAdapter(ob2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(show_applied_jobs.this,Homepage.class));
    }
}
