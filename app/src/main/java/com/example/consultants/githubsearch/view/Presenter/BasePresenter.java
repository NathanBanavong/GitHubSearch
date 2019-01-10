package com.example.consultants.githubsearch.view.Presenter;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void removeView();
}
