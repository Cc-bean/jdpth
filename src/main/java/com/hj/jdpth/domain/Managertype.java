package com.hj.jdpth.domain;

public class Managertype {
    private Integer mKey;

    private String mType;

    public Integer getmKey() {
        return mKey;
    }

    public void setmKey(Integer mKey) {
        this.mKey = mKey;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType == null ? null : mType.trim();
    }
}