package com.cqcb.hammer.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.cqcb.hammer.R;
import com.cqcb.hammer.di.component.DaggerMainComponent;
import com.cqcb.hammer.di.module.MainModule;
import com.cqcb.hammer.mvp.contract.MainContract;
import com.cqcb.hammer.mvp.presenter.MainPresenter;
import com.synews.hammer.base.BaseActivity;
import com.synews.hammer.di.component.AppComponent;
import com.synews.hammer.utils.HammerUtils;
import com.synews.hammer.widget.CustomPopupWindow;

import butterknife.BindView;

import static com.synews.hammer.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.button)
    Button button;

    /**
     * 安装依赖注入组件，如找不到类,请编译一下项目
     */
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    /**
     * 初始化布局，如果initView返回0,则不会调用setContentView(),也不会 Bind ButterKnife
     */
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        // TextView view = new TextView(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = CustomPopupWindow.inflateView(MainActivity.this, R.layout.activity_main_content);
                CustomPopupWindow popupWindow  =  CustomPopupWindow.builder().contentView(view1)
                        .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                            @Override
                            public void initPopupView(View contentView) {

                            }
                        })
                        .build();
                popupWindow.show();
            }
        });

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
        finish();
    }
}
