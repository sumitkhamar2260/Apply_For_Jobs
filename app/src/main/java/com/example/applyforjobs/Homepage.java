package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   ImageView menuimage;
    ProgressDialog pDialog;
  NavigationView navigationView;
  DrawerLayout drawerLayout;
  ActionBarDrawerToggle t;
  RecyclerView rv;
  ArrayList<String> jobtitle,company,location,experience,salary,skills,sector,description,jobid,compid;
  FirebaseAuth firebaseAuth;
  FirebaseDatabase fd;
  DatabaseReference ref,ref1;
  TextView profilename,profileemailadd,actionbartext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        //////////////
        menuimage = findViewById(R.id.actionmenuimage);
        drawerLayout = findViewById(R.id.drawerlay);
        t = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(t);
        t.syncState();
        //actionbartext=findViewById(R.id.actionbartext);
        //actionbartext.setVisibility(View.VISIBLE);
        menuimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //////////////

        rv = findViewById(R.id.rv);
        profilename = findViewById(R.id.profile_name);
        profileemailadd = findViewById(R.id.profile_email);
        fd = FirebaseDatabase.getInstance();
        pDialog = new ProgressDialog(Homepage.this);
        pDialog.setMessage("loading..");
        pDialog.show();
        jobtitle = new ArrayList<>();
        company = new ArrayList<>();
        location = new ArrayList<>();
        experience = new ArrayList<>();
        salary = new ArrayList<>();
        skills = new ArrayList<>();
        sector = new ArrayList<>();
        description = new ArrayList<>();
        jobid = new ArrayList<>();
        compid = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        ref = fd.getReference().child("Companies");
        ref1 = fd.getReference().child("users").child(FirebaseAuth.getInstance().getUid());

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
///////////////////////
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> ob = (Map) child.getValue();
                    String comp = ob.get("companyname");
                    String sect = ob.get("sector");
                    String desc = ob.get("description");
                    for (DataSnapshot child2 : child.getChildren()) {
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
                pDialog.dismiss();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(layoutManager);
                jobrow ob = new jobrow(getApplicationContext(), jobtitle, company, location, experience, salary, sector, description, jobid, compid);
                rv.setAdapter(ob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        ///////////////////////////////
       /* ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  profilename.setText("sumit");
                  profileemailadd.setText("ajdhaj");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
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
           startActivity(new Intent(Homepage.this,show_edu_detail.class));
        }
        if (menuItem.getItemId() == R.id.nav_per) {
            startActivity(new Intent(Homepage.this,show_personal_details.class));
            this.finish();
        }
        if (menuItem.getItemId() == R.id.nav_exp) {
            startActivity(new Intent(Homepage.this, Show_exp_details.class));
            this.finish();
        }
        if (menuItem.getItemId() == R.id.nav_skill) {
            startActivity(new Intent(Homepage.this,show_skills.class));
        }
        if (menuItem.getItemId() == R.id.nav_resume) {
            startActivity(new Intent(Homepage.this,resume_upload.class));
        }
        if (menuItem.getItemId() == R.id.nav_applied) {
            startActivity(new Intent(Homepage.this,show_applied_jobs.class));
        }
        if(menuItem.getItemId()== R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Homepage.this, MainActivity.class));
        }

        //drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
