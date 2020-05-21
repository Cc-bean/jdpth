package com.hj.jdpth.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Capitaldetails {
    private Integer capitaldetailsId;

    private Integer cdZijinid;

    private BigDecimal cdIncome;

    private BigDecimal cdExpenditure;

    private Date cdTime;

    private String cdXiangqing;

    private String cdBeiyong1;

    private String cdBeiyong2;

    private String cdBeiyong3;

    private Integer cdZu;

    private String cdXuhao;

    public Integer getCapitaldetailsId() {
        return capitaldetailsId;
    }

    public void setCapitaldetailsId(Integer capitaldetailsId) {
        this.capitaldetailsId = capitaldetailsId;
    }

    public Integer getCdZijinid() {
        return cdZijinid;
    }

    public void setCdZijinid(Integer cdZijinid) {
        this.cdZijinid = cdZijinid;
    }

    public BigDecimal getCdIncome() {
        return cdIncome;
    }

    public void setCdIncome(BigDecimal cdIncome) {
        this.cdIncome = cdIncome;
    }

    public BigDecimal getCdExpenditure() {
        return cdExpenditure;
    }

    public void setCdExpenditure(BigDecimal cdExpenditure) {
        this.cdExpenditure = cdExpenditure;
    }

    public Date getCdTime() {
        return cdTime;
    }

    public void setCdTime(Date cdTime) {
        this.cdTime = cdTime;
    }

    public String getCdXiangqing() {
        return cdXiangqing;
    }

    public void setCdXiangqing(String cdXiangqing) {
        this.cdXiangqing = cdXiangqing == null ? null : cdXiangqing.trim();
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

    public String getCdBeiyong3() {
        return cdBeiyong3;
    }

    public void setCdBeiyong3(String cdBeiyong3) {
        this.cdBeiyong3 = cdBeiyong3 == null ? null : cdBeiyong3.trim();
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
        return "Capitaldetails{" +
                "capitaldetailsId=" + capitaldetailsId +
                ", cdZijinid=" + cdZijinid +
                ", cdIncome=" + cdIncome +
                ", cdExpenditure=" + cdExpenditure +
                ", cdTime=" + cdTime +
                ", cdXiangqing='" + cdXiangqing + '\'' +
                ", cdBeiyong1='" + cdBeiyong1 + '\'' +
                ", cdBeiyong2='" + cdBeiyong2 + '\'' +
                ", cdBeiyong3='" + cdBeiyong3 + '\'' +
                ", cdZu=" + cdZu +
                ", cdXuhao='" + cdXuhao + '\'' +
                '}';
    }
}