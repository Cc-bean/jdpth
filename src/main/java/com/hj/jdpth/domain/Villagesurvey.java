package com.hj.jdpth.domain;

public class Villagesurvey {
    private Integer villagesurveyId;

    private Integer vsVillageid;

    private String vsSurvey;

    private String vsVillagehead;

    private Integer vsPopulation;

    private String vsPlace;

    private String vsShuji;

    private String vsBeiyong1;

    private String vsBeiyong2;

    private String vsBeiyong3;

    private String vsXuhao;

    public Integer getVillagesurveyId() {
        return villagesurveyId;
    }

    public void setVillagesurveyId(Integer villagesurveyId) {
        this.villagesurveyId = villagesurveyId;
    }

    public Integer getVsVillageid() {
        return vsVillageid;
    }

    public void setVsVillageid(Integer vsVillageid) {
        this.vsVillageid = vsVillageid;
    }

    public String getVsSurvey() {
        return vsSurvey;
    }

    public void setVsSurvey(String vsSurvey) {
        this.vsSurvey = vsSurvey == null ? null : vsSurvey.trim();
    }

    public String getVsVillagehead() {
        return vsVillagehead;
    }

    public void setVsVillagehead(String vsVillagehead) {
        this.vsVillagehead = vsVillagehead == null ? null : vsVillagehead.trim();
    }

    public Integer getVsPopulation() {
        return vsPopulation;
    }

    public void setVsPopulation(Integer vsPopulation) {
        this.vsPopulation = vsPopulation;
    }

    public String getVsPlace() {
        return vsPlace;
    }

    public void setVsPlace(String vsPlace) {
        this.vsPlace = vsPlace == null ? null : vsPlace.trim();
    }

    public String getVsShuji() {
        return vsShuji;
    }

    public void setVsShuji(String vsShuji) {
        this.vsShuji = vsShuji == null ? null : vsShuji.trim();
    }

    public String getVsBeiyong1() {
        return vsBeiyong1;
    }

    public void setVsBeiyong1(String vsBeiyong1) {
        this.vsBeiyong1 = vsBeiyong1 == null ? null : vsBeiyong1.trim();
    }

    public String getVsBeiyong2() {
        return vsBeiyong2;
    }

    public void setVsBeiyong2(String vsBeiyong2) {
        this.vsBeiyong2 = vsBeiyong2 == null ? null : vsBeiyong2.trim();
    }

    public String getVsBeiyong3() {
        return vsBeiyong3;
    }

    public void setVsBeiyong3(String vsBeiyong3) {
        this.vsBeiyong3 = vsBeiyong3 == null ? null : vsBeiyong3.trim();
    }

    public String getVsXuhao() {
        return vsXuhao;
    }

    public void setVsXuhao(String vsXuhao) {
        this.vsXuhao = vsXuhao == null ? null : vsXuhao.trim();
    }
}