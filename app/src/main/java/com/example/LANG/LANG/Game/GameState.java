package com.example.LANG.LANG.Game;


import android.graphics.Color;


public class GameState {




    static final private String SUCCESS = "Doğru!";
    static final private String FAILURE = "Yanlış";
    static final private String GO = "Başla";


    static final private String COLOR_GREEN = "0B6623";



    private int score, rounds, sizeOfArray;
    private boolean matching, active, success;

    private String wordGuess, wordCompare;

    public GameState(int size, boolean state) {
        this.sizeOfArray = size;
        this.score = 0;
        this.rounds = 0;
        this.matching = true;
        this.success = true;
        this.active = state;
        this.wordGuess = "";
        this.wordCompare = "";
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSuccess() {
        if(rounds==0)
            return GO;
        else if (success)
            return SUCCESS;
        else
            return FAILURE;
    }

    public int getSuccessColor(){
        if(rounds==0)
            return Color.BLACK;
        if(success)
            return 0xff000000 + Integer.parseInt(COLOR_GREEN,16);
        else
            return Color.RED;
    }




    public boolean isMatching() {
        return matching;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }




    public int getSizeOfArray() {
        return sizeOfArray;
    }

    public void setSizeOfArray(int sizeOfArray) {
        this.sizeOfArray = sizeOfArray;
    }

 //kaç kere oynandı

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void updateRounds() {
        this.rounds++;
    }

    //skor

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {
        this.score++;
    }



    public String getScoreRoundsString() {
        return String.valueOf(score) + " / " + String.valueOf(rounds);
    }


    public String getWordGuess() {
        return wordGuess;
    }

    public void setWordGuess(String wordEnglish) {
        this.wordGuess = wordEnglish;
    }



    public String getWordCompare() {
        return wordCompare;
    }

    public void setWordCompare(String wordTurk) {
        this.wordCompare = wordTurk;
    }
}
