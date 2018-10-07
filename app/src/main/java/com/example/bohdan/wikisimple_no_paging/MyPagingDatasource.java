package com.example.bohdan.wikisimple_no_paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPagingDatasource extends PageKeyedDataSource<Integer,Result> {

    public final String URL = "https://content.guardianapis.com/";

    public Model model;
    List<Model> models = new ArrayList<Model>();
    public String s = "football";
    public Thread loadObjectThread;
    public Call<Model> idsCall;
    public   List<Result> results = new ArrayList<>();
    List<Result> allresults = new ArrayList<>();

    public static final int PAGE_SIZE = 5;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        idsCall = idsApi.idsInfo(s,FIRST_PAGE,PAGE_SIZE);

        try {
            allresults = idsCall.execute().body().getResponse().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        callback.onResult(allresults,null,FIRST_PAGE+1);


        /*idsCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.body() != null) {
                    model = response.body();
                     results = response.body().getResponse().getResults();
                    models.add(model);
                    allresults.addAll(results);
                }

                callback.onResult(allresults,null,FIRST_PAGE+1);
            }


            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });*/

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        idsCall = idsApi.idsInfo(s,params.key,PAGE_SIZE);
        final Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

        try {
            allresults = idsCall.execute().body().getResponse().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        callback.onResult(allresults,adjacentKey);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        idsCall = idsApi.idsInfo(s,FIRST_PAGE,PAGE_SIZE);
        final Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

        idsCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.body() != null) {

                    model = response.body();
                    results = response.body().getResponse().getResults();
                    models.add(model);
                    allresults.addAll(results);
                    callback.onResult(allresults,adjacentKey);

                }

            }


            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        idsCall = idsApi.idsInfo(s,params.key,PAGE_SIZE);
        final Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

        try {
            allresults = idsCall.execute().body().getResponse().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        callback.onResult(allresults,params.key+1);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        idsCall = idsApi.idsInfo(s,FIRST_PAGE,PAGE_SIZE);
        final Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

        idsCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.body() != null) {

                    model = response.body();
                    results = response.body().getResponse().getResults();
                    models.add(model);
                    allresults.addAll(results);
                    callback.onResult(allresults,params.key+1);

                }

            }


            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });*/
    }
}
