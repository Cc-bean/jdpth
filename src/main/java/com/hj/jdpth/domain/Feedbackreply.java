package com.hj.jdpth.domain;

import java.util.Date;

public class Feedbackreply {
    private Integer feedbackreplyId;

    private Integer frFeedbackid;

    private Integer frPeople;

    private Date frTime;

    private String frContent;

    private String frBeiyong1;

    private String frBeiyong2;

    private String frBeiyong3;

    public Integer getFeedbackreplyId() {
        return feedbackreplyId;
    }

    public void setFeedbackreplyId(Integer feedbackreplyId) {
        this.feedbackreplyId = feedbackreplyId;
    }

    public Integer getFrFeedbackid() {
        return frFeedbackid;
    }

    public void setFrFeedbackid(Integer frFeedbackid) {
        this.frFeedbackid = frFeedbackid;
    }

    public Integer getFrPeople() {
        return frPeople;
    }

    public void setFrPeople(Integer frPeople) {
        this.frPeople = frPeople;
    }

    public Date getFrTime() {
        return frTime;
    }

    public void setFrTime(Date frTime) {
        this.frTime = frTime;
    }

    public String getFrContent() {
        return frContent;
    }

    public void setFrContent(String frContent) {
        this.frContent = frContent == null ? null : frContent.trim();
    }

    public String getFrBeiyong1() {
        return frBeiyong1;
    }

    public void setFrBeiyong1(String frBeiyong1) {
        this.frBeiyong1 = frBeiyong1 == null ? null : frBeiyong1.trim();
    }

    public String getFrBeiyong2() {
        return frBeiyong2;
    }

    public void setFrBeiyong2(String frBeiyong2) {
        this.frBeiyong2 = frBeiyong2 == null ? null : frBeiyong2.trim();
    }

    public String getFrBeiyong3() {
        return frBeiyong3;
    }

    public void setFrBeiyong3(String frBeiyong3) {
        this.frBeiyong3 = frBeiyong3 == null ? null : frBeiyong3.trim();
    }
}