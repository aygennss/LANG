package com.example.LANG.LANG.ogren;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LANG.LANG.R;
import com.example.LANG.LANG.uye.DataHelper;
import com.example.LANG.LANG.uye.UyeGirisActivity;
import com.example.LANG.LANG.uye.UyesecimActivity;

public class KelimeDilsecActivity extends AppCompatActivity {
    
    SharedPreferences pref;
    Cursor cursor;
    DataHelper dataHelper;
    TextView txtusername;
    Button buttonLogout;
    Button buttonback;
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String session_id =  pref.getString("session",null);

        dataHelper = new DataHelper(this);
        // Query check id user
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT username FROM users WHERE id = '" + session_id + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            TextView txtusername = (TextView) findViewById(R.id.txtusername);
            txtusername.setText("Merhaba, "+cursor.getString(0).toString()+ "\n  Lütfen Öğrenmek İstediğin Dili Seç");
        }
        img1=(ImageView)findViewById(R.id.franceimg);
        img2=(ImageView)findViewById(R.id.spainimg);
        img3=(ImageView)findViewById(R.id.italyimg);
        img4=(ImageView)findViewById(R.id.germanimg);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent   i=new Intent(KelimeDilsecActivity.this, Kelimebolum.class);
                i.putExtra("index",0);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   i=new Intent(KelimeDilsecActivity.this, Kelimebolum.class);
                i.putExtra("index",1);
                startActivity(i);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   i=new Intent(KelimeDilsecActivity.this, Kelimebolum.class);
                i.putExtra("index",2);
                startActivity(i);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   i=new Intent(KelimeDilsecActivity.this, Kelimebolum.class);
                i.putExtra("index",3);
                startActivity(i);
            }
        });
  buttonback=(Button) findViewById(R.id.buttonback);
  buttonback.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent geri= new Intent(KelimeDilsecActivity.this, UyesecimActivity.class);
          startActivity(geri);
      }
  });

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(getApplicationContext(), UyeGirisActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
