package com.bawei.t0511.mvp;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public interface ShowConstract {
    //IShowView
    interface IShowView{
        void getPreData(String data);
    }
    //IShowModel
    interface IShowModel{
        void Requester(String url,ModelCallBack modelCallBack);
        interface ModelCallBack{
            void onSuccess(String result);

            void onFail();
        }
    }
    //IShowPresenter
    interface IShowPresenter{
        void accth(IShowView iShowView);

        void deach();

        void getModel(String url);
    }
}
