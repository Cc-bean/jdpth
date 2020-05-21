package com.hj.jdpth.domain;

import java.util.Date;

public class Policy {
    private Integer policyId;

    private Integer pType;

    private String pName;

    private String pDocumentpath;

    private Date pDatetime;

    private Integer pVillageid;

    private String pBeiyong1;

    private String pBeiyong2;

    private Integer pZhenid;

    private String pBeiyong3;

    private Integer pSheng;

    private Integer pShi;

    private Integer pRegion;

    private String leixinName;

    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", pType=" + pType +
                ", pName='" + pName + '\'' +
                ", pDocumentpath='" + pDocumentpath + '\'' +
                ", pDatetime=" + pDatetime +
                ", pVillageid=" + pVillageid +
                ", pBeiyong1='" + pBeiyong1 + '\'' +
                ", pBeiyong2='" + pBeiyong2 + '\'' +
                ", pZhenid=" + pZhenid +
                ", pBeiyong3='" + pBeiyong3 + '\'' +
                ", pSheng=" + pSheng +
                ", pShi=" + pShi +
                ", pRegion=" + pRegion +
                '}';
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpDocumentpath() {
        return pDocumentpath;
    }

    public void setpDocumentpath(String pDocumentpath) {
        this.pDocumentpath = pDocumentpath == null ? null : pDocumentpath.trim();
    }

    public Date getpDatetime() {
        return pDatetime;
    }

    public void setpDatetime(Date pDatetime) {
        this.pDatetime = pDatetime;
    }

    public Integer getpVillageid() {
        return pVillageid;
    }

    public void setpVillageid(Integer pVillageid) {
        this.pVillageid = pVillageid;
    }

    public String getpBeiyong1() {
        return pBeiyong1;
    }

    public void setpBeiyong1(String pBeiyong1) {
        this.pBeiyong1 = pBeiyong1 == null ? null : pBeiyong1.trim();
    }

    public String getpBeiyong2() {
        return pBeiyong2;
    }

    public void setpBeiyong2(String pBeiyong2) {
        this.pBeiyong2 = pBeiyong2 == null ? null : pBeiyong2.trim();
    }

    public Integer getpZhenid() {
        return pZhenid;
    }

    public void setpZhenid(Integer pZhenid) {
        this.pZhenid = pZhenid;
    }

    public String getpBeiyong3() {
        return pBeiyong3;
    }

    public void setpBeiyong3(String pBeiyong3) {
        this.pBeiyong3 = pBeiyong3 == null ? null : pBeiyong3.trim();
    }

    public Integer getpSheng() {
        return pSheng;
    }

    public void setpSheng(Integer pSheng) {
        this.pSheng = pSheng;
    }

    public Integer getpShi() {
        return pShi;
    }

    public void setpShi(Integer pShi) {
        this.pShi = pShi;
    }

    public Integer getpRegion() {
        return pRegion;
    }

    public void setpRegion(Integer pRegion) {
        this.pRegion = pRegion;
    }

    public String getLeixinName() {
        return leixinName;
    }

    public void setLeixinName(String leixinName) {
        this.leixinName = leixinName;
    }
}