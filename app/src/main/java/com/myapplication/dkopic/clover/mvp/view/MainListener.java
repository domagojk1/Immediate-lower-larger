package com.myapplication.dkopic.clover.mvp.view;

/**
 * Created by domagoj on 12/19/16.
 */

public interface MainListener {
    void onSuccess(String firstSmaller, String firstLarger);
    void onRangeError();
    void onInputError();
    void onLargerUnavailable();
    void onSmallerUnavailable();
}
