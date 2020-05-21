package com.hj.jdpth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Journal {
    private Integer journalId;

    private Integer jPeopleid;

    private String jType;

    private Date jTime;

    private String jBeiyong1;

    private String jBeiyong2;

    private String jTable;

    private String jBeiyong3;

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public Integer getjPeopleid() {
        return jPeopleid;
    }

    public void setjPeopleid(Integer jPeopleid) {
        this.jPeopleid = jPeopleid;
    }

    public String getjType() {
        return jType;
    }

    public void setjType(String jType) {
        this.jType = jType == null ? null : jType.trim();
    }

    public Date getjTime() {
        return jTime;
    }

    public void setjTime(Date jTime) {
        this.jTime = jTime;
    }

    public String getjBeiyong1() {
        return jBeiyong1;
    }

    public void setjBeiyong1(String jBeiyong1) {
        this.jBeiyong1 = jBeiyong1 == null ? null : jBeiyong1.trim();
    }

    public String getjBeiyong2() {
        return jBeiyong2;
    }

    public void setjBeiyong2(String jBeiyong2) {
        this.jBeiyong2 = jBeiyong2 == null ? null : jBeiyong2.trim();
    }

    public String getjTable() {
        return jTable;
    }

    public void setjTable(String jTable) {
        this.jTable = jTable == null ? null : jTable.trim();
    }

    public String getjBeiyong3() {
        return jBeiyong3;
    }

    public void setjBeiyong3(String jBeiyong3) {
        this.jBeiyong3 = jBeiyong3 == null ? null : jBeiyong3.trim();
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journalId=" + journalId +
                ", jPeopleid=" + jPeopleid +
                ", jType='" + jType + '\'' +
                ", jTime=" + jTime +
                ", jBeiyong1='" + jBeiyong1 + '\'' +
                ", jBeiyong2='" + jBeiyong2 + '\'' +
                ", jTable='" + jTable + '\'' +
                ", jBeiyong3='" + jBeiyong3 + '\'' +
                '}';
    }
}