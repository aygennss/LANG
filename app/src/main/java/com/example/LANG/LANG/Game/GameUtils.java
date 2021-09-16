package com.example.LANG.LANG.Game;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.LANG.LANG.Game.GameWordModel;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.Random;

import static android.support.v4.util.Preconditions.checkNotNull;

public class GameUtils {


    public static GameWordModel readJsonFile(Context context, int resourceJson) {
        InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(resourceJson));

        return new Gson().fromJson(inputStreamReader, GameWordModel.class);
    }



    @SuppressLint("RestrictedApi")
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    public static boolean coinFlip(Random rand) {

        return rand.nextBoolean();
    }

    public static int getRandomNumber(Random rand, int range) {

        return rand.nextInt(range);
    }

    public static int getRandomNumber(Random rand, int range, int number) {

        int randInt = rand.nextInt(range);
        while (randInt == number) {
            randInt = rand.nextInt(range);
        }
        return randInt;
    }


}
