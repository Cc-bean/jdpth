package com.hj.jdpth.domain;

import javax.naming.Name;

public class YonghuCustom extends Yonghu {
    private String zuName;
    private String villageName;
    private String zhenName;
    private String nationName;
    private String regionName;
    private String shiName;
    private String shengName;
    private String politicalstatusName;

    @Override
    public String toString() {
        return super.toString() + "YonghuCustom{" +
                "zuName='" + zuName + '\'' +
                ", villageName='" + villageName + '\'' +
                ", zhenName='" + zhenName + '\'' +
                ", nationName='" + nationName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", shiName='" + shiName + '\'' +
                ", shengName='" + shengName + '\'' +
                ", politicalstatusName='" + politicalstatusName + '\'' +
                '}';
    }

    public String getPoliticalstatusName() {
        return politicalstatusName;
    }

    public void setPoliticalstatusName(String politicalstatusName) {
        this.politicalstatusName = politicalstatusName;
    }

    public String getZuName() {
        return zuName;
    }

    public void setZuName(String zuName) {
        this.zuName = zuName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getZhenName() {
        return zhenName;
    }

    public void setZhenName(String zhenName) {
        this.zhenName = zhenName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getShiName() {
        return shiName;
    }

    public void setShiName(String shiName) {
        this.shiName = shiName;
    }

    public String getShengName() {
        return shengName;
    }

    public void setShengName(String shengName) {
        this.shengName = shengName;
    }
}
