package com.hj.jdpth.domain;

import java.util.Date;

public class Change {
    private Integer cKey;

    private Integer cId;

    private Date cBirth;

    private Date cIn;

    private Date cOut;

    private Date cDie;

    private Date cMiss;

    private String cOther;

    public Integer getcKey() {
        return cKey;
    }

    public void setcKey(Integer cKey) {
        this.cKey = cKey;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Date getcBirth() {
        return cBirth;
    }

    public void setcBirth(Date cBirth) {
        this.cBirth = cBirth;
    }

    public Date getcIn() {
        return cIn;
    }

    public void setcIn(Date cIn) {
        this.cIn = cIn;
    }

    public Date getcOut() {
        return cOut;
    }

    public void setcOut(Date cOut) {
        this.cOut = cOut;
    }

    public Date getcDie() {
        return cDie;
    }

    public void setcDie(Date cDie) {
        this.cDie = cDie;
    }

    public Date getcMiss() {
        return cMiss;
    }

    public void setcMiss(Date cMiss) {
        this.cMiss = cMiss;
    }

    public String getcOther() {
        return cOther;
    }

    public void setcOther(String cOther) {
        this.cOther = cOther == null ? null : cOther.trim();
    }
}