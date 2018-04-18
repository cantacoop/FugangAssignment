package com.baiyai.android.fuganghomework;

public class User {

    private String mName;
    private String mPhotoUrl;

    public User(String name, String photoUrl) {
        this.mName = name;
        this.mPhotoUrl = photoUrl;
    }

    public String getName() {
        return mName;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
