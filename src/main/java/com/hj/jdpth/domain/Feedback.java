package com.hj.jdpth.domain;

import java.util.Date;

public class Feedback {
    private Integer feedbackId;

    private Integer fType;

    private String fAdmissibleunit;

    private String fTitle;

    private String fContent;

    private String fImagepath;

    private Integer fPeopleid;

    private Date fTime;

    private Boolean fHandle;

    private String fAdmissiblecontent;

    private String fBeiyong1;

    private String fBeiyong2;

    private Boolean fJudge;

    private Backtype backtype;

    private Feedbackreply feedbackreply;

    private Yonghu yonghu;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public String getfAdmissibleunit() {
        return fAdmissibleunit;
    }

    public void setfAdmissibleunit(String fAdmissibleunit) {
        this.fAdmissibleunit = fAdmissibleunit == null ? null : fAdmissibleunit.trim();
    }

    public String getfTitle() {
        return fTitle;
    }

    public void setfTitle(String fTitle) {
        this.fTitle = fTitle == null ? null : fTitle.trim();
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent == null ? null : fContent.trim();
    }

    public String getfImagepath() {
        return fImagepath;
    }

    public void setfImagepath(String fImagepath) {
        this.fImagepath = fImagepath == null ? null : fImagepath.trim();
    }

    public Integer getfPeopleid() {
        return fPeopleid;
    }

    public void setfPeopleid(Integer fPeopleid) {
        this.fPeopleid = fPeopleid;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    public Boolean getfHandle() {
        return fHandle;
    }

    public void setfHandle(Boolean fHandle) {
        this.fHandle = fHandle;
    }

    public String getfAdmissiblecontent() {
        return fAdmissiblecontent;
    }

    public void setfAdmissiblecontent(String fAdmissiblecontent) {
        this.fAdmissiblecontent = fAdmissiblecontent == null ? null : fAdmissiblecontent.trim();
    }

    public String getfBeiyong1() {
        return fBeiyong1;
    }

    public void setfBeiyong1(String fBeiyong1) {
        this.fBeiyong1 = fBeiyong1 == null ? null : fBeiyong1.trim();
    }

    public String getfBeiyong2() {
        return fBeiyong2;
    }

    public void setfBeiyong2(String fBeiyong2) {
        this.fBeiyong2 = fBeiyong2 == null ? null : fBeiyong2.trim();
    }

    public Boolean getfJudge() {
        return fJudge;
    }

    public void setfJudge(Boolean fJudge) {
        this.fJudge = fJudge;
    }

    public Backtype getBacktype() {
        return backtype;
    }

    public void setBacktype(Backtype backtype) {
        this.backtype = backtype;
    }

    public Feedbackreply getFeedbackreply() {
        return feedbackreply;
    }

    public void setFeedbackreply(Feedbackreply feedbackreply) {
        this.feedbackreply = feedbackreply;
    }

    public Yonghu getYonghu() {
        return yonghu;
    }

    public void setYonghu(Yonghu yonghu) {
        this.yonghu = yonghu;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", fType=" + fType +
                ", fAdmissibleunit='" + fAdmissibleunit + '\'' +
                ", fTitle='" + fTitle + '\'' +
                ", fContent='" + fContent + '\'' +
                ", fImagepath='" + fImagepath + '\'' +
                ", fPeopleid=" + fPeopleid +
                ", fTime=" + fTime +
                ", fHandle=" + fHandle +
                ", fAdmissiblecontent='" + fAdmissiblecontent + '\'' +
                ", fBeiyong1='" + fBeiyong1 + '\'' +
                ", fBeiyong2='" + fBeiyong2 + '\'' +
                ", fJudge=" + fJudge +
                ", backtype=" + backtype +
                ", feedbackreply=" + feedbackreply +
                '}';
    }
}