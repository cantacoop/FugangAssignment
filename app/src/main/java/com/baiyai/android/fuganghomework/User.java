package com.baiyai.android.fuganghomework;

public class User {

    private String mName;
    private String mPhoto;

    public User(String name, String photo) {
        this.mName = name;
        this.mPhoto = photo;
    }

    public String getName() {
        return mName;
    }

    public String getPhoto() {
        return mPhoto;
    }
}
