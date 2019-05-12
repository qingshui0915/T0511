package com.bawei.t0511.mvp;

import com.bawei.t0511.net.OkHttpManager;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public class ShowModel implements ShowConstract.IShowModel {
    //获取网络请求
    @Override
    public void Requester(String url, final ModelCallBack modelCallBack) {
        OkHttpManager.getInstance().doGet(url, new OkHttpManager.OkHttpCallBack() {
            @Override
            public void onSuccess(String result) {
                modelCallBack.onSuccess(result);
            }

            @Override
            public void onError() {
                modelCallBack.onFail();
            }
        });
    }
}
