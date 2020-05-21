package com.hj.jdpth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Jijifenzi {
    private Integer jjfzId;

    private String jjfzName;

    private Integer jjfzNative;

    private Date jjfzTime;

    private String jjfzWenhua;

    private Integer jjfzVillage;

    private Integer jjfzZu;

    private String jjfzEntity;

    private String jjfzDanwei;

    private String jjfzZhiwu;

    private String jjfzSex;

    private String jjfzPhone;

    private Integer jjfzYear;

    private String jjfzShenfenzheng;

    private String jjfzXuhao;

    private Nation nation;

    public Integer getJjfzId() {
        return jjfzId;
    }

    public void setJjfzId(Integer jjfzId) {
        this.jjfzId = jjfzId;
    }

    public String getJjfzName() {
        return jjfzName;
    }

    public void setJjfzName(String jjfzName) {
        this.jjfzName = jjfzName == null ? null : jjfzName.trim();
    }

    public Integer getJjfzNative() {
        return jjfzNative;
    }

    public void setJjfzNative(Integer jjfzNative) {
        this.jjfzNative = jjfzNative;
    }

    public Date getJjfzTime() {
        return jjfzTime;
    }

    public void setJjfzTime(Date jjfzTime) {
        this.jjfzTime = jjfzTime;
    }

    public String getJjfzWenhua() {
        return jjfzWenhua;
    }

    public void setJjfzWenhua(String jjfzWenhua) {
        this.jjfzWenhua = jjfzWenhua == null ? null : jjfzWenhua.trim();
    }

    public Integer getJjfzVillage() {
        return jjfzVillage;
    }

    public void setJjfzVillage(Integer jjfzVillage) {
        this.jjfzVillage = jjfzVillage;
    }

    public Integer getJjfzZu() {
        return jjfzZu;
    }

    public void setJjfzZu(Integer jjfzZu) {
        this.jjfzZu = jjfzZu;
    }

    public String getJjfzEntity() {
        return jjfzEntity;
    }

    public void setJjfzEntity(String jjfzEntity) {
        this.jjfzEntity = jjfzEntity == null ? null : jjfzEntity.trim();
    }

    public String getJjfzDanwei() {
        return jjfzDanwei;
    }

    public void setJjfzDanwei(String jjfzDanwei) {
        this.jjfzDanwei = jjfzDanwei == null ? null : jjfzDanwei.trim();
    }

    public String getJjfzZhiwu() {
        return jjfzZhiwu;
    }

    public void setJjfzZhiwu(String jjfzZhiwu) {
        this.jjfzZhiwu = jjfzZhiwu == null ? null : jjfzZhiwu.trim();
    }

    public String getJjfzSex() {
        return jjfzSex;
    }

    public void setJjfzSex(String jjfzSex) {
        this.jjfzSex = jjfzSex == null ? null : jjfzSex.trim();
    }

    public String getJjfzPhone() {
        return jjfzPhone;
    }

    public void setJjfzPhone(String jjfzPhone) {
        this.jjfzPhone = jjfzPhone == null ? null : jjfzPhone.trim();
    }

    public Integer getJjfzYear() {
        return jjfzYear;
    }

    public void setJjfzYear(Integer jjfzYear) {
        this.jjfzYear = jjfzYear;
    }

    public String getJjfzShenfenzheng() {
        return jjfzShenfenzheng;
    }

    public void setJjfzShenfenzheng(String jjfzShenfenzheng) {
        this.jjfzShenfenzheng = jjfzShenfenzheng == null ? null : jjfzShenfenzheng.trim();
    }

    public String getJjfzXuhao() {
        return jjfzXuhao;
    }

    public void setJjfzXuhao(String jjfzXuhao) {
        this.jjfzXuhao = jjfzXuhao == null ? null : jjfzXuhao.trim();
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Jijifenzi{" +
                "jjfzId=" + jjfzId +
                ", jjfzName='" + jjfzName + '\'' +
                ", jjfzNative=" + jjfzNative +
                ", jjfzTime=" + jjfzTime +
                ", jjfzWenhua='" + jjfzWenhua + '\'' +
                ", jjfzVillage=" + jjfzVillage +
                ", jjfzZu=" + jjfzZu +
                ", jjfzEntity='" + jjfzEntity + '\'' +
                ", jjfzDanwei='" + jjfzDanwei + '\'' +
                ", jjfzZhiwu='" + jjfzZhiwu + '\'' +
                ", jjfzSex='" + jjfzSex + '\'' +
                ", jjfzPhone='" + jjfzPhone + '\'' +
                ", jjfzYear=" + jjfzYear +
                ", jjfzShenfenzheng='" + jjfzShenfenzheng + '\'' +
                ", jjfzXuhao='" + jjfzXuhao + '\'' +
                ", nation=" + nation +
                '}';
    }
}