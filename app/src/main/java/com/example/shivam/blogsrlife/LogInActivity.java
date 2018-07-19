package com.example.shivam.blogsrlife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword ;
    Button btLogin ;
    TextView tvSignUp ;
    ProgressBar pb ;
    private FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btLogin = findViewById(R.id.bt_login);
        tvSignUp = findViewById(R.id.tv_register);
        pb = findViewById(R.id.pb_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            //directly start profile
            finish();
            startActivity(new Intent(getApplicationContext(), PostsActivity.class));
        }

        btLogin.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btLogin){
            pb.setVisibility(View.VISIBLE);
            btLogin.setVisibility(View.GONE);
            userLigin();
        }
        if(v == tvSignUp){
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
        }
    }

    private void userLigin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim() ;

        if (TextUtils.isEmpty(email)){
            etEmail.setError("Please Enter your Email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError("Please Enter your Password");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    pb.setVisibility(View.GONE);
                    //start profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), PostsActivity.class));
                }else {
                    pb.setVisibility(View.GONE);
                    btLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(LogInActivity.this, "User Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
