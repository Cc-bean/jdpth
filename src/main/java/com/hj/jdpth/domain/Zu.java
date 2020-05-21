package com.hj.jdpth.domain;

public class Zu {
    private Integer zKey;

    private Integer zVillage;

    private String zName;

    private String zBeiyong1;

    public Integer getzKey() {
        return zKey;
    }

    public void setzKey(Integer zKey) {
        this.zKey = zKey;
    }

    public Integer getzVillage() {
        return zVillage;
    }

    public void setzVillage(Integer zVillage) {
        this.zVillage = zVillage;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName == null ? null : zName.trim();
    }

    public String getzBeiyong1() {
        return zBeiyong1;
    }

    public void setzBeiyong1(String zBeiyong1) {
        this.zBeiyong1 = zBeiyong1 == null ? null : zBeiyong1.trim();
    }
}