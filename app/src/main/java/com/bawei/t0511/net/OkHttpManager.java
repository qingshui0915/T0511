package com.bawei.t0511.net;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:OKHTTP网络工具框架
 */
public class OkHttpManager {
    private Handler handler;
    private OkHttpClient client;
    //单例
    private static final OkHttpManager ourInstance = new OkHttpManager();
    public static OkHttpManager getInstance() {
        return ourInstance;
    }
    private OkHttpManager() {
        initOkHttp();
    }

    private void initOkHttp() {
        handler = new Handler();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                //日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        client = builder.build();
    }
    //get请求
    public void doGet(String url, final OkHttpCallBack okHttpCallBack){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (okHttpCallBack!=null){
                            okHttpCallBack.onError();
                        }
                    }
                });
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (okHttpCallBack != null) {
                                okHttpCallBack.onSuccess(string);
                            }
                        }
                    });
                }else {
                    if (okHttpCallBack!=null){
                        okHttpCallBack.onError();
                    }
                }

            }
        });

    }
    //接口
    public interface OkHttpCallBack{
        void onSuccess(String result);

        void onError();
    }
}
