package com.example.dell.myapplication.persenter;

import android.content.Context;

import com.example.dell.myapplication.beans.RootBean;
import com.example.dell.myapplication.model.Model;
import com.example.dell.myapplication.view.IView;

/**
 * Created by DELL on 2019/5/29.
 */

public class Persenter implements Ipersenter {
    private IView mIView;
    private final Model mModel;

    public Persenter(IView iView) {
        mIView = iView;
        mModel = new Model();
    }

    @Override
    public void setData() {
        if (mModel != null) {
            mModel.getData(new ICallBask() {
                @Override
                public void Successful(RootBean rootBean) {
                    mIView.Successful(rootBean);
                }

                @Override
                public void Error(String error) {
                    mIView.Error(error);
                }
            });
        }
    }
}
