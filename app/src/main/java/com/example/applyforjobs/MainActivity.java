package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.applyforjobs.R.color.btnText;

public class MainActivity extends AppCompatActivity {

    ImageView imageview1, imageview2;
    TextInputEditText email, password;
    MaterialButton loginbtn;
    TextView sigup;
    ProgressBar progressbar;
    FirebaseAuth firebaseAuth;
    TextInputLayout emaillay,passwordlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview1 = findViewById(R.id.imageView1);
        imageview2 = findViewById(R.id.imageView2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.login);
        sigup = findViewById(R.id.signup);
        progressbar = findViewById(R.id.progressBar);
        firebaseAuth = firebaseAuth.getInstance();
        emaillay = findViewById(R.id.editText1);
        passwordlay = findViewById(R.id.editText2);
        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUserProcess();
            }
        });
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Signup_page.class));
            }
        });
    }
    //caller loginbtn onclick
    void LoginUserProcess() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (Email.isEmpty() && !(Patterns.EMAIL_ADDRESS.matcher(Email).matches()))
        {
            emaillay.setError("Invalid E-mail");
            emaillay.requestFocus();
            return;
        }
        if (Password.isEmpty() && Password.length()<6)
        {
            emaillay.setError(null);
            passwordlay.setError("Invalid Password");
            passwordlay.requestFocus();
            return;
        }
         passwordlay.setError(null);
         loginbtn.setText("");
         loginbtn.setEnabled(false);
         progressbar.setVisibility(View.VISIBLE);
         firebaseAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            emailVerificationStatus();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loginbtn.setText("Login");
                            loginbtn.setEnabled(true);
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
    //caller LoginUserProcess oncomplete
    public void emailVerificationStatus(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();
        if(emailflag){
            startActivity(new Intent(MainActivity.this,Homepage.class));
            loginbtn.setEnabled(true);
            loginbtn.setText("Login");
            progressbar.setVisibility(View.GONE);
            MainActivity.this.finish();
        }
        else{
            Toast.makeText(this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            loginbtn.setText("Login");
            progressbar.setVisibility(View.GONE);

        }
    }
    //caller addTextChangeListner

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}


