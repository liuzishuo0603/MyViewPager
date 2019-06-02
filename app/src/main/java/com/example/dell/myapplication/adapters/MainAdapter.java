package com.example.dell.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.beans.RootBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/5/29.
 */

public class MainAdapter extends RecyclerView.Adapter {
    public List<RootBean.ResultsBean> mList = new ArrayList<>();
    private Context mContext;
    private onClickListener mListener;

    public MainAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_item_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        final RootBean.ResultsBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getUrl()).apply(RequestOptions.circleCropTransform()).placeholder(R.mipmap.ic_launcher).into(viewHolder.mIv);
        viewHolder.mTv.setText(bean.getDesc());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick((ArrayList<RootBean.ResultsBean>) mList, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    public interface onClickListener {
        void onClick(ArrayList<RootBean.ResultsBean> list, int position);
    }

    public void setOnClickListener(onClickListener listener) {
        mListener = listener;
    }
}
