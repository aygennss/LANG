package com.example.LANG.LANG.uye;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.LANG.LANG.ogren.KelimeDilsecActivity;
import com.example.LANG.LANG.R;

public class UyeGirisActivity extends AppCompatActivity {

    Cursor cursor;
    EditText username, password;
    TextInputLayout txtUsername, txtPassword;
    Button buttongiris;
    SharedPreferences pref,sharedpreferences;
    DataHelper dataHelper;
    TextView txtRegister;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyegiris);

        dataHelper = new DataHelper(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        txtUsername = (TextInputLayout) findViewById(R.id.txtUsername);
        txtPassword = (TextInputLayout) findViewById(R.id.txtPassword);
        buttongiris = (Button) findViewById(R.id.buttonLogin);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        

        buttongiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {

                    String Username = username.getText().toString();
                    String Password = password.getText().toString();

                    SQLiteDatabase db = dataHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT id FROM users WHERE username = '" + Username + "' AND password ='"+Password+"'",null);
                    cursor.moveToFirst();
                    if (cursor.getCount()>0) {

                        sharedpreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("session", cursor.getString(0).toString());
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Giriş Başarılı",
                                Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(UyeGirisActivity.this, UyesecimActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Hatalı giriş tekrar",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UyeGirisActivity.this, UyeOlActivity.class);
                startActivity(intent);
            }
        });

        pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String session_id =  pref.getString("session",null);
        if(session_id!=null){

            Intent intent=new Intent(UyeGirisActivity.this, KelimeDilsecActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public boolean validate() {
        boolean valid = false;


        String Username = username.getText().toString();
        String Password = password.getText().toString();


        if(Username.isEmpty()) {
            valid = false;
            txtUsername.setError("Kullanıcı adı giriniz!");
        }else {
            valid = true;
            txtUsername.setError(null);
        }


        if (Password.isEmpty()) {
            valid = false;
            txtPassword.setError("ŞİFRE GİRİNİZ!");
        } else if (Password.length() < 4) {
            valid = false;
            txtPassword.setError("ŞİFRE KISA!");
        } else {
            valid = true;
        }
        return valid;
    }

}
