package com.hj.jdpth.domain;

public class Department {
    private Integer departmentId;

    private String dName;

    private String dCompany;

    private String dBeiyong1;

    private String dBeiyong2;

    private String dBeiyong3;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    public String getdCompany() {
        return dCompany;
    }

    public void setdCompany(String dCompany) {
        this.dCompany = dCompany == null ? null : dCompany.trim();
    }

    public String getdBeiyong1() {
        return dBeiyong1;
    }

    public void setdBeiyong1(String dBeiyong1) {
        this.dBeiyong1 = dBeiyong1 == null ? null : dBeiyong1.trim();
    }

    public String getdBeiyong2() {
        return dBeiyong2;
    }

    public void setdBeiyong2(String dBeiyong2) {
        this.dBeiyong2 = dBeiyong2 == null ? null : dBeiyong2.trim();
    }

    public String getdBeiyong3() {
        return dBeiyong3;
    }

    public void setdBeiyong3(String dBeiyong3) {
        this.dBeiyong3 = dBeiyong3 == null ? null : dBeiyong3.trim();
    }
}