package com.hj.jdpth.domain;

public class Zichanmingzi {
    private Integer zcmzId;

    private String zcmzName;

    private Integer zcmzLeixing;

    public Integer getZcmzId() {
        return zcmzId;
    }

    public void setZcmzId(Integer zcmzId) {
        this.zcmzId = zcmzId;
    }

    public String getZcmzName() {
        return zcmzName;
    }

    public void setZcmzName(String zcmzName) {
        this.zcmzName = zcmzName == null ? null : zcmzName.trim();
    }

    public Integer getZcmzLeixing() {
        return zcmzLeixing;
    }

    public void setZcmzLeixing(Integer zcmzLeixing) {
        this.zcmzLeixing = zcmzLeixing;
    }
}