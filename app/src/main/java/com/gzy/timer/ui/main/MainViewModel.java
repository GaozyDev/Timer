package com.gzy.timer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gzy.timer.data.DataSource;
import com.gzy.timer.data.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel implements DataSource.Callback<List<String>> {

    private MainRepository mMainRepository;

    List<String> mStringList = new ArrayList<>();

    private MutableLiveData<Integer> mDataChange = new MutableLiveData<>();

    MainViewModel(MainRepository mainRepository) {
        this.mMainRepository = mainRepository;
    }

    MutableLiveData<Integer> getDataChange() {
        return mDataChange;
    }

    void loadMoreData(int count){
        mMainRepository.getMoreData(count, this);
    }

    @Override
    public void onDataLoaded(List<String> list) {
        mStringList.addAll(list);
        mDataChange.setValue(mStringList.size());
    }

    @Override
    public void onDataNotAvailable(String error) {

    }
}