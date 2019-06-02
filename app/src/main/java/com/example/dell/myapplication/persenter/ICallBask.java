package com.example.dell.myapplication.persenter;

import com.example.dell.myapplication.beans.RootBean;

/**
 * Created by DELL on 2019/5/29.
 */

public interface ICallBask {
    void Successful(RootBean rootBean);

    void Error(String error);
}
