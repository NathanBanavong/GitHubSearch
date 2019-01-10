package com.example.consultants.githubsearch.view;

import com.example.consultants.githubsearch.model.Repository;
import com.example.consultants.githubsearch.view.Presenter.BasePresenter;
import com.example.consultants.githubsearch.view.Presenter.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {


        void onListUpdated(List<Repository> items);

    }


    interface Presenter extends BasePresenter<View> {

        void updateList(String strSearch);


    }

}
