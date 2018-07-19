package com.example.shivam.blogsrlife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class CreatePostActivity extends AppCompatActivity {
    ImageButton ibPostImage;
    EditText etPostTitle, etPostDesc;

    Button btSubmit;
    private Uri myImageUri = null;
    private static final int GALLERY_CODE = 1;
    private ProgressDialog myProgress;

    private StorageReference mStorage ;
    private DatabaseReference myPostDatabase;
    private FirebaseUser myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        myProgress = new ProgressDialog(this);
        mStorage = FirebaseStorage.getInstance().getReference();
        ibPostImage = findViewById(R.id.ib_post_image);
        etPostTitle = findViewById(R.id.et_post_title);
        etPostDesc = findViewById(R.id.et_post_desc);
        btSubmit = findViewById(R.id.submitPost);


        ibPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getGalleryIntent.setType("image/*");
                startActivityForResult(getGalleryIntent, GALLERY_CODE);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSubmit.setVisibility(View.GONE);
                startPosting();

            }
        });

    }

    private void startPosting() {
        myProgress.setMessage("Posting to blog... please wait");
        myProgress.show();
        final String titleVal = etPostTitle.getText().toString().trim();
        final String descVal = etPostDesc.getText().toString().trim();
        if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(descVal) && myImageUri!=null) {
            final StorageReference filepath = mStorage.child("Blog_Images").child(myImageUri.getLastPathSegment());
            filepath.putFile(myImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = myPostDatabase.push();

                    Map<String, String> dataToSave = new HashMap<>();
                    dataToSave.put("title", titleVal);
                    dataToSave.put("desc", descVal);
                    dataToSave.put("image", downloadUrl.toString());
                    dataToSave.put("timeId", String.valueOf(java.lang.System.currentTimeMillis()));
                    dataToSave.put("userId", myUser.getUid());
                    dataToSave.put("username", myUser.getEmail());
                    newPost.setValue(dataToSave);
                    myProgress.dismiss();
                    startActivity(new Intent(CreatePostActivity.this, PostsActivity.class));
                    finish();

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            myImageUri = data.getData();
            ibPostImage.setImageURI(myImageUri);

        }
    }
}
