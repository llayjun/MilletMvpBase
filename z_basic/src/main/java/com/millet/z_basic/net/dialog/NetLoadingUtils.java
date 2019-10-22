package com.millet.z_basic.net.dialog;

import android.app.Activity;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.millet.z_basic.base.dialog.LoadingDialog;

public class NetLoadingUtils {

    private static LoadingDialog loadingDialog = null;

    public static void showDialog(Activity activity) {
        loadingDialog = new LoadingDialog.Builder(activity)
                .setMessage("加载中...")
                .setCancelable(false)
                .setCancelableOutSide(false)
                .create();
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public static LoadingDialog createDialog(Activity activity, @Nullable String text) {
        String loadText = "加载中...";
        if (!TextUtils.isEmpty(text))
            loadText = text;
        loadingDialog = new LoadingDialog.Builder(activity)
                .setMessage(loadText)
                .setCancelable(false)
                .setCancelableOutSide(false)
                .create();
        return loadingDialog;
    }

    public static void hideDialog() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
