package com.hj.jdpth.domain;

public class Policytype {
    private Integer policytypeId;

    private String ptName;

    private String ptBeiyong1;

    private String ptBeiyong2;

    private String ptBeiyong3;

    @Override
    public String toString() {
        return "Policytype{" +
                "policytypeId=" + policytypeId +
                ", ptName='" + ptName + '\'' +
                ", ptBeiyong1='" + ptBeiyong1 + '\'' +
                ", ptBeiyong2='" + ptBeiyong2 + '\'' +
                ", ptBeiyong3='" + ptBeiyong3 + '\'' +
                '}';
    }

    public Integer getPolicytypeId() {
        return policytypeId;
    }

    public void setPolicytypeId(Integer policytypeId) {
        this.policytypeId = policytypeId;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName == null ? null : ptName.trim();
    }

    public String getPtBeiyong1() {
        return ptBeiyong1;
    }

    public void setPtBeiyong1(String ptBeiyong1) {
        this.ptBeiyong1 = ptBeiyong1 == null ? null : ptBeiyong1.trim();
    }

    public String getPtBeiyong2() {
        return ptBeiyong2;
    }

    public void setPtBeiyong2(String ptBeiyong2) {
        this.ptBeiyong2 = ptBeiyong2 == null ? null : ptBeiyong2.trim();
    }

    public String getPtBeiyong3() {
        return ptBeiyong3;
    }

    public void setPtBeiyong3(String ptBeiyong3) {
        this.ptBeiyong3 = ptBeiyong3 == null ? null : ptBeiyong3.trim();
    }
}