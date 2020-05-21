package com.hj.jdpth.domain;

public class Assetsdetails {
    private Integer assetsdetailsId;

    private Integer adAssetsid;

    private String adName;

    private String adDetails;

    private String adBeiyong1;

    private String adBeiyong2;

    private String adMoney;

    public Integer getAssetsdetailsId() {
        return assetsdetailsId;
    }

    public void setAssetsdetailsId(Integer assetsdetailsId) {
        this.assetsdetailsId = assetsdetailsId;
    }

    public Integer getAdAssetsid() {
        return adAssetsid;
    }

    public void setAdAssetsid(Integer adAssetsid) {
        this.adAssetsid = adAssetsid;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public String getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(String adDetails) {
        this.adDetails = adDetails == null ? null : adDetails.trim();
    }

    public String getAdBeiyong1() {
        return adBeiyong1;
    }

    public void setAdBeiyong1(String adBeiyong1) {
        this.adBeiyong1 = adBeiyong1 == null ? null : adBeiyong1.trim();
    }

    public String getAdBeiyong2() {
        return adBeiyong2;
    }

    public void setAdBeiyong2(String adBeiyong2) {
        this.adBeiyong2 = adBeiyong2 == null ? null : adBeiyong2.trim();
    }

    public String getAdMoney() {
        return adMoney;
    }

    public void setAdMoney(String adMoney) {
        this.adMoney = adMoney == null ? null : adMoney.trim();
    }
}