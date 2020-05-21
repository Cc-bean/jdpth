package com.hj.jdpth.domain;

public class Resourcesdetails {
    private Integer resourcesdetailsId;

    private Integer rdResourcesid;

    private String rdAdress;

    private Float rdAreacovered;

    private String rdDetails;

    private String rdName;

    private String rdBeiyong1;

    private String rdBeiyong2;

    private String rdBeiyong3;

    public Integer getResourcesdetailsId() {
        return resourcesdetailsId;
    }

    public void setResourcesdetailsId(Integer resourcesdetailsId) {
        this.resourcesdetailsId = resourcesdetailsId;
    }

    public Integer getRdResourcesid() {
        return rdResourcesid;
    }

    public void setRdResourcesid(Integer rdResourcesid) {
        this.rdResourcesid = rdResourcesid;
    }

    public String getRdAdress() {
        return rdAdress;
    }

    public void setRdAdress(String rdAdress) {
        this.rdAdress = rdAdress == null ? null : rdAdress.trim();
    }

    public Float getRdAreacovered() {
        return rdAreacovered;
    }

    public void setRdAreacovered(Float rdAreacovered) {
        this.rdAreacovered = rdAreacovered;
    }

    public String getRdDetails() {
        return rdDetails;
    }

    public void setRdDetails(String rdDetails) {
        this.rdDetails = rdDetails == null ? null : rdDetails.trim();
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName == null ? null : rdName.trim();
    }

    public String getRdBeiyong1() {
        return rdBeiyong1;
    }

    public void setRdBeiyong1(String rdBeiyong1) {
        this.rdBeiyong1 = rdBeiyong1 == null ? null : rdBeiyong1.trim();
    }

    public String getRdBeiyong2() {
        return rdBeiyong2;
    }

    public void setRdBeiyong2(String rdBeiyong2) {
        this.rdBeiyong2 = rdBeiyong2 == null ? null : rdBeiyong2.trim();
    }

    public String getRdBeiyong3() {
        return rdBeiyong3;
    }

    public void setRdBeiyong3(String rdBeiyong3) {
        this.rdBeiyong3 = rdBeiyong3 == null ? null : rdBeiyong3.trim();
    }

    @Override
    public String toString() {
        return "Resourcesdetails{" +
                "resourcesdetailsId=" + resourcesdetailsId +
                ", rdResourcesid=" + rdResourcesid +
                ", rdAdress='" + rdAdress + '\'' +
                ", rdAreacovered=" + rdAreacovered +
                ", rdDetails='" + rdDetails + '\'' +
                ", rdName='" + rdName + '\'' +
                ", rdBeiyong1='" + rdBeiyong1 + '\'' +
                ", rdBeiyong2='" + rdBeiyong2 + '\'' +
                ", rdBeiyong3='" + rdBeiyong3 + '\'' +
                '}';
    }
}