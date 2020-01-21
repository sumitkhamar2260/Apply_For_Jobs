package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  NavigationView navigationView;
  public DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
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
