package com.millet.android.presenter;

import com.millet.z_basic.base.mvp.IBaseMvpView;
import com.millet.z_basic.net.bean.WeatherBean;

public interface IMainPresenter {

    void getWeatherInfo(String cityName);

    interface IMainView extends IBaseMvpView {
        void onGetWeatherInfo(WeatherBean weatherBean);
    }

}
