package com.hj.jdpth.domain;

public class Licaiqingkuang {
    private Integer lcId;

    private String lcName;

    private Short lcYear;

    private Integer lcMonth;

    private String lcMonthname;

    private Integer lcZu;

    private String lcZhengshilujing;

    private String lcLujing;

    public Integer getLcId() {
        return lcId;
    }

    public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }

    public String getLcName() {
        return lcName;
    }

    public void setLcName(String lcName) {
        this.lcName = lcName == null ? null : lcName.trim();
    }

    public Short getLcYear() {
        return lcYear;
    }

    public void setLcYear(Short lcYear) {
        this.lcYear = lcYear;
    }

    public Integer getLcMonth() {
        return lcMonth;
    }

    public void setLcMonth(Integer lcMonth) {
        this.lcMonth = lcMonth;
    }

    public String getLcMonthname() {
        return lcMonthname;
    }

    public void setLcMonthname(String lcMonthname) {
        this.lcMonthname = lcMonthname == null ? null : lcMonthname.trim();
    }

    public Integer getLcZu() {
        return lcZu;
    }

    public void setLcZu(Integer lcZu) {
        this.lcZu = lcZu;
    }

    public String getLcZhengshilujing() {
        return lcZhengshilujing;
    }

    public void setLcZhengshilujing(String lcZhengshilujing) {
        this.lcZhengshilujing = lcZhengshilujing == null ? null : lcZhengshilujing.trim();
    }

    public String getLcLujing() {
        return lcLujing;
    }

    public void setLcLujing(String lcLujing) {
        this.lcLujing = lcLujing == null ? null : lcLujing.trim();
    }

    @Override
    public String toString() {
        return "Licaiqingkuang{" +
                "lcId=" + lcId +
                ", lcName='" + lcName + '\'' +
                ", lcYear=" + lcYear +
                ", lcMonth=" + lcMonth +
                ", lcMonthname='" + lcMonthname + '\'' +
                ", lcZu=" + lcZu +
                ", lcZhengshilujing='" + lcZhengshilujing + '\'' +
                ", lcLujing='" + lcLujing + '\'' +
                '}';
    }
}