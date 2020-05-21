package com.hj.jdpth.domain;

public class Subsidyobject {
    private Integer subsidyobjectId;//对象ID

    private Integer soSubsidyid;//补助户Id外间参考补助户表，qtm

    private String soName;//补助对象名称

    private String soEnjoythesubsidy;//享受的补助（补助信息表主键）

    private String soBeiyong1;//性别

    private String soBeiyong2;//补助金额（物）

    private String soBeiyong3;//序号

    private String soBeiyong4;//（补助原因）

    private String soBeiyong5;//年份

    private Integer soYonghu;//用户Id

    private Integer soVillageid;//村ID

    private String soShenfenzheng;//身份证

    @Override
    public String toString() {
        return "Subsidyobject{" +
                "subsidyobjectId=" + subsidyobjectId +
                ", soSubsidyid=" + soSubsidyid +
                ", soName='" + soName + '\'' +
                ", soEnjoythesubsidy='" + soEnjoythesubsidy + '\'' +
                ", soBeiyong1='" + soBeiyong1 + '\'' +
                ", soBeiyong2='" + soBeiyong2 + '\'' +
                ", soBeiyong3='" + soBeiyong3 + '\'' +
                ", soBeiyong4='" + soBeiyong4 + '\'' +
                ", soBeiyong5='" + soBeiyong5 + '\'' +
                ", soYonghu=" + soYonghu +
                ", soVillageid=" + soVillageid +
                ", soShenfenzheng='" + soShenfenzheng + '\'' +
                '}';
    }

    public Integer getSubsidyobjectId() {
        return subsidyobjectId;
    }

    public void setSubsidyobjectId(Integer subsidyobjectId) {
        this.subsidyobjectId = subsidyobjectId;
    }

    public Integer getSoSubsidyid() {
        return soSubsidyid;
    }

    public void setSoSubsidyid(Integer soSubsidyid) {
        this.soSubsidyid = soSubsidyid;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName == null ? null : soName.trim();
    }

    public String getSoEnjoythesubsidy() {
        return soEnjoythesubsidy;
    }

    public void setSoEnjoythesubsidy(String soEnjoythesubsidy) {
        this.soEnjoythesubsidy = soEnjoythesubsidy == null ? null : soEnjoythesubsidy.trim();
    }

    public String getSoBeiyong1() {
        return soBeiyong1;
    }

    public void setSoBeiyong1(String soBeiyong1) {
        this.soBeiyong1 = soBeiyong1 == null ? null : soBeiyong1.trim();
    }

    public String getSoBeiyong2() {
        return soBeiyong2;
    }

    public void setSoBeiyong2(String soBeiyong2) {
        this.soBeiyong2 = soBeiyong2 == null ? null : soBeiyong2.trim();
    }

    public String getSoBeiyong3() {
        return soBeiyong3;
    }

    public void setSoBeiyong3(String soBeiyong3) {
        this.soBeiyong3 = soBeiyong3 == null ? null : soBeiyong3.trim();
    }

    public String getSoBeiyong4() {
        return soBeiyong4;
    }

    public void setSoBeiyong4(String soBeiyong4) {
        this.soBeiyong4 = soBeiyong4 == null ? null : soBeiyong4.trim();
    }

    public String getSoBeiyong5() {
        return soBeiyong5;
    }

    public void setSoBeiyong5(String soBeiyong5) {
        this.soBeiyong5 = soBeiyong5 == null ? null : soBeiyong5.trim();
    }

    public Integer getSoYonghu() {
        return soYonghu;
    }

    public void setSoYonghu(Integer soYonghu) {
        this.soYonghu = soYonghu;
    }

    public Integer getSoVillageid() {
        return soVillageid;
    }

    public void setSoVillageid(Integer soVillageid) {
        this.soVillageid = soVillageid;
    }

    public String getSoShenfenzheng() {
        return soShenfenzheng;
    }

    public void setSoShenfenzheng(String soShenfenzheng) {
        this.soShenfenzheng = soShenfenzheng == null ? null : soShenfenzheng.trim();
    }

    public void clear() {
        this.subsidyobjectId = null;
        this.soSubsidyid = null;
        this.soName = null;
        this.soEnjoythesubsidy = null;
        this.soBeiyong1 = null;
        this.soBeiyong2 = null;
        this.soBeiyong3 = null;
        this.soBeiyong4 = null;
        this.soBeiyong5 = null;
        this.soYonghu = null;
        this.soVillageid = null;
        this.soShenfenzheng = null;
    }
}