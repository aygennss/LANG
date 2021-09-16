package com.example.LANG.LANG.Game;



import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.example.LANG.LANG.Game.BoardFragment;
import com.example.LANG.LANG.R;

public class Game2Activity extends AppCompatActivity {

    private BoardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);

        if (size.x>size.y)
            getSupportActionBar().hide();



        FragmentManager fm= getSupportFragmentManager();
        fragment = (BoardFragment) fm.findFragmentByTag("etiket");

        if (fragment==null){
            //Toast.makeText(this, "Sıfırdan oluştu", Toast.LENGTH_SHORT).show();
            fragment=new BoardFragment();
            fm.beginTransaction().add(R.id.container,fragment,"etiket").commit();
        }


    }
}
