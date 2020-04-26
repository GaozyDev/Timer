package com.gzy.timer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gzy.timer.data.DataSource;
import com.gzy.timer.data.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel implements DataSource.Callback<List<String>> {

    private MainRepository mMainRepository;

    private MutableLiveData<List<String>> stringList = new MutableLiveData<>();

    MainViewModel(MainRepository mainRepository) {
        this.mMainRepository = mainRepository;
        mMainRepository.getMoreData(0, this);
    }

    MutableLiveData<List<String>> getStringList() {
        return stringList;
    }

    void loadMoreData(int count){
        mMainRepository.getMoreData(count, this);
    }

    @Override
    public void onDataLoaded(List<String> list) {
        stringList.setValue(list);
    }

    @Override
    public void onDataNotAvailable(String error) {

    }
}