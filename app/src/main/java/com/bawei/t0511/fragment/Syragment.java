package com.bawei.t0511.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.t0511.MainActivity;
import com.bawei.t0511.R;
import com.bawei.t0511.adapter.ShowAdapter;
import com.bawei.t0511.bean.DedailsBean;
import com.bawei.t0511.bean.ShowBean;
import com.bawei.t0511.mvp.ShowConstract;
import com.bawei.t0511.mvp.ShowPresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public class Syragment extends Fragment implements ShowConstract.IShowView {

    private ShowPresenter showPresenter;
    private RecyclerView show_rlc;
    private ShowBean showBean;
    public static final String URl = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=手机&page=1&count=10";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sy, null);
        showPresenter = new ShowPresenter();
        showPresenter.accth(this);
        showPresenter.getModel(URl);
        show_rlc = view.findViewById(R.id.show_rlc);

        return view;
    }

    @Override
    public void getPreData(String data) {
        Gson gson = new Gson();
        showBean = gson.fromJson(data, ShowBean.class);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        show_rlc.setLayoutManager(linearLayoutManager);
        //设置适配器
        ShowAdapter showAdapter = new ShowAdapter(getContext(), showBean);
        show_rlc.setAdapter(showAdapter);
        //回调点击
        showAdapter.rightClick(new ShowAdapter.setOnClick() {
            @Override
            public void onSetclick(int i) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).onChangeItem();
                    //发送数据
                    DedailsBean dedailsBean = new DedailsBean();
                    dedailsBean.setImg(showBean.getResult().get(i).getMasterPic());
                    dedailsBean.setName(showBean.getResult().get(i).getCommodityName());
                    dedailsBean.setPrice(showBean.getResult().get(i).getPrice() + "");
                    EventBus.getDefault().post(dedailsBean);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showPresenter.deach();
    }
}
