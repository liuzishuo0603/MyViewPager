package com.example.dell.myapplication.view;

import com.example.dell.myapplication.beans.RootBean;

/**
 * Created by DELL on 2019/5/29.
 */

public interface IView {
    void Successful(RootBean rootBean);

    void Error(String error);
}
