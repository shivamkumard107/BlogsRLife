package com.example.shivam.blogsrlife;

public class Posts {
    String imageUrl;
    String mPostDesc;
    String title;

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    String userPost ;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getmPostDesc() {
        return mPostDesc;
    }

    public Posts() {
    }

    public Posts(String imageUrl, String mPostDesc, String title, String user) {
        this.imageUrl = imageUrl;
        this.mPostDesc = mPostDesc;
        this.title = title;
        this.userPost = user;
    }

    public void setmPostDesc(String mPostDesc) {
        this.mPostDesc = mPostDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
