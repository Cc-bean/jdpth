package com.hj.jdpth.domain;

public class VillageManagementofthreecapital {
    private Integer vMotcId;

    private Integer vMotcVillageid;

    private String vMotcType;

    private String vMotcName;

    private String vMotcChildname;

    private String vMotcBeiyong1;

    private String vMotcBeiyong2;

    public Integer getvMotcId() {
        return vMotcId;
    }

    public void setvMotcId(Integer vMotcId) {
        this.vMotcId = vMotcId;
    }

    public Integer getvMotcVillageid() {
        return vMotcVillageid;
    }

    public void setvMotcVillageid(Integer vMotcVillageid) {
        this.vMotcVillageid = vMotcVillageid;
    }

    public String getvMotcType() {
        return vMotcType;
    }

    public void setvMotcType(String vMotcType) {
        this.vMotcType = vMotcType == null ? null : vMotcType.trim();
    }

    public String getvMotcName() {
        return vMotcName;
    }

    public void setvMotcName(String vMotcName) {
        this.vMotcName = vMotcName == null ? null : vMotcName.trim();
    }

    public String getvMotcChildname() {
        return vMotcChildname;
    }

    public void setvMotcChildname(String vMotcChildname) {
        this.vMotcChildname = vMotcChildname == null ? null : vMotcChildname.trim();
    }

    public String getvMotcBeiyong1() {
        return vMotcBeiyong1;
    }

    public void setvMotcBeiyong1(String vMotcBeiyong1) {
        this.vMotcBeiyong1 = vMotcBeiyong1 == null ? null : vMotcBeiyong1.trim();
    }

    public String getvMotcBeiyong2() {
        return vMotcBeiyong2;
    }

    public void setvMotcBeiyong2(String vMotcBeiyong2) {
        this.vMotcBeiyong2 = vMotcBeiyong2 == null ? null : vMotcBeiyong2.trim();
    }
}