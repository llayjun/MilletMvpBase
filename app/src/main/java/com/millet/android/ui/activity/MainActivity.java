package com.millet.android.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.millet.android.R;
import com.millet.android.presenter.IMainPresenter;
import com.millet.android.presenter.impl.MainPresenter;
import com.millet.z_basic.base.mvp.BaseMvpActivity;
import com.millet.z_basic.net.bean.WeatherBean;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<IMainPresenter.IMainView, MainPresenter> implements IMainPresenter.IMainView {

    @BindView(R.id.tv_click)
    TextView tvClick;

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

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {

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

    @OnClick(R.id.tv_click)
    public void onViewClicked() {
        mPresenter.getWeatherInfo("南通");
    }

}
