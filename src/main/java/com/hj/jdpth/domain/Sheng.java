package com.hj.jdpth.domain;

public class Sheng {
    private String sName;

    private Integer sKey;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public Integer getsKey() {
        return sKey;
    }

    public void setsKey(Integer sKey) {
        this.sKey = sKey;
    }
}