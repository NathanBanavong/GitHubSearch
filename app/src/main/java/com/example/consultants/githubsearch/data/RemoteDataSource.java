package com.example.consultants.githubsearch.data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RemoteDataSource {

    //TODO move to constant folder
    public static final String BASE_URL = "https://api.github.com";
    public static final String QUERY_TAG = "/search/repositories";
    public static final String QUERY_SORT = "stars";
    public static final String QUERY_ORDER = "desc";


    public static Retrofit create() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<String> getResponse(String strSearch) {
        Retrofit retrofit = create();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        return gitHubService.getList(strSearch, QUERY_SORT, QUERY_ORDER);
    }


    public interface GitHubService {

        @GET(QUERY_TAG)
        Call<String> getList(@Query("q") String search, @Query("sort") String stars, @Query("order") String order);
    }

}
