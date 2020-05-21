package com.hj.jdpth.domain;

import java.util.Date;

public class Hukouqianyi {
    private Integer hkqyId;

    private Date hkqyTime;

    private String hkqyZhengming;

    private String hkqyDizhi;

    private String hkqyName;

    private String hkqySex;

    private String hkqyPhone;

    private String hkqyEntity;

    private Integer hkqyVillage;

    private Integer hkqyZu;

    private String hkqyWenhuachengdu;

    private Integer hkqyLeixing;

    private Integer hkqyMinzu;

    private String hkqyBeiyong1;

    private Zhen zhen;

    private Village village;

    public Integer getHkqyId() {
        return hkqyId;
    }

    public void setHkqyId(Integer hkqyId) {
        this.hkqyId = hkqyId;
    }

    public Date getHkqyTime() {
        return hkqyTime;
    }

    public void setHkqyTime(Date hkqyTime) {
        this.hkqyTime = hkqyTime;
    }

    public String getHkqyZhengming() {
        return hkqyZhengming;
    }

    public void setHkqyZhengming(String hkqyZhengming) {
        this.hkqyZhengming = hkqyZhengming == null ? null : hkqyZhengming.trim();
    }

    public String getHkqyDizhi() {
        return hkqyDizhi;
    }

    public void setHkqyDizhi(String hkqyDizhi) {
        this.hkqyDizhi = hkqyDizhi == null ? null : hkqyDizhi.trim();
    }

    public String getHkqyName() {
        return hkqyName;
    }

    public void setHkqyName(String hkqyName) {
        this.hkqyName = hkqyName == null ? null : hkqyName.trim();
    }

    public String getHkqySex() {
        return hkqySex;
    }

    public void setHkqySex(String hkqySex) {
        this.hkqySex = hkqySex == null ? null : hkqySex.trim();
    }

    public String getHkqyPhone() {
        return hkqyPhone;
    }

    public void setHkqyPhone(String hkqyPhone) {
        this.hkqyPhone = hkqyPhone == null ? null : hkqyPhone.trim();
    }

    public String getHkqyEntity() {
        return hkqyEntity;
    }

    public void setHkqyEntity(String hkqyEntity) {
        this.hkqyEntity = hkqyEntity == null ? null : hkqyEntity.trim();
    }

    public Integer getHkqyVillage() {
        return hkqyVillage;
    }

    public void setHkqyVillage(Integer hkqyVillage) {
        this.hkqyVillage = hkqyVillage;
    }

    public Integer getHkqyZu() {
        return hkqyZu;
    }

    public void setHkqyZu(Integer hkqyZu) {
        this.hkqyZu = hkqyZu;
    }

    public String getHkqyWenhuachengdu() {
        return hkqyWenhuachengdu;
    }

    public void setHkqyWenhuachengdu(String hkqyWenhuachengdu) {
        this.hkqyWenhuachengdu = hkqyWenhuachengdu == null ? null : hkqyWenhuachengdu.trim();
    }

    public Integer getHkqyLeixing() {
        return hkqyLeixing;
    }

    public void setHkqyLeixing(Integer hkqyLeixing) {
        this.hkqyLeixing = hkqyLeixing;
    }

    public Integer getHkqyMinzu() {
        return hkqyMinzu;
    }

    public void setHkqyMinzu(Integer hkqyMinzu) {
        this.hkqyMinzu = hkqyMinzu;
    }

    public String getHkqyBeiyong1() {
        return hkqyBeiyong1;
    }

    public void setHkqyBeiyong1(String hkqyBeiyong1) {
        this.hkqyBeiyong1 = hkqyBeiyong1 == null ? null : hkqyBeiyong1.trim();
    }

    public Zhen getZhen() {
        return zhen;
    }

    public void setZhen(Zhen zhen) {
        this.zhen = zhen;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    @Override
    public String toString() {
        return "Hukouqianyi{" +
                "hkqyId=" + hkqyId +
                ", hkqyTime=" + hkqyTime +
                ", hkqyZhengming='" + hkqyZhengming + '\'' +
                ", hkqyDizhi='" + hkqyDizhi + '\'' +
                ", hkqyName='" + hkqyName + '\'' +
                ", hkqySex='" + hkqySex + '\'' +
                ", hkqyPhone='" + hkqyPhone + '\'' +
                ", hkqyEntity='" + hkqyEntity + '\'' +
                ", hkqyVillage=" + hkqyVillage +
                ", hkqyZu=" + hkqyZu +
                ", hkqyWenhuachengdu='" + hkqyWenhuachengdu + '\'' +
                ", hkqyLeixing=" + hkqyLeixing +
                ", hkqyMinzu=" + hkqyMinzu +
                ", hkqyBeiyong1='" + hkqyBeiyong1 + '\'' +
                '}';
    }
}