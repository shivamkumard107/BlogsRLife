package com.example.shivam.blogsrlife;

public class UploadPost {
    private String mTitle;
    private String mImageUrl;

    public String getmPostDesc() {
        return mPostDesc;
    }

    public void setmPostDesc(String mPostDesc) {
        this.mPostDesc = mPostDesc;
    }

    private String mPostDesc ;
    
    public UploadPost() {
        //empty constructor needed
    }

    public UploadPost(String Title, String imageUrl,String Desc) {
        if (Title.trim().equals("")) {
            Title = "No Title";
        }

        this.mTitle = Title;
        this.mImageUrl = imageUrl;
        this.mPostDesc = Desc;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        mTitle = Title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
