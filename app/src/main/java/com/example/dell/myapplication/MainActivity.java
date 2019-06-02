package com.example.dell.myapplication;
//刘子硕 /2019/5/29/20:42

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.dell.myapplication.adapters.MainAdapter;
import com.example.dell.myapplication.beans.RootBean;
import com.example.dell.myapplication.beans.Stu;
import com.example.dell.myapplication.persenter.Persenter;
import com.example.dell.myapplication.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView mRlv;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Persenter persenter = new Persenter(this);
        persenter.setData();
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new MainAdapter(this);
        mRlv.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new MainAdapter.onClickListener() {
            @Override
            public void onClick(ArrayList<RootBean.ResultsBean> list, int position) {
                Intent intent = new Intent(MainActivity.this, ViewPageActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(new Stu(list,position));
            }
        });
    }

    @Override
    public void Successful(RootBean rootBean) {
        final List<RootBean.ResultsBean> results = rootBean.getResults();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.mList.addAll(results);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "数据加载错误" + error, Toast.LENGTH_SHORT).show();
    }
}
