package com.bawei.t0511.comtor;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.t0511.R;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:自定义组合控件
 */
public class ZuHeView extends LinearLayout {

    public Button show_jian;
    public EditText show_ed;
    public Button show_jia;
    private static int sum=0;
    public ZuHeView(Context context) {
        this(context,null);
    }

    public ZuHeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZuHeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context,R.layout.zuhe,this);

        show_jian = findViewById(R.id.show_jian);
        show_ed = findViewById(R.id.show_ed);
        show_jia = findViewById(R.id.show_jia);

        //加
        show_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = show_ed.getText().toString();
                int sum=0;
                if (!TextUtils.isEmpty(s)) {
                    sum=Integer.valueOf(s);
                    sum++;
                }
                    show_ed.setText(sum+"");
                if (priceChangeListener!=null) {
                    priceChangeListener.onSumChangelistener(Integer.valueOf(s),sum);
                }
            }
        });
        //减
        show_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = show_ed.getText().toString();
                int sum=0;
                if (!TextUtils.isEmpty(s)){
                    sum=Integer.valueOf(s);
                    sum--;
                }
                if (sum<0){
                    sum=0;
                    show_ed.setText(sum+"");
                }else{
                    show_ed.setText(sum+"");
                    if (priceChangeListener!=null) {
                        priceChangeListener.onSumChangelistener(Integer.valueOf(s),sum);
                    }
                }
            }
        });
    }
//        public int getSum() {
//            String s = show_ed.getText().toString();
//            if (!TextUtils.isEmpty(s)) {
//                return Integer.valueOf(s);
//            }
//            return 0;
//        }
//
//        public void setSum(int newsum) {
//            if(newsum>0){
//                show_ed.setText(newsum);
//            }else{
//                Toast.makeText(getContext(), "不能小于0哦", Toast.LENGTH_SHORT).show();
//            }
//        }


    //自定义接口
    onPriceChangeListener priceChangeListener;
    public interface onPriceChangeListener{
        void onSumChangelistener(int oldNum,int ewSum);
    }
    public void setPriceChangeListener(onPriceChangeListener priceChangeListener) {
        this.priceChangeListener = priceChangeListener;
    }
}
