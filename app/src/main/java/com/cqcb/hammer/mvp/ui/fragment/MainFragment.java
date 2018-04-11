package com.cqcb.hammer.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.synews.hammer.base.BaseFragment;
import com.synews.hammer.base.delegate.IFragment;
import com.synews.hammer.di.component.AppComponent;
import com.synews.hammer.utils.HammerUtils;

import com.cqcb.hammer.di.component.DaggerMainComponent;
import com.cqcb.hammer.di.module.MainModule;
import com.cqcb.hammer.mvp.contract.MainContract;
import com.cqcb.hammer.mvp.presenter.MainPresenter;

import com.cqcb.hammer.R;

import static com.synews.hammer.utils.Preconditions.checkNotNull;


public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View {

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信
     * 调用此方法时请注意调用时 Fragment 的生命周期，初始化操作请在 {@link #initData(Bundle)} 中操作
     * 更多说明请移步{@link IFragment#setData(Object)}
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        HammerUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        HammerUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }
}
