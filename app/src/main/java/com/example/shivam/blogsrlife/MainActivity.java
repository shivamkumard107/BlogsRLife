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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword ;
    Button btRegister ;
    TextView tvSignIn ;
    ProgressBar pb ;
    FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btRegister = findViewById(R.id.bt_register);
        tvSignIn = findViewById(R.id.tv_signIn);
        pb = findViewById(R.id.pb_register);

        btRegister.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() != null){
            //directly start profile
            finish();
            startActivity(new Intent(getApplicationContext(), PostsActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        if (v==btRegister){
            pb.setVisibility(View.VISIBLE);
            btRegister.setVisibility(View.GONE);
            registerUser();
        }
        if (v== tvSignIn){
            // will open sign in activity
            startActivity(new Intent(MainActivity.this, LogInActivity.class));
        }
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etEmail.setError("Please Enter your Email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError("Please Enter your Password");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    startActivity(new Intent(MainActivity.this, PostsActivity.class));
                }else {
                    Toast.makeText(MainActivity.this, "Could not Register. Please try again", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    btRegister.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
