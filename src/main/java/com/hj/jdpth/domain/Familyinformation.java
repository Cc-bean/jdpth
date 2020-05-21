package com.hj.jdpth.domain;

public class Familyinformation {
    private Integer familyinformationId;

    private Integer fiPeopleid;

    private String fiName;

    private String fiPhone;

    private String fiRelation;

    private String fiSex;

    private String fiEntityid;

    private Integer fiPoliticalstatus;

    private Integer fiNation;

    private String fiBeiyong1;

    private String fiBeiyong2;

    private String fiBeiyong3;

    public Integer getFamilyinformationId() {
        return familyinformationId;
    }

    public void setFamilyinformationId(Integer familyinformationId) {
        this.familyinformationId = familyinformationId;
    }

    public Integer getFiPeopleid() {
        return fiPeopleid;
    }

    public void setFiPeopleid(Integer fiPeopleid) {
        this.fiPeopleid = fiPeopleid;
    }

    public String getFiName() {
        return fiName;
    }

    public void setFiName(String fiName) {
        this.fiName = fiName == null ? null : fiName.trim();
    }

    public String getFiPhone() {
        return fiPhone;
    }

    public void setFiPhone(String fiPhone) {
        this.fiPhone = fiPhone == null ? null : fiPhone.trim();
    }

    public String getFiRelation() {
        return fiRelation;
    }

    public void setFiRelation(String fiRelation) {
        this.fiRelation = fiRelation == null ? null : fiRelation.trim();
    }

    public String getFiSex() {
        return fiSex;
    }

    public void setFiSex(String fiSex) {
        this.fiSex = fiSex == null ? null : fiSex.trim();
    }

    public String getFiEntityid() {
        return fiEntityid;
    }

    public void setFiEntityid(String fiEntityid) {
        this.fiEntityid = fiEntityid == null ? null : fiEntityid.trim();
    }

    public Integer getFiPoliticalstatus() {
        return fiPoliticalstatus;
    }

    public void setFiPoliticalstatus(Integer fiPoliticalstatus) {
        this.fiPoliticalstatus = fiPoliticalstatus;
    }

    public Integer getFiNation() {
        return fiNation;
    }

    public void setFiNation(Integer fiNation) {
        this.fiNation = fiNation;
    }

    public String getFiBeiyong1() {
        return fiBeiyong1;
    }

    public void setFiBeiyong1(String fiBeiyong1) {
        this.fiBeiyong1 = fiBeiyong1 == null ? null : fiBeiyong1.trim();
    }

    public String getFiBeiyong2() {
        return fiBeiyong2;
    }

    public void setFiBeiyong2(String fiBeiyong2) {
        this.fiBeiyong2 = fiBeiyong2 == null ? null : fiBeiyong2.trim();
    }

    public String getFiBeiyong3() {
        return fiBeiyong3;
    }

    public void setFiBeiyong3(String fiBeiyong3) {
        this.fiBeiyong3 = fiBeiyong3 == null ? null : fiBeiyong3.trim();
    }
}