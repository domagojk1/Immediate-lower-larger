package com.myapplication.dkopic.clover.mvp.presenter.impl;

import com.myapplication.dkopic.clover.mvp.interactor.MainInteractor;
import com.myapplication.dkopic.clover.mvp.interactor.impl.MainInteractorImpl;
import com.myapplication.dkopic.clover.mvp.presenter.MainPresenter;
import com.myapplication.dkopic.clover.mvp.view.MainListener;
import com.myapplication.dkopic.clover.mvp.view.MainView;

import java.lang.ref.WeakReference;

/**
 * Created by domagoj on 12/19/16.
 */

public class MainPresenterImpl implements MainPresenter, MainListener {
    private WeakReference<MainView> view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = new WeakReference<MainView>(view);
        interactor = new MainInteractorImpl();
    }

    @Override
    public void fetchResults(String input) {
        interactor.fetchResults(input, this);
    }

    @Override
    public void onSuccess(String firstSmaller, String firstLarger) {
        if (view != null) {
            MainView mainView = view.get();

            if (firstSmaller != "") {
                mainView.showSmaller(firstSmaller);
            }
            else {
                mainView.showSmallerUnavailable();
            }

            if (firstLarger != "") {
                mainView.showLarger(firstLarger);
            }
            else {
                mainView.showLargerUnavailable();
            }

            mainView.showResultView();
            mainView.hideError();
        }
    }

    @Override
    public void onRangeError() {
        if (view != null) {
            view.get().showRangeError();
        }
    }

    @Override
    public void onInputError() {
        if (view != null) {
            view.get().showInputError();
        }
    }

    @Override
    public void onLargerUnavailable() {
        if (view != null) {
            view.get().showLargerUnavailable();
        }
    }

    @Override
    public void onSmallerUnavailable() {
        if (view != null) {
            view.get().showSmallerUnavailable();
        }
    }
}
