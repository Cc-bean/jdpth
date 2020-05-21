package com.hj.jdpth.domain;

public class PartymemberinformationAndYonghu extends Partymemberinformation {
    private Yonghu yonghu;

    public Yonghu getYonghu() {
        return yonghu;
    }

    public void setYonghu(Yonghu yonghu) {
        this.yonghu = yonghu;
    }

    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    private String nation;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
