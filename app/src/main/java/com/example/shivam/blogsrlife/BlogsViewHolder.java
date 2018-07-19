package com.example.shivam.blogsrlife;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

class BlogsViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public BlogsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setTitle(String title) {
        TextView post_title = (TextView) mView.findViewById(R.id.postTitle);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        TextView post_desc = (TextView) mView.findViewById(R.id.postDesc);
        post_desc.setText(desc);
    }
    public void setUserName(String userName){
        TextView username = (TextView)mView.findViewById(R.id.pName);
        username.setText(userName);
    }
    public void setImage(Context ctx, String image){
        ImageView post_image = (ImageView)mView.findViewById(R.id.postImage);
        post_image.setVisibility(View.VISIBLE);
        Picasso.with(ctx).load(image).into(post_image);
    }
}