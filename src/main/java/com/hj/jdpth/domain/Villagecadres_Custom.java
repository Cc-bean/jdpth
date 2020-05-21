package com.hj.jdpth.domain;

public class Villagecadres_Custom extends Villagecadres {

    private String jName;
    private Integer zhenId;

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public Integer getZhenId() {
        return zhenId;
    }

    public void setZhenId(Integer zhenId) {
        this.zhenId = zhenId;
    }

    @Override
    public String toString() {
        return "Villagecadres_Custom{" +
                "jName='" + jName + '\'' +
                ", zhenId=" + zhenId +
                '}';
    }
}

