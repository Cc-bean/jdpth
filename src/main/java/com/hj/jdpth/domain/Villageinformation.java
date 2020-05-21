package com.hj.jdpth.domain;

public class Villageinformation {
    private Integer villageinformationId;

    private Integer viVillageid;

    private String viType;

    private String viPopulation;

    private Integer viLandship;

    private Integer viHouses;

    private String viImagepath;

    private String viBeiyong1;

    private String viBeiyong2;

    private String viBeiyong3;

    public Integer getVillageinformationId() {
        return villageinformationId;
    }

    public void setVillageinformationId(Integer villageinformationId) {
        this.villageinformationId = villageinformationId;
    }

    public Integer getViVillageid() {
        return viVillageid;
    }

    public void setViVillageid(Integer viVillageid) {
        this.viVillageid = viVillageid;
    }

    public String getViType() {
        return viType;
    }

    public void setViType(String viType) {
        this.viType = viType == null ? null : viType.trim();
    }

    public String getViPopulation() {
        return viPopulation;
    }

    public void setViPopulation(String viPopulation) {
        this.viPopulation = viPopulation == null ? null : viPopulation.trim();
    }

    public Integer getViLandship() {
        return viLandship;
    }

    public void setViLandship(Integer viLandship) {
        this.viLandship = viLandship;
    }

    public Integer getViHouses() {
        return viHouses;
    }

    public void setViHouses(Integer viHouses) {
        this.viHouses = viHouses;
    }

    public String getViImagepath() {
        return viImagepath;
    }

    public void setViImagepath(String viImagepath) {
        this.viImagepath = viImagepath == null ? null : viImagepath.trim();
    }

    public String getViBeiyong1() {
        return viBeiyong1;
    }

    public void setViBeiyong1(String viBeiyong1) {
        this.viBeiyong1 = viBeiyong1 == null ? null : viBeiyong1.trim();
    }

    public String getViBeiyong2() {
        return viBeiyong2;
    }

    public void setViBeiyong2(String viBeiyong2) {
        this.viBeiyong2 = viBeiyong2 == null ? null : viBeiyong2.trim();
    }

    public String getViBeiyong3() {
        return viBeiyong3;
    }

    public void setViBeiyong3(String viBeiyong3) {
        this.viBeiyong3 = viBeiyong3 == null ? null : viBeiyong3.trim();
    }
}