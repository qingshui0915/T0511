package com.bawei.t0511.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.t0511.MainActivity;
import com.bawei.t0511.R;
import com.bawei.t0511.bean.DedailsBean;
import com.bawei.t0511.comtor.ZuHeView;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public class Xqragment extends Fragment {

    private static final String TAG = "Xqragment";
    private ImageView xiang_im;
    private DedailsBean dedailsBean1;
    private TextView xiang_tv;
    private TextView xiang_ptv;
    private ZuHeView show_zuhe;
    private EditText show_sum;
    private String price;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xq, null);
        EventBus.getDefault().register(this);
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).onChangesItem();
                }
            }
        });
        show_zuhe = view.findViewById(R.id.show_zuhe);
        show_sum = view.findViewById(R.id.show_sum);
        xiang_im = view.findViewById(R.id.xiang_im);
        xiang_tv = view.findViewById(R.id.xiang_tv);
        xiang_ptv = view.findViewById(R.id.xiang_ptv);

        show_sum.setText("￥：");
        //String s = show_zuhe.show_ed.getText().toString();

        show_zuhe.setPriceChangeListener(new ZuHeView.onPriceChangeListener() {
            @Override
            public void onSumChangelistener(int oldNum, int ewSum) {
                Log.d(TAG, "onSumChangelistener: " + ewSum + "dahsuksbjdbkja" + oldNum);
//                ewSum
                Log.d(TAG, "asfdsfsdfadgetManager: " + price);
                int sums = 0;
                Integer integer = Integer.valueOf(price);

                sums = ewSum * integer;
                show_sum.setText("￥：" + sums + "");

            }
        });

        return view;
    }

    //接收数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getManager(DedailsBean dedailsBean) {
        Log.i(TAG, "getManager: " + dedailsBean.getImg());
        dedailsBean1 = dedailsBean;
        Glide.with(getContext()).load(dedailsBean1.getImg()).into(xiang_im);
        xiang_tv.setText(dedailsBean.getName());
        xiang_ptv.setText("￥" + dedailsBean.getPrice() + "元");
        price = dedailsBean.getPrice();
//        Log.d(TAG, "asfdsfsdfadgetManager: " + price);


//        int sum = show_zuhe.getSum();
//        Log.i(TAG, "aaaaaagetManager: " + sum);
//        int num = 0;
//        Integer integer = Integer.valueOf(price);
//        num = sum * integer;
//
//        show_sum.setText(num);

    }

    @Override
    public void onStop() {
        super.onStop();
        dedailsBean1 = null;
        getActivity().finish();


//
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


}
