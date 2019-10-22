package com.millet.z_basic.net;

import android.app.Activity;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;
import com.millet.z_basic.net.bean.BaseModel;
import com.millet.z_basic.net.dialog.NetLoadingUtils;
import com.millet.z_basic.net.exception.ExceptionHandler;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.millet.z_basic.net.exception.ExceptionHandler.onException;

public abstract class BaseObserver<T extends BaseModel> implements Observer<T> {

    private static final String tag = BaseObserver.class.getSimpleName();

    protected BaseObserver(Activity activity, boolean isShowDialog) {
        if (isShowDialog)
            NetLoadingUtils.showDialog(activity);
    }

    protected BaseObserver(Activity activity) {
        NetLoadingUtils.showDialog(activity);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        NetLoadingUtils.hideDialog();
        if (t.isSuccess()) {
            try {
                onSuccess(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onFailure(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        NetLoadingUtils.hideDialog();
        int resId;
        if (e instanceof HttpException) {     //   HTTP错误
            resId = onException(ExceptionHandler.ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            resId = onException(ExceptionHandler.ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            resId = onException(ExceptionHandler.ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            resId = onException(ExceptionHandler.ExceptionReason.PARSE_ERROR);
        } else {//  未知错误
            resId = onException(ExceptionHandler.ExceptionReason.UNKNOWN_ERROR);
        }
        ToastUtils.showShort(resId);
    }

    /**
     * 返回成功
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

    /**
     * 返回失败
     *
     * @param response
     */
    protected void onFailure(T response) {
        Logger.d("错误消息" + response.getErrorCode() + "   " + response.getErrorMessage());
        if (response.getErrorCode() == -1001) { // 重新登录
            // 需要重新登录
            startLogin();
        }
    }

    public static void startLogin() {
//        Intent intent = new Intent(Utils.getApp(), LoginActivity.class);
//        Utils.getApp().startActivity(intent);
    }

}
