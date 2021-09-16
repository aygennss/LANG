package com.example.LANG.LANG.uye;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LANG.LANG.R;

public class giris extends AppCompatActivity {
    ImageView ımage;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        ımage=(ImageView) findViewById(R.id.imageView);
        text=(TextView) findViewById(R.id.textview);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.girisanimasyon);
        text.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(giris.this, UyeGirisActivity.class);
                startActivity(i);
                finish();

            }
        },5000);
    }
}