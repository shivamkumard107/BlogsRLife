package com.example.shivam.blogsrlife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PostsActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    //    TextView tvText;
//    Button btLogOut;

    // changes to verify github conotributions

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase ;
    SwipeRefreshLayout mPostRefresh;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        mPostRefresh = (SwipeRefreshLayout) findViewById(R.id.pullRefreshHome);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("uploads");
        mBlogList = (RecyclerView) findViewById(R.id.rv_posts);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

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

        mPostRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onStart(); // your code
                mPostRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onStart() {

        FirebaseRecyclerAdapter<Posts, BlogsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Posts, BlogsViewHolder>(
                Posts.class,
                R.layout.layout_post,
                BlogsViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogsViewHolder viewHolder, Posts model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getmPostDesc());
                viewHolder.setImage(getApplicationContext(), model.getImageUrl());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_post) {
            startActivity(new Intent(PostsActivity.this, CreatePostActivity.class));
        }
        if (item.getItemId() == R.id.action_settings) {
            //open settings
            Intent intent = new Intent(PostsActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_signout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(PostsActivity.this, LogInActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
