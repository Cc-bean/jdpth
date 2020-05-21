package com.hj.jdpth.domain;

public class Huodongleixing {
    private Integer hdlxId;

    private String hdlxName;

    private Integer hdlxSheng;

    private Integer hdlxShi;

    private Integer hdlxQu;

    private Integer hdlxZhen;

    private Integer hdlxVillage;

    public Integer getHdlxId() {
        return hdlxId;
    }

    public void setHdlxId(Integer hdlxId) {
        this.hdlxId = hdlxId;
    }

    public String getHdlxName() {
        return hdlxName;
    }

    public void setHdlxName(String hdlxName) {
        this.hdlxName = hdlxName == null ? null : hdlxName.trim();
    }

    public Integer getHdlxSheng() {
        return hdlxSheng;
    }

    public void setHdlxSheng(Integer hdlxSheng) {
        this.hdlxSheng = hdlxSheng;
    }

    public Integer getHdlxShi() {
        return hdlxShi;
    }

    public void setHdlxShi(Integer hdlxShi) {
        this.hdlxShi = hdlxShi;
    }

    public Integer getHdlxQu() {
        return hdlxQu;
    }

    public void setHdlxQu(Integer hdlxQu) {
        this.hdlxQu = hdlxQu;
    }

    public Integer getHdlxZhen() {
        return hdlxZhen;
    }

    public void setHdlxZhen(Integer hdlxZhen) {
        this.hdlxZhen = hdlxZhen;
    }

    public Integer getHdlxVillage() {
        return hdlxVillage;
    }

    public void setHdlxVillage(Integer hdlxVillage) {
        this.hdlxVillage = hdlxVillage;
    }
}