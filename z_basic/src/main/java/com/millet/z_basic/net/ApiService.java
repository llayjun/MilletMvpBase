package com.millet.z_basic.net;

import com.millet.z_basic.net.bean.WeatherBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "https://www.wanandroid.com/";


    // 以下为retrofit2接口请求的例子
    // get请求
    @GET("xxx")
    Observable<String> get(@Query("x") int x);

    // 传json
    @POST("xxx")
    Observable<String> postJson(@Body WeatherBean weatherBean);

    // 表单形式
    @FormUrlEncoded
    @POST("xxx")
    Observable<String> postForm(
            @Field("x") int x
    );

    // 上传图片一张图片
    @Multipart
    @POST("xxx")
    Observable<String> uploadOneImage(@Part MultipartBody.Part part);

    // 上传多张图片
    @Multipart
    @POST("xxx")
    Observable<String> uploadManyImages(@Part List<MultipartBody.Part> partList);

    // 获取天气预报
    @GET("http://wthrcdn.etouch.cn/weather_mini")
    Observable<WeatherBean> getWeatherByCityName(@Query("city") String cityName);

}
