package com.hj.jdpth.domain;

public class Village {
    private Integer villageId;

    private String vPopulation;

    private String vImagepath1;

    private String vImagepath2;

    private String vImagepath3;

    private Float vLandship;

    private Float vArea;//多大

    private Integer vHouses;

    private String vAdress;//地址

    private Integer vShi;//市

    private Integer vRegion;//区

    private Integer vProvince;//省

    private String vName;//行政村名称

    private String vBeiyong1;

    private String vBeiyong2;

    private String vBeiyong3;

    private Integer vZhenXiang;//镇乡

    private Villagesurvey villagesurvey;

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public String getvPopulation() {
        return vPopulation;
    }

    public void setvPopulation(String vPopulation) {
        this.vPopulation = vPopulation == null ? null : vPopulation.trim();
    }

    public String getvImagepath1() {
        return vImagepath1;
    }

    public void setvImagepath1(String vImagepath1) {
        this.vImagepath1 = vImagepath1 == null ? null : vImagepath1.trim();
    }

    public String getvImagepath2() {
        return vImagepath2;
    }

    public void setvImagepath2(String vImagepath2) {
        this.vImagepath2 = vImagepath2 == null ? null : vImagepath2.trim();
    }

    public String getvImagepath3() {
        return vImagepath3;
    }

    public void setvImagepath3(String vImagepath3) {
        this.vImagepath3 = vImagepath3 == null ? null : vImagepath3.trim();
    }

    public Float getvLandship() {
        return vLandship;
    }

    public void setvLandship(Float vLandship) {
        this.vLandship = vLandship;
    }

    public Float getvArea() {
        return vArea;
    }

    public void setvArea(Float vArea) {
        this.vArea = vArea;
    }

    public Integer getvHouses() {
        return vHouses;
    }

    public void setvHouses(Integer vHouses) {
        this.vHouses = vHouses;
    }

    public String getvAdress() {
        return vAdress;
    }

    public void setvAdress(String vAdress) {
        this.vAdress = vAdress == null ? null : vAdress.trim();
    }

    public Integer getvShi() {
        return vShi;
    }

    public void setvShi(Integer vShi) {
        this.vShi = vShi;
    }

    public Integer getvRegion() {
        return vRegion;
    }

    public void setvRegion(Integer vRegion) {
        this.vRegion = vRegion;
    }

    public Integer getvProvince() {
        return vProvince;
    }

    public void setvProvince(Integer vProvince) {
        this.vProvince = vProvince;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName == null ? null : vName.trim();
    }

    public String getvBeiyong1() {
        return vBeiyong1;
    }

    public void setvBeiyong1(String vBeiyong1) {
        this.vBeiyong1 = vBeiyong1 == null ? null : vBeiyong1.trim();
    }

    public String getvBeiyong2() {
        return vBeiyong2;
    }

    public void setvBeiyong2(String vBeiyong2) {
        this.vBeiyong2 = vBeiyong2 == null ? null : vBeiyong2.trim();
    }

    public String getvBeiyong3() {
        return vBeiyong3;
    }

    public void setvBeiyong3(String vBeiyong3) {
        this.vBeiyong3 = vBeiyong3 == null ? null : vBeiyong3.trim();
    }

    public Integer getvZhenXiang() {
        return vZhenXiang;
    }

    public void setvZhenXiang(Integer vZhenXiang) {
        this.vZhenXiang = vZhenXiang;
    }

    public Villagesurvey getVillagesurvey() {
        return villagesurvey;
    }

    public void setVillagesurvey(Villagesurvey villagesurvey) {
        this.villagesurvey = villagesurvey;
    }
}