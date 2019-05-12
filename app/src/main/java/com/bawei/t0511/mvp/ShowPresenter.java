package com.bawei.t0511.mvp;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public class ShowPresenter implements ShowConstract.IShowPresenter {
    private ShowConstract.IShowView iShowView;
    private ShowModel showModel;

    @Override
    public void accth(ShowConstract.IShowView iShowView) {
        this.iShowView=iShowView;
        showModel = new ShowModel();
    }

    @Override
    public void deach() {
        if (iShowView!=null){
            iShowView=null;
        }
        if (showModel!=null){
            showModel=null;
        }
        System.gc();

    }

    @Override
    public void getModel(String url) {
        showModel.Requester(url, new ShowConstract.IShowModel.ModelCallBack() {
            @Override
            public void onSuccess(String result) {
                iShowView.getPreData(result);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
