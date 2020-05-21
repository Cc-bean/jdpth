package com.hj.jdpth.domain;

public class Capital {
    private Integer capitalId;

    private String cType;

    private Integer cVillageid;

    private String cdIncome;

    private String cdExpenditure;

    private String cdBeiyong1;

    private String cdBeiyong2;

    private Integer cdZu;

    private String cdXuhao;

    public Integer getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
    }

    public Integer getcVillageid() {
        return cVillageid;
    }

    public void setcVillageid(Integer cVillageid) {
        this.cVillageid = cVillageid;
    }

    public String getCdIncome() {
        return cdIncome;
    }

    public void setCdIncome(String cdIncome) {
        this.cdIncome = cdIncome == null ? null : cdIncome.trim();
    }

    public String getCdExpenditure() {
        return cdExpenditure;
    }

    public void setCdExpenditure(String cdExpenditure) {
        this.cdExpenditure = cdExpenditure == null ? null : cdExpenditure.trim();
    }

    public String getCdBeiyong1() {
        return cdBeiyong1;
    }

    public void setCdBeiyong1(String cdBeiyong1) {
        this.cdBeiyong1 = cdBeiyong1 == null ? null : cdBeiyong1.trim();
    }

    public String getCdBeiyong2() {
        return cdBeiyong2;
    }

    public void setCdBeiyong2(String cdBeiyong2) {
        this.cdBeiyong2 = cdBeiyong2 == null ? null : cdBeiyong2.trim();
    }

    public Integer getCdZu() {
        return cdZu;
    }

    public void setCdZu(Integer cdZu) {
        this.cdZu = cdZu;
    }

    public String getCdXuhao() {
        return cdXuhao;
    }

    public void setCdXuhao(String cdXuhao) {
        this.cdXuhao = cdXuhao == null ? null : cdXuhao.trim();
    }

    @Override
    public String toString() {
        return "Capital{" +
                "capitalId=" + capitalId +
                ", cType='" + cType + '\'' +
                ", cVillageid=" + cVillageid +
                ", cdIncome='" + cdIncome + '\'' +
                ", cdExpenditure='" + cdExpenditure + '\'' +
                ", cdBeiyong1='" + cdBeiyong1 + '\'' +
                ", cdBeiyong2='" + cdBeiyong2 + '\'' +
                ", cdZu=" + cdZu +
                ", cdXuhao='" + cdXuhao + '\'' +
                '}';
    }
}