package com.example.dell.myapplication.model;

import android.content.Context;
import android.util.Log;

import com.example.dell.myapplication.beans.RootBean;
import com.example.dell.myapplication.persenter.ICallBask;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DELL on 2019/5/29.
 */

public class Model implements Imodel {
    private static final String TAG = "Model";

    @Override
    public void getData(final ICallBask iCallBask) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getCause().getMessage());
                iCallBask.Error(e.getCause().getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resurt = response.body().string();
                RootBean rootBean = new Gson().fromJson(resurt, RootBean.class);
                iCallBask.Successful(rootBean);
            }
        });
    }
}
