package com.example.shivam.blogsrlife;

public class Posts {
    String imageUrl, mPostDesc, title;

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

    public Posts(String imageUrl, String mPostDesc, String title) {
        this.imageUrl = imageUrl;
        this.mPostDesc = mPostDesc;
        this.title = title;
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
