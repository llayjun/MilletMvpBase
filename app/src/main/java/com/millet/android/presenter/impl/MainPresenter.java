package com.millet.android.presenter.impl;

import com.millet.z_basic.base.mvp.BaseMvpPresenter;
import com.millet.android.presenter.IMainPresenter;
import com.millet.z_basic.net.RetrofitFactory;
import com.millet.z_basic.net.bean.WeatherBean;
import com.millet.z_basic.net.dialog.NetLoadingUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BaseMvpPresenter<IMainPresenter.IMainView> implements IMainPresenter {

    @Override
    public void getWeatherInfo(String cityName) {
        RetrofitFactory.setRequest(RetrofitFactory.getInstance().API().getWeatherByCityName(cityName), new Observer<WeatherBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                NetLoadingUtils.showDialog(mActivity);
            }

            @Override
            public void onNext(WeatherBean weatherBean) {
                mView.onGetWeatherInfo(weatherBean);
            }

            @Override
            public void onError(Throwable e) {
                NetLoadingUtils.hideDialog();
                mView.showError(e.toString());
            }

            @Override
            public void onComplete() {
                NetLoadingUtils.hideDialog();
            }
        });
    }

}
