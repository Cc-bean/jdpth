package com.hj.jdpth.domain;

public class Law {
    private Integer lawId;

    private Integer lPeople;

    private String lTitle;

    private String lContent;

    private String lPubdate;

    private Integer lType;

    private String lBeiyong1;

    private String lBeiyong2;

    private String lBeiyong3;

    private String lBeiyong4;

    private String lBeiyong5;

    public Integer getLawId() {
        return lawId;
    }

    public void setLawId(Integer lawId) {
        this.lawId = lawId;
    }

    public Integer getlPeople() {
        return lPeople;
    }

    public void setlPeople(Integer lPeople) {
        this.lPeople = lPeople;
    }

    public String getlTitle() {
        return lTitle;
    }

    public void setlTitle(String lTitle) {
        this.lTitle = lTitle == null ? null : lTitle.trim();
    }

    public String getlContent() {
        return lContent;
    }

    public void setlContent(String lContent) {
        this.lContent = lContent == null ? null : lContent.trim();
    }

    public String getlPubdate() {
        return lPubdate;
    }

    public void setlPubdate(String lPubdate) {
        this.lPubdate = lPubdate == null ? null : lPubdate.trim();
    }

    public Integer getlType() {
        return lType;
    }

    public void setlType(Integer lType) {
        this.lType = lType;
    }

    public String getlBeiyong1() {
        return lBeiyong1;
    }

    public void setlBeiyong1(String lBeiyong1) {
        this.lBeiyong1 = lBeiyong1 == null ? null : lBeiyong1.trim();
    }

    public String getlBeiyong2() {
        return lBeiyong2;
    }

    public void setlBeiyong2(String lBeiyong2) {
        this.lBeiyong2 = lBeiyong2 == null ? null : lBeiyong2.trim();
    }

    public String getlBeiyong3() {
        return lBeiyong3;
    }

    public void setlBeiyong3(String lBeiyong3) {
        this.lBeiyong3 = lBeiyong3 == null ? null : lBeiyong3.trim();
    }

    public String getlBeiyong4() {
        return lBeiyong4;
    }

    public void setlBeiyong4(String lBeiyong4) {
        this.lBeiyong4 = lBeiyong4 == null ? null : lBeiyong4.trim();
    }

    public String getlBeiyong5() {
        return lBeiyong5;
    }

    public void setlBeiyong5(String lBeiyong5) {
        this.lBeiyong5 = lBeiyong5 == null ? null : lBeiyong5.trim();
    }
}