package com.example.LANG.LANG.uye;

import android.content.Intent;
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

import com.example.LANG.LANG.R;


public class UyeOlActivity extends AppCompatActivity {

    EditText username, email, password, conPassword;
    TextInputLayout txtUsername, txtEmail, txtPassword, txtConPassword;
    Button buttonuyeol;
    Cursor cursor;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyeol);

        dataHelper = new DataHelper(this);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        conPassword = (EditText) findViewById(R.id.confirmPassword);

        txtUsername = (TextInputLayout) findViewById(R.id.txtUsername);
        txtEmail = (TextInputLayout) findViewById(R.id.txtEmail);
        txtPassword = (TextInputLayout) findViewById(R.id.txtPassword);
        txtConPassword = (TextInputLayout) findViewById(R.id.txtConfirmPassword);

        buttonuyeol = (Button) findViewById(R.id.buttonRegister);
        TextView txtBacktoLogin = (TextView) findViewById(R.id.txtBacktoLogin);

        buttonuyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = username.getText().toString();
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();


                    SQLiteDatabase db = dataHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT id FROM users WHERE username = '" + UserName + "'",null);
                    cursor.moveToFirst();
                    if (cursor.getCount()>0) {

                        Toast.makeText(getApplicationContext(), "Kullanıcı adı kullanımda",
                                Toast.LENGTH_LONG).show();
                    }else{

                        SQLiteDatabase query = dataHelper.getWritableDatabase();
                        query.execSQL("insert into users(username, email, password) values('" +
                                UserName + "','" +
                                Email + "','" +
                                Password + "')");
                        Toast.makeText(getApplicationContext(), "Üyelik Oluşturuldu",
                                Toast.LENGTH_LONG).show();


                        Intent intent=new Intent(getApplicationContext(), UyeGirisActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        txtBacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public boolean validate() {
        boolean valid = false;

        String UserName = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String ConfirmPassword = conPassword.getText().toString();


        if (UserName.isEmpty()) {
            valid = false;
            txtUsername.setError("Geçerli Kullanıcı adı gir!");
        } else {
            valid = true;
            txtUsername.setError(null);
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            txtEmail.setError("Geçerli mail girin!");
        } else {
            valid = true;
            txtEmail.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            txtPassword.setError("Geçerli Şifre Girin");
        } else if (Password.length() < 4 ) {
            valid = false;
            txtPassword.setError("Şifre Çok Kısa. Min 5");
        } else {
            valid = true;
            txtPassword.setError(null);

        }

        if (ConfirmPassword.isEmpty() || !ConfirmPassword.equals(Password)) {
            valid = false;
            txtConPassword.setError("Şifreler aynı değil!");
        } else {
            valid = true;
            txtConPassword.setError(null);
        }

        return valid;
    }
}
