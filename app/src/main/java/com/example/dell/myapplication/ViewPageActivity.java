package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.myapplication.adapters.ViewPagerAdapter;
import com.example.dell.myapplication.beans.RootBean;
import com.example.dell.myapplication.beans.Stu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends AppCompatActivity {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void EnvBus(final Stu envenBus) {
        ArrayList<View> views = new ArrayList<>();
        List<RootBean.ResultsBean> list = envenBus.getList();
        Log.e("tab", "EnvBus: "+list.toString() );
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.viewpaget_item, null);
            ImageView iv = view.findViewById(R.id.view_iv);
            final TextView tv = view.findViewById(R.id.view_tv);
            Glide.with(this).load(list.get(i).getUrl()).into(iv);
            views.add(view);
            mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    tv.setText(envenBus.getI() + 1 + "/" + "20");
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
            ViewPagerAdapter adapter = new ViewPagerAdapter(views);
            mVp.setAdapter(adapter);
            mVp.setCurrentItem(envenBus.getI());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
