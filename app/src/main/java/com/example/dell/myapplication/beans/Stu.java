package com.example.dell.myapplication.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/5/29.
 */

public class Stu {
    private ArrayList<RootBean.ResultsBean> mList;
    private int i;

    public Stu(ArrayList<RootBean.ResultsBean> list, int i) {
        mList = list;
        this.i = i;
    }

    public ArrayList<RootBean.ResultsBean> getList() {
        return mList;
    }

    public void setList(ArrayList<RootBean.ResultsBean> list) {
        mList = list;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "mList=" + mList +
                ", i=" + i +
                '}';
    }
}
