package com.example.LANG.LANG.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LANG.LANG.R;

public class OyunSecimActivity extends AppCompatActivity {
TextView game1;
TextView game2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_secim);

        game1=findViewById(R.id.game1);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game1=new Intent(OyunSecimActivity.this, GameMainActivity.class);
                startActivity(game1);

            }
        });


        game2=findViewById(R.id.game2);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game2=new Intent(OyunSecimActivity.this, Game2Activity.class);
                startActivity(game2);
            }
        });
    }
}