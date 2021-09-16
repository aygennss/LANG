package com.example.LANG.LANG.Game;



import android.os.CountDownTimer;

import java.util.Random;

public class MainPresenter implements GameMainContract.PresenterLayer {


    private GameMainContract.ViewLayer vLayer;
    private GameWordModel model;
    private GameState gState;
    private int numberRange;
    private UICountDownTimer timer;
    private Random rand;


    MainPresenter(GameMainContract.ViewLayer view, GameState gameState, GameWordModel gameWordModel, UICountDownTimer timer, Random random) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = gameWordModel;
        this.timer = timer;
        this.rand = random;


        if (gameState.getSizeOfArray() > 0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

        this.timer.attach(this.vLayer);
        this.timer.start();

    }


    MainPresenter(GameMainContract.ViewLayer view, GameState gameState, GameWordModel gameWordModel, Random random) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = gameWordModel;
        this.timer = null;
        this.rand = random;


        if (gameState.getSizeOfArray() > 0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

    }


    public void fetchNewWords() {


        boolean matching = GameUtils.coinFlip(rand);


        String compareWord, guessWord;
        int number = GameUtils.getRandomNumber(rand, numberRange);
        guessWord = model.getGuessElement(number);




        if (matching) {

            compareWord = model.getCompareElement(number);
        } else {

            compareWord = model.getCompareElement(GameUtils.getRandomNumber(rand, numberRange, number));


            matching = compareWords(compareWord, model.getCompareElement(number));
        }


        gState.setMatching(matching);


        gState.setWordGuess(guessWord);
        gState.setWordCompare(compareWord);

        vLayer.updateScreenElements(gState.getScoreRoundsString(), gState.getSuccess(), gState.getSuccessColor(), gState.getWordGuess(), gState.getWordCompare());
    }

    public boolean compareWords(String guess, String compare) {
        return(guess.equals(compare));

    }

    public void restartGame() {


        activateState();


        timer.start();

        vLayer.switchScreens();


        clearValues();


        fetchNewWords();
    }

    public void clearValues() {
        gState.setScore(0);
        gState.setRounds(0);
    }

    public void checkResult(boolean userGuess) {
        if (userGuess == gState.isMatching())
            correctResult();
        else
            incorrectResult();
    }

    public void correctResult() {
        gState.setSuccess(true);
        gState.updateScore();
        gState.updateRounds();
        fetchNewWords();
    }

    public void incorrectResult() {
        gState.setSuccess(false);
        gState.updateRounds();
        fetchNewWords();
    }

    public String getScoreRoundsString() {
        return gState.getScoreRoundsString();
    }

    public boolean isGameActive() {
        return gState.isActive();
    }

    public void deactivateState() {
        gState.setActive(false);
    }

    public void activateState() {
        gState.setActive(true);
    }

    public interface UICountDownTimer {
        void attach(GameMainContract.ViewLayer view);

        void start();

        void cancel();
    }

}



class DefaultCountDownTimer implements MainPresenter.UICountDownTimer {

    private CountDownTimer timer;

    public void attach(final GameMainContract.ViewLayer view) {
        timer = new CountDownTimer(46000, 1000) {
            @Override
            public void onTick(long l) {

                view.updateScreenTime(String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {


                view.gameOver();
            }
        };
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

}
