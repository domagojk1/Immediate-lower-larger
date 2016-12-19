package com.myapplication.dkopic.clover.mvp.view;

/**
 * Created by domagoj on 12/19/16.
 */

public interface MainView {
    void showResultView();
    void hideResultView();
    void showSmaller(String result);
    void showLarger(String result);
    void showRangeError();
    void showInputError();
    void showLargerUnavailable();
    void showSmallerUnavailable();
    void hideError();
}
