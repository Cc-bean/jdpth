package com.hj.jdpth.domain;

public class Backtype {
    private Integer bKey;

    private String bType;

    private String bBeiyong1;

    private String bBeiyong2;

    private String bBeiyong3;

    public Integer getbKey() {
        return bKey;
    }

    public void setbKey(Integer bKey) {
        this.bKey = bKey;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType == null ? null : bType.trim();
    }

    public String getbBeiyong1() {
        return bBeiyong1;
    }

    public void setbBeiyong1(String bBeiyong1) {
        this.bBeiyong1 = bBeiyong1 == null ? null : bBeiyong1.trim();
    }

    public String getbBeiyong2() {
        return bBeiyong2;
    }

    public void setbBeiyong2(String bBeiyong2) {
        this.bBeiyong2 = bBeiyong2 == null ? null : bBeiyong2.trim();
    }

    public String getbBeiyong3() {
        return bBeiyong3;
    }

    public void setbBeiyong3(String bBeiyong3) {
        this.bBeiyong3 = bBeiyong3 == null ? null : bBeiyong3.trim();
    }
}