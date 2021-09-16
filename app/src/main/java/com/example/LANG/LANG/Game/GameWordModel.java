package com.example.LANG.LANG.Game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameWordModel {



    @SerializedName("list")
    private ArrayList<WordPair> list;

    public String getGuessElement(int position){
        return list.get(position).textGuess;
    }

    public String getCompareElement(int position){
        return list.get(position).textCompare;
    }

    public ArrayList<WordPair> getList() {
        return list;
    }

    public int getArraySize(){
        return list.size();
    }


    static private class WordPair {
        @SerializedName("text_eng")
        private String textGuess;
        @SerializedName("text_tr")
        private String textCompare;
    }
}