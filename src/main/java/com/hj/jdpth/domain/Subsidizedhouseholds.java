package com.hj.jdpth.domain;

public class Subsidizedhouseholds {
    private Integer subsidizedhouseholdsId;//Id

    private String shName;//姓名

    private String shAddress;//地址

    private String shEnjoythesubsidy;//享受的补助

    private String shFamilyinformation;//家庭情况描述

    private Integer shVillageid;//村

    private String shBeiyong1;//序号

    private String shBeiyong2;//身份证

    private String shBeiyong3;//补助金额（物）

    private String shHu;//年份

    public Integer getSubsidizedhouseholdsId() {
        return subsidizedhouseholdsId;
    }

    public void setSubsidizedhouseholdsId(Integer subsidizedhouseholdsId) {
        this.subsidizedhouseholdsId = subsidizedhouseholdsId;
    }

    public String getShName() {
        return shName;
    }

    public void setShName(String shName) {
        this.shName = shName == null ? null : shName.trim();
    }

    public String getShAddress() {
        return shAddress;
    }

    public void setShAddress(String shAddress) {
        this.shAddress = shAddress == null ? null : shAddress.trim();
    }

    public String getShEnjoythesubsidy() {
        return shEnjoythesubsidy;
    }

    public void setShEnjoythesubsidy(String shEnjoythesubsidy) {
        this.shEnjoythesubsidy = shEnjoythesubsidy == null ? null : shEnjoythesubsidy.trim();
    }

    public String getShFamilyinformation() {
        return shFamilyinformation;
    }

    public void setShFamilyinformation(String shFamilyinformation) {
        this.shFamilyinformation = shFamilyinformation == null ? null : shFamilyinformation.trim();
    }

    public Integer getShVillageid() {
        return shVillageid;
    }

    public void setShVillageid(Integer shVillageid) {
        this.shVillageid = shVillageid;
    }

    public String getShBeiyong1() {
        return shBeiyong1;
    }

    public void setShBeiyong1(String shBeiyong1) {
        this.shBeiyong1 = shBeiyong1 == null ? null : shBeiyong1.trim();
    }

    public String getShBeiyong2() {
        return shBeiyong2;
    }

    public void setShBeiyong2(String shBeiyong2) {
        this.shBeiyong2 = shBeiyong2 == null ? null : shBeiyong2.trim();
    }

    public String getShBeiyong3() {
        return shBeiyong3;
    }

    public void setShBeiyong3(String shBeiyong3) {
        this.shBeiyong3 = shBeiyong3 == null ? null : shBeiyong3.trim();
    }

    public String getShHu() {
        return shHu;
    }

    public void setShHu(String shHu) {
        this.shHu = shHu;
    }

    public void clear() {
        this.subsidizedhouseholdsId = null;
        this.shName = null;
        this.shAddress = null;
        this.shEnjoythesubsidy = null;
        this.shFamilyinformation = null;
        this.shVillageid = null;
        this.shBeiyong1 = null;
        this.shBeiyong2 = null;
        this.shBeiyong3 = null;
        this.shHu = null;
    }
}