package com.gzy.timer.data;

import com.gzy.timer.data.network.MainDataSource;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    private static volatile MainRepository instance;

    private MainDataSource mDataSource;

    private MainRepository(MainDataSource dataSource) {
        this.mDataSource = dataSource;
    }

    public static MainRepository getInstance(MainDataSource dataSource) {
        if (instance == null) {
            instance = new MainRepository(dataSource);
        }
        return instance;
    }

    public void getMoreData(int count, DataSource.Callback<List<String>> callback) {
        List<String> list = new ArrayList<>();
        for (int i = count; i < count + 5; i++) {
            list.add(String.valueOf(i));
        }
        callback.onDataLoaded(list);
    }
}
