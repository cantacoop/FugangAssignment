package com.baiyai.android.fuganghomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String photo = intent.getStringExtra(MainActivity.EXTRA_PHOTO);

        ImageView photoView = findViewById(R.id.me_image);
        photoView.setImageResource(Utilities.getPhotoResourceId(photo));

        TextView textView = findViewById(R.id.me_name);
        textView.setText(name);
    }
}
