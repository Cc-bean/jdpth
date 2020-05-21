package com.hj.jdpth.domain;

import java.util.Date;

public class Jiaoyv {
    private Integer jyId;

    private String jyLujing;

    private String jyZhenshilujing;

    private String jyNmaeoffile;

    private Integer jySheng;

    private Integer jyShi;

    private Integer jyQu;

    private Integer jyZhen;

    private Integer jyVillage;

    private Date jyTime;

    private Integer jyFangwen;

    private String jyPeople;

    public Integer getJyId() {
        return jyId;
    }

    public void setJyId(Integer jyId) {
        this.jyId = jyId;
    }

    public String getJyLujing() {
        return jyLujing;
    }

    public void setJyLujing(String jyLujing) {
        this.jyLujing = jyLujing == null ? null : jyLujing.trim();
    }

    public String getJyZhenshilujing() {
        return jyZhenshilujing;
    }

    public void setJyZhenshilujing(String jyZhenshilujing) {
        this.jyZhenshilujing = jyZhenshilujing == null ? null : jyZhenshilujing.trim();
    }

    public String getJyNmaeoffile() {
        return jyNmaeoffile;
    }

    public void setJyNmaeoffile(String jyNmaeoffile) {
        this.jyNmaeoffile = jyNmaeoffile == null ? null : jyNmaeoffile.trim();
    }

    public Integer getJySheng() {
        return jySheng;
    }

    public void setJySheng(Integer jySheng) {
        this.jySheng = jySheng;
    }

    public Integer getJyShi() {
        return jyShi;
    }

    public void setJyShi(Integer jyShi) {
        this.jyShi = jyShi;
    }

    public Integer getJyQu() {
        return jyQu;
    }

    public void setJyQu(Integer jyQu) {
        this.jyQu = jyQu;
    }

    public Integer getJyZhen() {
        return jyZhen;
    }

    public void setJyZhen(Integer jyZhen) {
        this.jyZhen = jyZhen;
    }

    public Integer getJyVillage() {
        return jyVillage;
    }

    public void setJyVillage(Integer jyVillage) {
        this.jyVillage = jyVillage;
    }

    public Date getJyTime() {
        return jyTime;
    }

    public void setJyTime(Date jyTime) {
        this.jyTime = jyTime;
    }

    public Integer getJyFangwen() {
        return jyFangwen;
    }

    public void setJyFangwen(Integer jyFangwen) {
        this.jyFangwen = jyFangwen;
    }

    public String getJyPeople() {
        return jyPeople;
    }

    public void setJyPeople(String jyPeople) {
        this.jyPeople = jyPeople == null ? null : jyPeople.trim();
    }

    @Override
    public String toString() {
        return "Jiaoyv{" +
                "jyId=" + jyId +
                ", jyLujing='" + jyLujing + '\'' +
                ", jyZhenshilujing='" + jyZhenshilujing + '\'' +
                ", jyNmaeoffile='" + jyNmaeoffile + '\'' +
                ", jySheng=" + jySheng +
                ", jyShi=" + jyShi +
                ", jyQu=" + jyQu +
                ", jyZhen=" + jyZhen +
                ", jyVillage=" + jyVillage +
                ", jyTime=" + jyTime +
                ", jyFangwen=" + jyFangwen +
                ", jyPeople='" + jyPeople + '\'' +
                '}';
    }
}