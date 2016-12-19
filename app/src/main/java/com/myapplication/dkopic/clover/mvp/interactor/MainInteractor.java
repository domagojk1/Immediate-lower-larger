package com.myapplication.dkopic.clover.mvp.interactor;

import com.myapplication.dkopic.clover.mvp.view.MainListener;

import java.util.List;

/**
 * Created by domagoj on 12/19/16.
 */

public interface MainInteractor {
    void fetchResults(String input, MainListener listener);
    String fetchSmaller(List<Integer> numberList);
    String fetchLarger(List<Integer> numberList);
}
