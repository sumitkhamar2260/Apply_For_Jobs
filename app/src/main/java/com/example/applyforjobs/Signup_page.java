package com.example.applyforjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_page extends AppCompatActivity {

    ImageView imageview1,imageview2;
    TextInputEditText fullname,phonenumber,email,password;
    MaterialButton signupbtn;
    ProgressBar progressBar;
    TextView login;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        imageview1 = findViewById(R.id.imageView1);
        imageview1 = findViewById(R.id.imageView2);
        fullname = findViewById(R.id.fullname);
        phonenumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signup);
        progressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login);
        firebaseAuth = firebaseAuth.getInstance();
    }
}
