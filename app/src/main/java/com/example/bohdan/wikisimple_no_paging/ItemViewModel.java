package com.example.bohdan.wikisimple_no_paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PositionalDataSource;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Result>> modelPagedList;
    public LiveData<PageKeyedDataSource<Integer,Result>> positionalDataSourceLiveData;

    public ItemViewModel() {
        MyPositionalDataSourceFactory myPositionalDataSource = new MyPositionalDataSourceFactory();
        positionalDataSourceLiveData = myPositionalDataSource.getMutableLiveData();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MyPagingDatasource.PAGE_SIZE).build();
        
        modelPagedList = (new LivePagedListBuilder(myPositionalDataSource, pagedListConfig))
                .build();
    }
}
