package com.hj.jdpth.domain;

public class Hu {
    private Integer hKey;

    private Integer hZu;

    private String hName;

    private String hBeiyong1;

    public Integer gethKey() {
        return hKey;
    }

    public void sethKey(Integer hKey) {
        this.hKey = hKey;
    }

    public Integer gethZu() {
        return hZu;
    }

    public void sethZu(Integer hZu) {
        this.hZu = hZu;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName == null ? null : hName.trim();
    }

    public String gethBeiyong1() {
        return hBeiyong1;
    }

    public void sethBeiyong1(String hBeiyong1) {
        this.hBeiyong1 = hBeiyong1 == null ? null : hBeiyong1.trim();
    }
}