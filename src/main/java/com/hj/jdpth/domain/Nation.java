package com.hj.jdpth.domain;

public class Nation {
    private Integer nKey;

    private String nName;

    public Integer getnKey() {
        return nKey;
    }

    public void setnKey(Integer nKey) {
        this.nKey = nKey;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName == null ? null : nName.trim();
    }
}