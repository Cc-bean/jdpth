package com.hj.jdpth.domain;

public class Region {
    private Integer rKey;

    private String rName;

    private Integer rShi;

    public Integer getrKey() {
        return rKey;
    }

    public void setrKey(Integer rKey) {
        this.rKey = rKey;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public Integer getrShi() {
        return rShi;
    }

    public void setrShi(Integer rShi) {
        this.rShi = rShi;
    }
}