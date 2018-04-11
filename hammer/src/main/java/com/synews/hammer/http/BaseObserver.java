package com.synews.hammer.http;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 陈东  -  2018/4/2 - 下午4:17
 */
public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart(d);

    }


    @Override
    public void onNext(T baseEntity) {
        onRequestEnd();
        //判断服务器代码
        RequestProcessing();
    }

    /**
     * 处理返回的代码
     */
    protected abstract void RequestProcessing();

    /**
     * 根据不同的错误情况同统一处理错误
     *
     * @param e e
     */
    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @param data
     * @throws Exception e
     */
    protected abstract void onSuccess(T data) throws Exception;



    /**
     * 返回失败
     *
     * @param e              e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception e
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;


    /**
     * 请求开始，重写此方法在请求之前做操作
     * @param d Disposable
     */
    protected void onRequestStart(Disposable d) {
    }

    /**
     * 请求结束，重写此方法在请求之后做操作
     * 无论是否成功都会走此方法
     *
     */
    protected void onRequestEnd() {

    }


}
