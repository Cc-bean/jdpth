package com.hj.jdpth.domain;

import java.util.Date;

public class Partymemberinformation {
    private Integer partymemberinformationId;

    private Integer pmiPost;

    private String pmiDetails;

    private String pmiPhoto;

    private String pmiDegreeofeducation;

    private String pmiInauguralunit;

    private Date pmiJointime;

    private String pmJiguan;

    private String pmBeiyong1;

    private String pmBeiyong2;

    private String pmBeiyong3;

    private String pmBasic;

    private Date pmJoined;

    private Integer pmYonghu;

    private Integer pmVillage;

    public Integer getPartymemberinformationId() {
        return partymemberinformationId;
    }

    public void setPartymemberinformationId(Integer partymemberinformationId) {
        this.partymemberinformationId = partymemberinformationId;
    }

    public Integer getPmiPost() {
        return pmiPost;
    }

    public void setPmiPost(Integer pmiPost) {
        this.pmiPost = pmiPost;
    }

    public String getPmiDetails() {
        return pmiDetails;
    }

    public void setPmiDetails(String pmiDetails) {
        this.pmiDetails = pmiDetails == null ? null : pmiDetails.trim();
    }

    public String getPmiPhoto() {
        return pmiPhoto;
    }

    public void setPmiPhoto(String pmiPhoto) {
        this.pmiPhoto = pmiPhoto == null ? null : pmiPhoto.trim();
    }

    public String getPmiDegreeofeducation() {
        return pmiDegreeofeducation;
    }

    public void setPmiDegreeofeducation(String pmiDegreeofeducation) {
        this.pmiDegreeofeducation = pmiDegreeofeducation == null ? null : pmiDegreeofeducation.trim();
    }

    public String getPmiInauguralunit() {
        return pmiInauguralunit;
    }

    public void setPmiInauguralunit(String pmiInauguralunit) {
        this.pmiInauguralunit = pmiInauguralunit == null ? null : pmiInauguralunit.trim();
    }

    public Date getPmiJointime() {
        return pmiJointime;
    }

    public void setPmiJointime(Date pmiJointime) {
        this.pmiJointime = pmiJointime;
    }

    public String getPmJiguan() {
        return pmJiguan;
    }

    public void setPmJiguan(String pmJiguan) {
        this.pmJiguan = pmJiguan == null ? null : pmJiguan.trim();
    }

    public String getPmBeiyong1() {
        return pmBeiyong1;
    }

    public void setPmBeiyong1(String pmBeiyong1) {
        this.pmBeiyong1 = pmBeiyong1 == null ? null : pmBeiyong1.trim();
    }

    public String getPmBeiyong2() {
        return pmBeiyong2;
    }

    public void setPmBeiyong2(String pmBeiyong2) {
        this.pmBeiyong2 = pmBeiyong2 == null ? null : pmBeiyong2.trim();
    }

    public String getPmBeiyong3() {
        return pmBeiyong3;
    }

    public void setPmBeiyong3(String pmBeiyong3) {
        this.pmBeiyong3 = pmBeiyong3 == null ? null : pmBeiyong3.trim();
    }

    public String getPmBasic() {
        return pmBasic;
    }

    public void setPmBasic(String pmBasic) {
        this.pmBasic = pmBasic == null ? null : pmBasic.trim();
    }

    public Date getPmJoined() {
        return pmJoined;
    }

    public void setPmJoined(Date pmJoined) {
        this.pmJoined = pmJoined;
    }

    public Integer getPmYonghu() {
        return pmYonghu;
    }

    public void setPmYonghu(Integer pmYonghu) {
        this.pmYonghu = pmYonghu;
    }

    public Integer getPmVillage() {
        return pmVillage;
    }

    public void setPmVillage(Integer pmVillage) {
        this.pmVillage = pmVillage;
    }
}