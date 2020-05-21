package com.hj.jdpth.domain;

public class Subsidytype {
    private Integer subsidytypeId;//Id

    private Integer siVillageid;//村ID

    private String stName;//补助项类型

    private String siBeiyong1;

    private String siBeiyong2;//镇ID

    private String siBeiyong3;

    public Integer getSubsidytypeId() {
        return subsidytypeId;
    }

    public void setSubsidytypeId(Integer subsidytypeId) {
        this.subsidytypeId = subsidytypeId;
    }

    public Integer getSiVillageid() {
        return siVillageid;
    }

    public void setSiVillageid(Integer siVillageid) {
        this.siVillageid = siVillageid;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName == null ? null : stName.trim();
    }

    public String getSiBeiyong1() {
        return siBeiyong1;
    }

    public void setSiBeiyong1(String siBeiyong1) {
        this.siBeiyong1 = siBeiyong1 == null ? null : siBeiyong1.trim();
    }

    public String getSiBeiyong2() {
        return siBeiyong2;
    }

    public void setSiBeiyong2(String siBeiyong2) {
        this.siBeiyong2 = siBeiyong2 == null ? null : siBeiyong2.trim();
    }

    public String getSiBeiyong3() {
        return siBeiyong3;
    }

    public void setSiBeiyong3(String siBeiyong3) {
        this.siBeiyong3 = siBeiyong3 == null ? null : siBeiyong3.trim();
    }

    @Override
    public String toString() {
        return "Subsidytype{" +
                "subsidytypeId=" + subsidytypeId +
                ", siVillageid=" + siVillageid +
                ", stName='" + stName + '\'' +
                ", siBeiyong1='" + siBeiyong1 + '\'' +
                ", siBeiyong2='" + siBeiyong2 + '\'' +
                ", siBeiyong3='" + siBeiyong3 + '\'' +
                '}';
    }
}