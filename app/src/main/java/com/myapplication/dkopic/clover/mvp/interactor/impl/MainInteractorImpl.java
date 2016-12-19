package com.myapplication.dkopic.clover.mvp.interactor.impl;

import com.myapplication.dkopic.clover.constants.Boundaries;
import com.myapplication.dkopic.clover.mvp.interactor.MainInteractor;
import com.myapplication.dkopic.clover.mvp.view.MainListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by domagoj on 12/19/16.
 */

public class MainInteractorImpl implements MainInteractor {
    private static final String NUM_REGEX = "[0-9]+";

    @Override
    public void fetchResults(String input, MainListener listener) {
        if (! input.matches(NUM_REGEX) || input.length() < 2) {
            listener.onInputError();
            return;
        }

        int number = Integer.parseInt(input);
        if (number < Boundaries.MIN || number > Boundaries.MAX) {
            listener.onRangeError();
            return;
        }

        String numberChars[] = input.split("");
        List<Integer> numberList1 = new ArrayList<>();
        List<Integer> numberList2 = new ArrayList<>();

        int num;

        for (int i = 1; i < numberChars.length; i++) {
            num = Integer.parseInt(numberChars[i]);
            numberList1.add(num);
            numberList2.add(num);
        }

        String firstSmaller = fetchSmaller(numberList1);
        String firstLarger = fetchLarger(numberList2);
        listener.onSuccess(firstSmaller, firstLarger);
    }

    @Override
    public String fetchLarger(List<Integer> numberList) {
        int i;

        for (i = numberList.size() - 1; i > 0; i--) {
            if (numberList.get(i) > numberList.get(i-1)) break;
        }

        if (i == 0) return "";
        int pivot = numberList.get(i-1);
        int min = i;

        for (int j = i + 1; j < numberList.size(); j++) {
            if (numberList.get(j) > pivot && numberList.get(j) < numberList.get(min)) {
                min = j;
            }
        }

        int temp = pivot;
        numberList.set(i-1, numberList.get(min));
        numberList.set(min, temp);
        Collections.sort(numberList.subList(i, numberList.size()));

        return getStringFromArray(numberList);
    }

    @Override
    public String fetchSmaller(List<Integer> numberList) {
        int i;

        for (i = numberList.size() - 1; i > 0; i--) {
            if (numberList.get(i) < numberList.get(i-1)) break;
        }

        if (i == 0) return "";
        int pivot = numberList.get(i-1);
        int min = i;

        for (int j = i + 1; j < numberList.size(); j++) {
            if (numberList.get(j) < pivot && numberList.get(j) > numberList.get(min)) {
                min = j;
            }
        }

        int temp = pivot;
        numberList.set(i-1, numberList.get(min));
        numberList.set(min, temp);

        Collections.sort(numberList.subList(i, numberList.size()));
        Collections.reverse(numberList.subList(i, numberList.size()));

        return getStringFromArray(numberList);
    }

    private String getStringFromArray(List<Integer> numberList) {
        StringBuilder builder = new StringBuilder();
        Iterator<Integer> iterator = numberList.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }

        return builder.toString();
    }
}
