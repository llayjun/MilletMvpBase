package com.millet.z_basic.base.mvp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.millet.z_basic.base.dialog.LoadingDialog;
import com.millet.z_basic.net.dialog.NetLoadingUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxAppCompatActivity implements IBaseMvpView, IActivity , View.OnClickListener {

    protected P mPresenter;

    protected LoadingDialog dialog;

    private Unbinder mUnbinder;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initImmersionBar();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
        mPresenter.setActivity(this);
        dialog = NetLoadingUtils.createDialog(this, "记载中...");
        initView();
        initData(savedInstanceState);
        loadData();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).init();
    }

    /**
     * 今日头条适配方式
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080);
    }

    public void showLoadingDialog() {
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideLoadingDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
