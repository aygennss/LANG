package com.example.LANG.LANG.Game;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.LANG.LANG.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameMainActivity extends AppCompatActivity {


    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.preLayout)
    ConstraintLayout preLayout;

    private GameMainFragmentView mView;

    private GameWordModel model;
    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        model = GameUtils.readJsonFile(this, R.raw.deutsch_b1_verben);


        gameState = new GameState(model.getArraySize(), false);



    }

    @OnClick(R.id.startGame)
    void startGame() {

        gameState.setActive(true);


        coordinatorLayout.setVisibility(View.VISIBLE);
        preLayout.setVisibility(View.GONE);


        mView = (GameMainFragmentView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mView == null) {
            mView = GameMainFragmentView.newInstance();
            GameUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentFrame);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        MainPresenter mPresenter = new MainPresenter(mView, gameState, model, new Random());


    }
}
