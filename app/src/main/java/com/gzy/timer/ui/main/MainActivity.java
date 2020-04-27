package com.gzy.timer.ui.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gzy.timer.R;
import com.gzy.timer.ui.BaseActivity;
import com.gzy.timer.ui.login.TimerAdapter;
import com.gzy.timer.utils.StatusBarUtils;

public class MainActivity extends BaseActivity {

    private MainViewModel mMainViewModel;

    private TimerAdapter mTimerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setTextColor(this, true);

        mMainViewModel = ViewModelProviders.of(this, new MainViewModelFactory())
                .get(MainViewModel.class);

        initView();
        observeViewModel();
        initClickListener();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTimerAdapter = new TimerAdapter(this, mMainViewModel.mStringList);
        recyclerView.setAdapter(mTimerAdapter);
    }

    private void initClickListener() {
        findViewById(R.id.add).setOnClickListener(v
                -> mMainViewModel.loadMoreData(mTimerAdapter.getItemCount()));
    }

    private void observeViewModel() {
        mMainViewModel.getDataChange().observe(this, stringList
                -> mTimerAdapter.notifyDataSetChanged());
    }
}
