package com.example.LANG.LANG.uye;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.LANG.LANG.Game.OyunSecimActivity;
import com.example.LANG.LANG.ogren.KelimeDilsecActivity;
import com.example.LANG.LANG.quiz.QuizSecimActivity;
import com.example.LANG.LANG.R;



public class UyesecimActivity extends AppCompatActivity {
    TextView ogren;
    TextView testet;
    TextView dict1;
    TextView minigamee;
    ViewFlipper v_flipper;
    SharedPreferences pref;
    Cursor cursor;
    DataHelper dataHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);



        pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String session_id =  pref.getString("session",null);

        dataHelper = new DataHelper(this);
        // Query check id user
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT username FROM users WHERE id = '" + session_id + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            TextView txtusername = (TextView) findViewById(R.id.txtusername);
            txtusername.setText("Merhaba, "+cursor.getString(0).toString());
        }

        int images[]={R.drawable.sld1, R.drawable.sld2, R.drawable.sld3,R.drawable.sld4,R.drawable.sld5};

v_flipper=findViewById(R.id.v_flipper);

for(int image: images){
    flipperImage(image);

}

minigamee=findViewById(R.id.minigamee);
minigamee.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent minigamee=new Intent(UyesecimActivity.this, OyunSecimActivity.class);
        startActivity(minigamee);
    }
});
testet=findViewById(R.id.testet);
testet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent testet= new Intent(UyesecimActivity.this, QuizSecimActivity.class);
        startActivity(testet);
    }
});

        ogren=findViewById(R.id.ogren);
        ogren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ogren = new Intent(UyesecimActivity.this, KelimeDilsecActivity.class);
                startActivity(ogren);
            }
        });
    }
    public void flipperImage(int image){
        ImageView imageview= new ImageView(this);
        imageview.setBackgroundResource(image);

        v_flipper.addView(imageview);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);



        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }


    }

