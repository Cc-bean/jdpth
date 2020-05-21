package com.hj.jdpth.domain;

public class SubsidyCustom extends Subsidyinformation {

    private String stName;//补贴类型
    private String sName;//补助项名称

    @Override
    public String toString() {
        return super.toString() + "SubsidyCustom{" +
                "stName='" + stName + '\'' +
                ", sName='" + sName + '\'' +
                '}';
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
