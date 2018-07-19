package com.example.shivam.blogsrlife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PostsActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
//    TextView tvText;
//    Button btLogOut;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(PostsActivity.this, LogInActivity.class));
        }
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        tvText = findViewById(R.id.tv_userEmail);
//        if (user != null) {
//            tvText.setText("Welcome " + user.getEmail());
//        }
//        btLogOut = findViewById(R.id.bt_logOut);

//        btLogOut.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_post){
            startActivity(new Intent(PostsActivity.this, CreatePostActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
//        if (v == btLogOut){
//            firebaseAuth.signOut();
//            finish();
//            startActivity(new Intent(PostsActivity.this, LogInActivity.class));
//        }


    }
}
