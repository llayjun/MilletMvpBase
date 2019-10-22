package com.millet.z_basic.base.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.millet.z_basic.R;
import com.millet.z_basic.base.dialog.base_dialog.BaseDialog;


/**
 * Created by zhangyinlei on 2018/7/11
 */
public class LoadingDialog extends BaseDialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context mContext;
        private String mMessage;
        private boolean isShowMessage = true;
        private boolean isCancelable = false;
        private boolean isCancelableOutSide = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.isCancelable = cancelable;
            return this;
        }

        public Builder setCancelableOutSide(boolean cancelableOutSide) {
            this.isCancelableOutSide = cancelableOutSide;
            return this;
        }

        public LoadingDialog create() {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.view_dialog_loading, null);
            TextView messageTv = view.findViewById(R.id.progress_text);
            if (isShowMessage) {
                messageTv.setText(mMessage);
            } else {
                messageTv.setVisibility(View.GONE);
            }
            LoadingDialog loadingDialog = new LoadingDialog(mContext, R.style.DialogNoTitleStyle);
            loadingDialog.setContentView(view);
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.setCanceledOnTouchOutside(isCancelableOutSide);
            return loadingDialog;
        }
    }

}
