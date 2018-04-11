package com.synews.hammer.http;

import com.synews.hammer.mvp.IView;
import com.synews.hammer.utils.RxLifecycleUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author chen
 * RX转换器，整合了线程的转换和声明周期的绑定动作
 *
 */
public class RxTransformer<T> implements ObservableTransformer<T, T> {



    IView  mRootView;

    private RxTransformer(IView rootView) {
        this.mRootView = rootView;
    }

    public static <T> RxTransformer<T> createTransformer(IView rootView) {
        return new RxTransformer<T>(rootView);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView));
    }


}
