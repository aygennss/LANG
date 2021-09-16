package com.example.LANG.LANG.Game;



import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.LANG.LANG.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v4.util.Preconditions.checkNotNull;

public class GameMainFragmentView extends Fragment implements Animation.AnimationListener, GameMainContract.ViewLayer {


    private GameMainContract.PresenterLayer mPresenter;

    // Butterknife ile idleri tanımlama
    @BindView(R.id.timerTextView)
    TextView timerTextView;
    @BindView(R.id.scoreTextView)
    TextView scoreTextView;
    @BindView(R.id.resultTextView)
    TextView resultTextView;
    @BindView(R.id.fallingTextView)
    TextView fallingTextView;
    @BindView(R.id.matchTextView)
    TextView matchTextView;
    @BindView(R.id.layoutBottom)
    ConstraintLayout constraintLayoutBottom;
    @BindView(R.id.linearLayoutEndScreen)
    LinearLayout linearLayoutEndScreen;
    @BindView(R.id.scoreFinalTextView)
    TextView scoreFinalTextView;


    Animation animationFall, animationReset;


    public GameMainFragmentView() {

    }

    public static GameMainFragmentView newInstance() {
        return new GameMainFragmentView();
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(@NonNull GameMainContract.PresenterLayer presenter) {
        this.mPresenter = checkNotNull(presenter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_view, container, false);

        //Animasyon Ayarlama ve başlama
        setAnimations();

        ButterKnife.bind(this, view);

        fallingTextView.startAnimation(animationFall);


        mPresenter.fetchNewWords();

        return view;
    }

    @OnClick(R.id.wrongButton)
    void onClickWrongButton() {
        mPresenter.checkResult(false);

        fallingTextView.startAnimation(animationReset);
    }

    @OnClick(R.id.correctButton)
    void onClickCorrectButton() {
        mPresenter.checkResult(true);

        fallingTextView.startAnimation(animationReset);
    }

    @OnClick(R.id.restartGameButton)
    void restartGameButtonPressed() {
        mPresenter.restartGame();
    }

    @Override
    public void updateScreenTime(String s) {
        timerTextView.setText(s);
    }

    @Override
    public void updateScreenElements(String score, String result, int color, String fallingWord, String matchWord) {
        if (mPresenter.isGameActive()) {
            //Oyun devam ediyosa değerleri güncelle
            fallingTextView.setText(fallingWord);
            matchTextView.setText(matchWord);

            //current score
            scoreTextView.setText(score);

            //current result
            resultTextView.setText(result);
            resultTextView.setTextColor(color);

        }
    }


    public void gameOver() {

        //oyunu bitir
        mPresenter.deactivateState();


        updateScreenTime(getResources().getString(R.string.zero));

        switchScreens();

        scoreFinalTextView.setText(mPresenter.getScoreRoundsString());
    }



    public void switchScreens() {

        if (mPresenter.isGameActive()) {

            linearLayoutEndScreen.setVisibility(View.GONE);


            fallingTextView.startAnimation(animationFall);


            fallingTextView.setVisibility(View.VISIBLE);
            constraintLayoutBottom.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.VISIBLE);
        } else {

            fallingTextView.clearAnimation();
            fallingTextView.setVisibility(View.GONE);
            constraintLayoutBottom.setVisibility(View.GONE);
            resultTextView.setVisibility(View.GONE);


            linearLayoutEndScreen.setVisibility(View.VISIBLE);
        }
    }



    public void setAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animationFall = AnimationUtils.loadAnimation(Objects.requireNonNull(getActivity()).getApplicationContext(), R.anim.falling);
        }
        animationFall.setAnimationListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animationReset = AnimationUtils.loadAnimation(Objects.requireNonNull(getActivity()).getApplicationContext(), R.anim.reset);
        }
        animationReset.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }


    @Override
    public void onAnimationEnd(Animation animation) {


        if (mPresenter.isGameActive())
            if (animation == animationFall) {

                fallingTextView.startAnimation(animationReset);


                mPresenter.incorrectResult();


            } else if (animation == animationReset) {

                fallingTextView.startAnimation(animationFall);
            }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
