package com.example.shivam.blogsrlife;

public class UploadPost {
    private String mTitle;
    private String mImageUrl;
    private String mPostDesc ;
    private String uid ;

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    private String userPost;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getmPostDesc() {
        return mPostDesc;
    }

    public void setmPostDesc(String mPostDesc) {
        this.mPostDesc = mPostDesc;
    }


    
    public UploadPost(String trim, String imageUrl, String desc, String uid, Object userName) {
        //empty constructor needed
    }

    public UploadPost(String Title, String imageUrl,String Desc, String uid, String userPost) {
        if (Title.trim().equals("")) {
            Title = "No Title";
        }

        this.mTitle = Title;
        this.mImageUrl = imageUrl;
        this.mPostDesc = Desc;
        this.uid = uid ;
        this.userPost = userPost ;
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
