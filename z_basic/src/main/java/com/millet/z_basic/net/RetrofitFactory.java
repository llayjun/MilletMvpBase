package com.millet.z_basic.net;

import com.millet.z_basic.net.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private int HTTP_TIME = 10;

    private final ApiService mApiService;

    private static RetrofitFactory mRetrofitFactory;

    private RetrofitFactory() {
        //创建OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())// 添加拦截器
//                .addInterceptor(new ReceivedCookiesInterceptor())// 接收cookies
//                .addInterceptor(new AddCookiesInterceptor()) // 添加cookies
                .build();

        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava2转换器
                .client(mOkHttpClient)
                .build();

        //创建接口实现类
        mApiService = retrofit.create(ApiService.class);
    }

    public static RetrofitFactory getInstance() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }
        }
        return mRetrofitFactory;
    }

    public ApiService API() {
        return mApiService;
    }

    public static ObservableTransformer threadTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> void setRequest(Observable observable, Observer<T> observer) {
        observable
                .compose(threadTransformer())
                .subscribe(observer);
    }

}
