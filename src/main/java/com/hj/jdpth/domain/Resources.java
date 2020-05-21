package com.hj.jdpth.domain;

public class Resources {
    private Integer resourcesId;

    private String rType;

    private Integer rVillageid;

    private String rBeiyong1;

    private String rBeiyong2;

    private String rBeiyong3;

    private String rBeiyong4;

    private Integer rZu;

    private String rXvhao;

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public Integer getrVillageid() {
        return rVillageid;
    }

    public void setrVillageid(Integer rVillageid) {
        this.rVillageid = rVillageid;
    }

    public String getrBeiyong1() {
        return rBeiyong1;
    }

    public void setrBeiyong1(String rBeiyong1) {
        this.rBeiyong1 = rBeiyong1 == null ? null : rBeiyong1.trim();
    }

    public String getrBeiyong2() {
        return rBeiyong2;
    }

    public void setrBeiyong2(String rBeiyong2) {
        this.rBeiyong2 = rBeiyong2 == null ? null : rBeiyong2.trim();
    }

    public String getrBeiyong3() {
        return rBeiyong3;
    }

    public void setrBeiyong3(String rBeiyong3) {
        this.rBeiyong3 = rBeiyong3 == null ? null : rBeiyong3.trim();
    }

    public String getrBeiyong4() {
        return rBeiyong4;
    }

    public void setrBeiyong4(String rBeiyong4) {
        this.rBeiyong4 = rBeiyong4 == null ? null : rBeiyong4.trim();
    }

    public Integer getrZu() {
        return rZu;
    }

    public void setrZu(Integer rZu) {
        this.rZu = rZu;
    }

    public String getrXvhao() {
        return rXvhao;
    }

    public void setrXvhao(String rXvhao) {
        this.rXvhao = rXvhao == null ? null : rXvhao.trim();
    }

    @Override
    public String toString() {
        return "Resources{" +
                "resourcesId=" + resourcesId +
                ", rType='" + rType + '\'' +
                ", rVillageid=" + rVillageid +
                ", rBeiyong1='" + rBeiyong1 + '\'' +
                ", rBeiyong2='" + rBeiyong2 + '\'' +
                ", rBeiyong3='" + rBeiyong3 + '\'' +
                ", rBeiyong4='" + rBeiyong4 + '\'' +
                ", rZu=" + rZu +
                ", rXvhao='" + rXvhao + '\'' +
                '}';
    }
}