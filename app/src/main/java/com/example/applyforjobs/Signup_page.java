package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.applyforjobs.R.color.btnText;
import static com.example.applyforjobs.R.color.error_color_material_dark;

public class Signup_page extends AppCompatActivity {

    ImageView imageview1,imageview2;
    TextInputLayout fullnamelay,phonenumberlay,emaillay,passwordlay;
    TextInputEditText fullname,phonenumber,email,password;
    MaterialButton signupbtn;
    ProgressBar progressBar;
    TextView login;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

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
        fullnamelay = (TextInputLayout) findViewById(R.id.editText1);
        phonenumberlay = (TextInputLayout)findViewById(R.id.editText2);
        emaillay = (TextInputLayout)findViewById(R.id.editText3);
        passwordlay = (TextInputLayout)findViewById(R.id.editText4);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();



        //call to firebase,creating acc,save user data
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              signinUserProcess();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_page.this,MainActivity.class));
                Signup_page.this.finish();
            }
        });
    }

    //back button is pressed
    @Override
     public void onBackPressed(){
        startActivity(new Intent(Signup_page.this, MainActivity.class));
        Signup_page.this.finish();
    }

    //caller signupbtn onclick,firebase calling
     void signinUserProcess()
     {
         final String Email = email.getText().toString().trim();
         final String fullName = fullname.getText().toString().trim();
         final String phoneNumber = phonenumber.getText().toString().trim();
         final String Pass = password.getText().toString().trim();

         //////////
         //validation logic
         if (fullName.isEmpty())
         {
             fullnamelay.setError("Full Name can't be empty");
             fullnamelay.requestFocus();
             return;
         }
         if (phoneNumber.isEmpty())
         {
             fullnamelay.setError(null);
             phonenumberlay.setError("Phone Number can't be empty");
             phonenumberlay.requestFocus();
             return;
         }
         if (phoneNumber.length() != 10)
         {
             phonenumberlay.setError(null);
             phonenumberlay.setError("Invalid Mobile Number Length");
             phonenumberlay.requestFocus();
             return;
         }
         if (Email.isEmpty())
         {
             phonenumberlay.setError(null);
             emaillay.setError("E-mail can't be empty");
             emaillay.requestFocus();
             return;
         }
         if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
         {
             emaillay.setError(null);
             emaillay.setError("Invalid E-mail pattern");
             emaillay.requestFocus();
             return;
         }
         if (Pass.isEmpty())
         {
             emaillay.setError(null);
             passwordlay.setError("Password can't be empty");
             passwordlay.requestFocus();
             return;
         }
         if (Pass.length() < 6)
         {
             passwordlay.setError(null);
             passwordlay.setError("Password must be atleast 6 char. long.");
             passwordlay.requestFocus();
             return;
         }
         passwordlay.setError(null);
         //////////
         signupbtn.setText("");
         signupbtn.setEnabled(false);
         progressBar.setVisibility(View.VISIBLE);
         firebaseAuth.createUserWithEmailAndPassword(Email,Pass)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             //sending verification Email
                             firebaseAuth.getCurrentUser().sendEmailVerification()
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                      Toast.makeText(Signup_page.this,"Verification Email sent to"+Email,Toast.LENGTH_LONG).show();
                                   }
                             });

                             //saving user data in database
                             Map<Object,String> userdata = new HashMap<>();
                             userdata.put("Email",Email);
                             userdata.put("fullname",fullName);
                             userdata.put("phonenumber",phoneNumber);
                             firebaseDatabase.getReference("users")
                                     .child(firebaseAuth.getCurrentUser().getUid()).setValue(userdata);

                             //shift user to MainActivity activity
                             startActivity(new Intent(Signup_page.this, MainActivity.class));
                             progressBar.setVisibility(View.GONE);
                             signupbtn.setText("Signup");
                             Signup_page.this.finish();

                         }else{
                             Toast.makeText(Signup_page.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                             progressBar.setVisibility(View.GONE);
                             signupbtn.setText("Signup");
                             signupbtn.setEnabled(true);
                             signupbtn.setTextColor(getResources().getColorStateList(btnText));
                         }
                     }
                 });
     }

}
