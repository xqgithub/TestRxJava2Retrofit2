package com.example.testrxjava2retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = RetrofitServiceManager.getInstance().getApiService();
        apiService.routesv3()
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                // 输出异常信息
                                Log.d(TAG, "发生异常 = " + throwable.toString());
                                /**
                                 * 需求2：实现重试
                                 * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                 *
                                 * 需求3：延迟1段时间再重试
                                 * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                 *
                                 * 需求4：遇到的异常越多，时间越长
                                 * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                 */
                                if (throwable instanceof IOException) {
                                    return Observable.just(1).delay(0, TimeUnit.MILLISECONDS);
                                } else {
                                    // 若发生的异常不属于I/O异常，则不重试
                                    // 通过返回的Observable发送的事件 = Error事件 实现（可在观察者的onError（）中获取信息）
                                    return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
                                }
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ApiResponse<RoutesResponse>>() {
                    @Override
                    public void onSuccess(ApiResponse<RoutesResponse> routesResponseApiResponse) {
                        Log.i("111", "routesResponseApiResponse----->" + routesResponseApiResponse.getData().getD2o());
                    }

                    @Override
                    public void onFailure(String message, int error_code) {
                        Log.i("111", "onFailure----->" + message + " | " + error_code);
                    }
                });
    }
}
