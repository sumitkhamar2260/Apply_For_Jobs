package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  NavigationView navigationView;
  DrawerLayout drawerLayout;
  RecyclerView rv;
  ArrayList<String> jobtitle,company,location,experience,salary,skills,sector,description,jobid,compid;
  FirebaseAuth firebaseAuth;
  FirebaseDatabase fd;
  DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        rv=findViewById(R.id.rv);
        jobtitle=new ArrayList<>();
        company=new ArrayList<>();
        location=new ArrayList<>();
        experience=new ArrayList<>();
        salary=new ArrayList<>();
        skills=new ArrayList<>();
        sector=new ArrayList<>();
        description=new ArrayList<>();
        jobid=new ArrayList<>();
        compid=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference().child("Companies");
        jobtitle.clear();
        company.clear();
        location.clear();
        experience.clear();
        salary.clear();
        skills.clear();
        sector.clear();
        description.clear();
        jobid.clear();
        compid.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    Map<String ,String > ob =(Map) child.getValue();
                    String comp=ob.get("companyname");
                    String sect=ob.get("sector");
                    String desc=ob.get("description");
                    for (DataSnapshot child2:child.getChildren()) {
                        for (DataSnapshot child3 : child2.getChildren()) {
                            Map<String, String> ob2 = (Map) child3.getValue();
                            jobid.add(ob2.get("JobId"));
                            compid.add(ob2.get("Companyid"));
                            company.add(comp);
                            sector.add(sect);
                            description.add(desc);
                            jobtitle.add(ob2.get("JobTitle"));
                            location.add(ob2.get("Location"));
                            experience.add(ob2.get("Experience"));
                            salary.add(ob2.get("Salary"));
                            /*for(DataSnapshot child4:child3.getChildren()){
                                long count=child4.getChildrenCount();
                                System.out.println("childs are"+count);
                                Map<String,String> ob3 = (Map) child4.getValue();
                                for(int i=1;i<=count;i++){
                                    skill.concat(ob3.get("skill"+i));
                                    skill.concat(" ");
                                }
                            }*/
                        }
                    }
                }
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(layoutManager);
                jobrow ob=new jobrow(getApplicationContext(),jobtitle,company,location,experience,salary,sector,description,jobid,compid);
                rv.setAdapter(ob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home) {

            startActivity(new Intent(Homepage.this,Homepage.class));
        }
        if (menuItem.getItemId() == R.id.nav_edu) {
           startActivity(new Intent(Homepage.this,person_education.class));
        }
        if (menuItem.getItemId() == R.id.nav_per) {
            startActivity(new Intent(Homepage.this,person_details.class));
        }
        if (menuItem.getItemId() == R.id.nav_exp) {
            startActivity(new Intent(Homepage.this,person_experience.class));
        }
        if (menuItem.getItemId() == R.id.nav_skill) {
            startActivity(new Intent(Homepage.this,skills.class));
        }
        if(menuItem.getItemId()== R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Homepage.this, MainActivity.class));
        }
        //drawerLayout.closeDrawers();
        return true;
    }
}
