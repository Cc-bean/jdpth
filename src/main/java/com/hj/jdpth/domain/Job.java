package com.hj.jdpth.domain;

public class Job {
    private Integer jKey;

    private String jName;

    private Integer jVillage;

    private String jBeiyong1;

    private String jBeiyong2;

    private String jBeiyong3;

    private String jBeiyong4;

    private String jBeiyong5;

    public Integer getjKey() {
        return jKey;
    }

    public void setjKey(Integer jKey) {
        this.jKey = jKey;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName == null ? null : jName.trim();
    }

    public Integer getjVillage() {
        return jVillage;
    }

    public void setjVillage(Integer jVillage) {
        this.jVillage = jVillage;
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

    public String getjBeiyong3() {
        return jBeiyong3;
    }

    public void setjBeiyong3(String jBeiyong3) {
        this.jBeiyong3 = jBeiyong3 == null ? null : jBeiyong3.trim();
    }

    public String getjBeiyong4() {
        return jBeiyong4;
    }

    public void setjBeiyong4(String jBeiyong4) {
        this.jBeiyong4 = jBeiyong4 == null ? null : jBeiyong4.trim();
    }

    public String getjBeiyong5() {
        return jBeiyong5;
    }

    public void setjBeiyong5(String jBeiyong5) {
        this.jBeiyong5 = jBeiyong5 == null ? null : jBeiyong5.trim();
    }
}