package com.hj.jdpth.domain;

public class Lawtype {
    private Integer lKey;

    private String lName;

    private String lBeiyong1;

    private String lBeiyong2;

    private String lBeiyong3;

    private Integer lSheng;

    private Integer lShi;

    private Integer lRegion;

    private Integer lZhen;

    public Integer getlKey() {
        return lKey;
    }

    public void setlKey(Integer lKey) {
        this.lKey = lKey;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName == null ? null : lName.trim();
    }

    public String getlBeiyong1() {
        return lBeiyong1;
    }

    public void setlBeiyong1(String lBeiyong1) {
        this.lBeiyong1 = lBeiyong1 == null ? null : lBeiyong1.trim();
    }

    public String getlBeiyong2() {
        return lBeiyong2;
    }

    public void setlBeiyong2(String lBeiyong2) {
        this.lBeiyong2 = lBeiyong2 == null ? null : lBeiyong2.trim();
    }

    public String getlBeiyong3() {
        return lBeiyong3;
    }

    public void setlBeiyong3(String lBeiyong3) {
        this.lBeiyong3 = lBeiyong3 == null ? null : lBeiyong3.trim();
    }

    public Integer getlSheng() {
        return lSheng;
    }

    public void setlSheng(Integer lSheng) {
        this.lSheng = lSheng;
    }

    public Integer getlShi() {
        return lShi;
    }

    public void setlShi(Integer lShi) {
        this.lShi = lShi;
    }

    public Integer getlRegion() {
        return lRegion;
    }

    public void setlRegion(Integer lRegion) {
        this.lRegion = lRegion;
    }

    public Integer getlZhen() {
        return lZhen;
    }

    public void setlZhen(Integer lZhen) {
        this.lZhen = lZhen;
    }
}