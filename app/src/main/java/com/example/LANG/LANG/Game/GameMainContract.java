package com.example.LANG.LANG.Game;

public interface GameMainContract {

    interface ViewLayer {
        void setPresenter(GameMainContract.PresenterLayer presenter);

        void updateScreenTime(String time);

        void updateScreenElements(String score, String result, int color, String fallingWord, String matchWord);

        void setAnimations();

        void gameOver();

        void switchScreens();
    }

    interface PresenterLayer {

        void checkResult(boolean guess);

        void fetchNewWords();

        void incorrectResult();

        void correctResult();

        void deactivateState();

        boolean isGameActive();

        String getScoreRoundsString();

        void restartGame();
    }
}
