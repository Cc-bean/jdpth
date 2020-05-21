package com.hj.jdpth.domain;


public class Assets {
    private Integer assetsId;

    private String aType;

    private Integer aVillageid;

    private String aBeiyong1;

    private String aBeiyong2;

    private Integer aZu;

    public Integer getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Integer assetsId) {
        this.assetsId = assetsId;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType == null ? null : aType.trim();
    }

    public Integer getaVillageid() {
        return aVillageid;
    }

    public void setaVillageid(Integer aVillageid) {
        this.aVillageid = aVillageid;
    }

    public String getaBeiyong1() {
        return aBeiyong1;
    }

    public void setaBeiyong1(String aBeiyong1) {
        this.aBeiyong1 = aBeiyong1 == null ? null : aBeiyong1.trim();
    }

    public String getaBeiyong2() {
        return aBeiyong2;
    }

    public void setaBeiyong2(String aBeiyong2) {
        this.aBeiyong2 = aBeiyong2 == null ? null : aBeiyong2.trim();
    }

    public Integer getaZu() {
        return aZu;
    }

    public void setaZu(Integer aZu) {
        this.aZu = aZu;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "assetsId=" + assetsId +
                ", aType='" + aType + '\'' +
                ", aVillageid=" + aVillageid +
                ", aBeiyong1='" + aBeiyong1 + '\'' +
                ", aBeiyong2='" + aBeiyong2 + '\'' +
                ", aZu=" + aZu +
                '}';
    }
}