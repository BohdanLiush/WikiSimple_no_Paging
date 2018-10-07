package com.example.bohdan.wikisimple_no_paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class MyPositionalDataSourceFactory extends DataSource.Factory {

    public MutableLiveData<PageKeyedDataSource<Integer,Result>> mutableLiveData = new MutableLiveData();


    @Override
    public DataSource <Integer,Result> create() {

        MyPagingDatasource myPositionalDataSource = new MyPagingDatasource();
        mutableLiveData.postValue(myPositionalDataSource);


        return myPositionalDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer,Result>> getMutableLiveData() {
        return mutableLiveData;
    }
}