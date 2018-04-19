package com.baiyai.android.fuganghomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String photoUrl = intent.getStringExtra(MainActivity.EXTRA_PHOTO);

        ImageView photoView = findViewById(R.id.me_image);
        Glide.with(this).load(photoUrl).into(photoView);

        TextView textView = findViewById(R.id.me_name);
        textView.setText(name);
    }
}
