package com.millet.android.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.millet.android.R;
import com.millet.android.presenter.IMainPresenter;
import com.millet.android.presenter.impl.MainPresenter;
import com.millet.z_basic.base.mvp.BaseMvpActivity;
import com.millet.z_basic.net.bean.WeatherBean;

public class MainActivity extends BaseMvpActivity<IMainPresenter.IMainView, MainPresenter> implements IMainPresenter.IMainView {

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        TextView textView = findViewById(R.id.tv_click);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getWeatherInfo("南通");
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetWeatherInfo(WeatherBean weatherBean) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void cancelLoadingDialog() {

    }

    @Override
    public void showError(String message) {

    }

}
