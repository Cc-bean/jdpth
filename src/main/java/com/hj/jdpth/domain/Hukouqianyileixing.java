package com.hj.jdpth.domain;

public class Hukouqianyileixing {
    private Integer hkqylxId;

    private String hkqylxName;

    private Integer hkqylxSheng;

    private Integer hkqylxShi;

    private Integer hkqylxQu;

    private Integer hkqylxZhen;

    private Integer hkqylxVillage;

    public Integer getHkqylxId() {
        return hkqylxId;
    }

    public void setHkqylxId(Integer hkqylxId) {
        this.hkqylxId = hkqylxId;
    }

    public String getHkqylxName() {
        return hkqylxName;
    }

    public void setHkqylxName(String hkqylxName) {
        this.hkqylxName = hkqylxName == null ? null : hkqylxName.trim();
    }

    public Integer getHkqylxSheng() {
        return hkqylxSheng;
    }

    public void setHkqylxSheng(Integer hkqylxSheng) {
        this.hkqylxSheng = hkqylxSheng;
    }

    public Integer getHkqylxShi() {
        return hkqylxShi;
    }

    public void setHkqylxShi(Integer hkqylxShi) {
        this.hkqylxShi = hkqylxShi;
    }

    public Integer getHkqylxQu() {
        return hkqylxQu;
    }

    public void setHkqylxQu(Integer hkqylxQu) {
        this.hkqylxQu = hkqylxQu;
    }

    public Integer getHkqylxZhen() {
        return hkqylxZhen;
    }

    public void setHkqylxZhen(Integer hkqylxZhen) {
        this.hkqylxZhen = hkqylxZhen;
    }

    public Integer getHkqylxVillage() {
        return hkqylxVillage;
    }

    public void setHkqylxVillage(Integer hkqylxVillage) {
        this.hkqylxVillage = hkqylxVillage;
    }
}