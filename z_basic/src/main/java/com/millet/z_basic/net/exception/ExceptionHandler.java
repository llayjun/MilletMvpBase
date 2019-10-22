package com.millet.z_basic.net.exception;

import com.millet.z_basic.R;

/**
 * 网络请求异常处理类
 */
public class ExceptionHandler {

    /**
     * 网络请求异常
     *
     * @param reason
     */
    public static int onException(ExceptionReason reason) {
        int resId;
        switch (reason) {
            case BAD_NETWORK:
                resId = R.string.bad_network;
                break;
            case CONNECT_ERROR:
                resId = R.string.connect_error;
                break;
            case CONNECT_TIMEOUT:
                resId = R.string.connect_timeout;
                break;
            case PARSE_ERROR:
                resId = R.string.parse_error;
                break;
            case UNKNOWN_ERROR:
            default:
                resId = R.string.unknown_error;
                break;
        }
        return resId;
    }

    /**
     * 网络请求失败原因
     */
    public enum ExceptionReason {
        //网络问题
        BAD_NETWORK,
        //连接错误
        CONNECT_ERROR,
        //连接超时
        CONNECT_TIMEOUT,
        //解析数据失败
        PARSE_ERROR,
        //未知错误
        UNKNOWN_ERROR,
    }

}
