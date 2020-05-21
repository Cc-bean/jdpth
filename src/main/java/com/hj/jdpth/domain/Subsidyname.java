package com.hj.jdpth.domain;

public class Subsidyname {
    private Integer sKey;//iD

    private String sName;//补助项名称

    private Integer sType;//补助项类型Id 查找补助类型

    private Integer sUp;//上级政策Id

    private Integer sDown;//下级政策ID

    private String sBeiyong1;//（年份）

    private String sBeiyong2;//村ID

    private String sBeiyong3;

    @Override
    public String toString() {
        return "Subsidyname{" +
                "sKey=" + sKey +
                ", sName='" + sName + '\'' +
                ", sType=" + sType +
                ", sUp=" + sUp +
                ", sDown=" + sDown +
                ", sBeiyong1='" + sBeiyong1 + '\'' +
                ", sBeiyong2='" + sBeiyong2 + '\'' +
                ", sBeiyong3='" + sBeiyong3 + '\'' +
                '}';
    }

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

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }

    public Integer getsUp() {
        return sUp;
    }

    public void setsUp(Integer sUp) {
        this.sUp = sUp;
    }

    public Integer getsDown() {
        return sDown;
    }

    public void setsDown(Integer sDown) {
        this.sDown = sDown;
    }

    public String getsBeiyong1() {
        return sBeiyong1;
    }

    public void setsBeiyong1(String sBeiyong1) {
        this.sBeiyong1 = sBeiyong1 == null ? null : sBeiyong1.trim();
    }

    public String getsBeiyong2() {
        return sBeiyong2;
    }

    public void setsBeiyong2(String sBeiyong2) {
        this.sBeiyong2 = sBeiyong2 == null ? null : sBeiyong2.trim();
    }

    public String getsBeiyong3() {
        return sBeiyong3;
    }

    public void setsBeiyong3(String sBeiyong3) {
        this.sBeiyong3 = sBeiyong3 == null ? null : sBeiyong3.trim();
    }
}