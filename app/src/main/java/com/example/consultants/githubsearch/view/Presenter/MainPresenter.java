package com.example.consultants.githubsearch.view.Presenter;

import android.util.Log;

import com.example.consultants.githubsearch.data.RemoteDataSource;
import com.example.consultants.githubsearch.model.Repository;
import com.example.consultants.githubsearch.view.MainContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {


    private static final String TAG = "MainPresenterTag";
    MainContract.View view;


    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;

    }

    @Override
    public void updateList(String strSearch) {

        Call<String> gitHubDataCall = RemoteDataSource.getResponse(strSearch);

        gitHubDataCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> responseRepository) {
                Log.d(TAG, "onResponse: before");
                List<Repository> repositoryList = new ArrayList<>();
                JSONObject response = null;
                try {
                    response = new JSONObject(responseRepository.body());
                    JSONArray items = response.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = (JSONObject) items.get(i);
                        JSONObject owner = (JSONObject) item.getJSONObject("owner");
                        repositoryList.add(new Repository(
                                item.getString("name"),
                                item.getString("description"),
                                item.getInt("stargazers_count"),
                                owner.getString("avatar_url")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: " + repositoryList);
                view.onListUpdated(repositoryList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });


    }
}
