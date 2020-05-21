package com.hj.jdpth.domain;

public class Shi {
    private Integer sKey;

    private String sName;

    private Integer sSheng;

    public Integer getsKey() {
        return sKey;
    }

    public void setsKey(Integer sKey) {
        this.sKey = sKey;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public Integer getsSheng() {
        return sSheng;
    }

    public void setsSheng(Integer sSheng) {
        this.sSheng = sSheng;
    }
}