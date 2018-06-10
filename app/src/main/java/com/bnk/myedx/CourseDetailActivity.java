package com.bnk.myedx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Intent intent= getIntent();

        ImageView _imv= findViewById(R.id.detail_image);
        TextView name=findViewById(R.id.detail_name);
        TextView des=findViewById(R.id.detail_des);
        TextView about=findViewById(R.id.detail_about);

        name.setText(intent.getStringExtra("name"));
        des.setText(intent.getStringExtra("des"));
        about.setText(intent.getStringExtra("about"));


        if(getIntent().hasExtra("byteArray")) {
            Bitmap _bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            _imv.setImageBitmap(_bitmap);
        }

    }
}
