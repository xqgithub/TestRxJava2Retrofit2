package com.example.testrxjava2retrofit2;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2018/1/16.
 */
public abstract class BaseSubscriber<T> implements Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onFailure(String message, int error_code);

    public Disposable d;

    @Override
    public void onComplete() {
        Log.i("111", "onComplete----->");
        d.dispose();
    }

    @Override
    public void onError(Throwable e) {
        Log.i("111", "onError----->");
        onFailure("网络繁忙:", 777);
        d.dispose();
    }

    @Override
    public void onNext(T t) {
        Log.i("111", "onNext----->");
        ApiResponse br = (ApiResponse) t;
        if (br == null) {
            onFailure("请求失败:" + 400, 400);
            return;
        }
        int error_code = 0;
        if (br.isSuccess()) {
            onSuccess(t);
        } else {
            error_code = br.getError_code();
            if (error_code != 100) {
                onFailure(br.getMessage(), error_code);
            }
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i("111", "onSubscribe----->");
        this.d = d;
    }
}
