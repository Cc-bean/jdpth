package com.hj.jdpth.domain;

public class SubsidynameCustom extends Subsidyname {
    private String siStandard;//标准
    private String siReceivetime;//申领时间
    private Float siSubsidyamount;//补贴金额
    private String siThing;//补贴物品
    private String siPrize;//奖励品
    private Integer siNameofsubsidy;//补助项ID
    private String siBeiyong2;//序号

    @Override
    public String toString() {
        return super.toString() + "SubsidynameCustom{" +
                "siStandard='" + siStandard + '\'' +
                ", siReceivetime='" + siReceivetime + '\'' +
                ", siSubsidyamount=" + siSubsidyamount +
                ", siThing='" + siThing + '\'' +
                ", siPrize='" + siPrize + '\'' +
                ", siNameofsubsidy=" + siNameofsubsidy +
                ", siBeiyong2='" + siBeiyong2 + '\'' +
                '}';
    }

    public String getSiBeiyong2() {
        return siBeiyong2;
    }

    public void setSiBeiyong2(String siBeiyong2) {
        this.siBeiyong2 = siBeiyong2;
    }

    public Integer getSiNameofsubsidy() {
        return siNameofsubsidy;
    }

    public void setSiNameofsubsidy(Integer siNameofsubsidy) {
        this.siNameofsubsidy = siNameofsubsidy;
    }

    public String getSiStandard() {
        return siStandard;
    }

    public void setSiStandard(String siStandard) {
        this.siStandard = siStandard;
    }

    public String getSiReceivetime() {
        return siReceivetime;
    }

    public void setSiReceivetime(String siReceivetime) {
        this.siReceivetime = siReceivetime;
    }

    public Float getSiSubsidyamount() {
        return siSubsidyamount;
    }

    public void setSiSubsidyamount(Float siSubsidyamount) {
        this.siSubsidyamount = siSubsidyamount;
    }

    public String getSiThing() {
        return siThing;
    }

    public void setSiThing(String siThing) {
        this.siThing = siThing;
    }

    public String getSiPrize() {
        return siPrize;
    }

    public void setSiPrize(String siPrize) {
        this.siPrize = siPrize;
    }
}
