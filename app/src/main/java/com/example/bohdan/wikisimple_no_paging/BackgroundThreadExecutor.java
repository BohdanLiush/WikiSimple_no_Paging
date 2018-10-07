package com.example.bohdan.wikisimple_no_paging;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundThreadExecutor implements Executor {
    private ExecutorService executorService =
            Executors.newFixedThreadPool(3);

    @Override public void execute(@NonNull Runnable command) {
        executorService.execute(command);
    }
}