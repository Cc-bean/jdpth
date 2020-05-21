package com.hj.jdpth.domain;

public class Policystatu {
    private Integer pKey;

    private String pName;

    private String pBeiyong1;

    private String pBeiyong2;

    private String pBeiyong3;

    public Integer getpKey() {
        return pKey;
    }

    public void setpKey(Integer pKey) {
        this.pKey = pKey;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpBeiyong1() {
        return pBeiyong1;
    }

    public void setpBeiyong1(String pBeiyong1) {
        this.pBeiyong1 = pBeiyong1 == null ? null : pBeiyong1.trim();
    }

    public String getpBeiyong2() {
        return pBeiyong2;
    }

    public void setpBeiyong2(String pBeiyong2) {
        this.pBeiyong2 = pBeiyong2 == null ? null : pBeiyong2.trim();
    }

    public String getpBeiyong3() {
        return pBeiyong3;
    }

    public void setpBeiyong3(String pBeiyong3) {
        this.pBeiyong3 = pBeiyong3 == null ? null : pBeiyong3.trim();
    }
}