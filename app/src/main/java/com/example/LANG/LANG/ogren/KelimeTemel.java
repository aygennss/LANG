package com.example.LANG.LANG.ogren;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LANG.LANG.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class KelimeTemel extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ImageView imageView1,cloud;
    EditText editText1,editText2;
    Button button2,button1;
    TextToSpeech ttobj;

    int index=0;
    String basic_beg = "Nasıl Söylenir: ";
    String basic_end ;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temel);
        Intent in = getIntent();
        index = in.getIntExtra("index",0);
        Log.i("TAG",Integer.toString(index));

        basic_end = KelimeConstants.Language_Name.language_name[index];

        editText1 = (EditText) findViewById(R.id.editText1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        cloud = (ImageView) findViewById(R.id.cloud);
        editText2 = (EditText) findViewById(R.id.editText2);
        button2 =(Button) findViewById(R.id.button2);
        button1 =(Button) findViewById(R.id.button1);

        ttobj=new TextToSpeech(this,this);


        String total= KelimeConstants.Translations.TEMEL.basics[0] + basic_end + "  " + basic_beg;
        editText1.setText(total, TextView.BufferType.EDITABLE);
        imageView1.setImageResource(KelimeConstants.Basic_pics.basic_pics[0]);
        button2.setBackgroundColor(Color.parseColor("#B0CBC4"));
        button2.setEnabled(false);
    }

    public void next(View v){
        button2.setEnabled(false);
        button1.setText("Öğren!");
        i++;

        try {
            if(i==9)
                button2.setText("Geri");
            else if(i==10) {
                Intent i = new Intent(KelimeTemel.this, Kelimebolum.class);
                startActivity(i);
            }
            String total = KelimeConstants.Translations.TEMEL.basics[i] + basic_end + " "+ basic_beg;
            Log.i("TAG", total);
            editText1.setText(total, TextView.BufferType.EDITABLE);
            imageView1.setImageResource(KelimeConstants.Basic_pics.basic_pics[i]);
            editText2.getText().clear();
            cloud.setImageResource(0);
            button2.setBackgroundColor(Color.parseColor("#B0CBC4"));
            button2.setEnabled(false);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void see(View v){
        String toSpeak = editText2.getText().toString();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,null);
        }
        else{
            ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask picSwitch1 = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        cloud.setImageResource(R.drawable.cloud);
                    }
                });
            }
        };

        TimerTask picSwitch2 = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        editText2.setText(KelimeConstants.Template.BASICS.basics[index][i]);
                        button1.setText("DİNLE");
                      button2.setAlpha(1f);
                        button2.setEnabled(true);
                        button2.setBackgroundColor(Color.parseColor("#00695C"));

                    }
                });
            }
        };

        timer.schedule(picSwitch1, 500);
        timer.schedule(picSwitch2, 900);

    }

    @Override
    public void onInit(final int status) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(status !=TextToSpeech.ERROR){
                    if(index==0)
                        ttobj.setLanguage(Locale.FRENCH);
                    else if(index==1)
                        ttobj.setLanguage(Locale.ENGLISH);
                    else if(index==2)
                        ttobj.setLanguage(Locale.ITALIAN);
                    else if(index==3)
                        ttobj.setLanguage(Locale.GERMAN);
                    else
                        Log.i("TAG","HATA");
                    ttobj.setSpeechRate(1f);
                    ttobj.setPitch(1.2f);
                }
            }
        }).start();

    }
}

