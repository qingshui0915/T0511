package com.bawei.t0511.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.t0511.R;
import com.bawei.t0511.bean.ShowBean;
import com.bawei.t0511.comtor.ZuHeView;
import com.bumptech.glide.Glide;

/**
 * Project_Name: T0511
 * Time: 2019/5/11
 * Data: 晚么
 * Description:
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private Context context;
    private ShowBean showBean;
    private setOnClick onClick;

    public ShowAdapter(Context context, ShowBean showBean) {
        this.context = context;
        this.showBean = showBean;
    }

    @NonNull
    @Override
    public ShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sy_item, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(showBean.getResult().get(i).getMasterPic()).into(viewHolder.show_im);
        viewHolder.show_tv.setText(showBean.getResult().get(i).getCommodityName());
        viewHolder.show_ptv.setText("￥" + showBean.getResult().get(i).getPrice() + "元");

        //实现点击
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onSetclick(viewHolder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return showBean.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView show_im;
        private final TextView show_tv, show_ptv;
        private final ZuHeView zuHeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_im = itemView.findViewById(R.id.show_im);
            show_tv = itemView.findViewById(R.id.show_tv);
            show_ptv = itemView.findViewById(R.id.show_ptv);

            zuHeView = itemView.findViewById(R.id.zuhe);
        }
    }

    //点击
    public void rightClick(ShowAdapter.setOnClick onClick) {
        this.onClick = onClick;
    }
    public interface setOnClick {
        void onSetclick(int i);
    }



}
