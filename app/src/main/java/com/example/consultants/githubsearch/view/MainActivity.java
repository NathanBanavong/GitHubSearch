package com.example.consultants.githubsearch.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.consultants.githubsearch.R;
import com.example.consultants.githubsearch.model.Repository;
import com.example.consultants.githubsearch.view.Presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    MainPresenter presenter;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindView();
    }

    public void onBindView(){
        etSearch = findViewById(R.id.etSearch);
        presenter = new MainPresenter();
        recyclerView = findViewById(R.id.recycler_view);
        presenter.attachView(this);
        List<Repository> itemList = new ArrayList<>();
        MainAdapter mainActivityAdapter = new MainAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    public void onSearch(View view) {
        presenter.updateList(etSearch.getText().toString());
    }

    @Override
    public void onListUpdated(List<Repository> itemList) {
        MainAdapter mainActivityAdapter = new MainAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    @Override
    public void showError(String e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
