package com.hj.jdpth.domain;

import java.util.Date;

public class Dangyuanzuzhihuodong {
    private Integer dyzzhdId;

    private String dyzzhdName;

    private String dyzzhdContent;

    private String dyzzhdPhoto;

    private String dyzzhdZhenshilujing;

    private String dyzzhdPlace;

    private Date dyzzhdTime;

    private Integer dyzzhdVillageid;

    private Integer dyzzhdLeixing;

    private String dyzzhdXvhao;

    public Integer getDyzzhdId() {
        return dyzzhdId;
    }

    public void setDyzzhdId(Integer dyzzhdId) {
        this.dyzzhdId = dyzzhdId;
    }

    public String getDyzzhdName() {
        return dyzzhdName;
    }

    public void setDyzzhdName(String dyzzhdName) {
        this.dyzzhdName = dyzzhdName == null ? null : dyzzhdName.trim();
    }

    public String getDyzzhdContent() {
        return dyzzhdContent;
    }

    public void setDyzzhdContent(String dyzzhdContent) {
        this.dyzzhdContent = dyzzhdContent == null ? null : dyzzhdContent.trim();
    }

    public String getDyzzhdPhoto() {
        return dyzzhdPhoto;
    }

    public void setDyzzhdPhoto(String dyzzhdPhoto) {
        this.dyzzhdPhoto = dyzzhdPhoto == null ? null : dyzzhdPhoto.trim();
    }

    public String getDyzzhdZhenshilujing() {
        return dyzzhdZhenshilujing;
    }

    public void setDyzzhdZhenshilujing(String dyzzhdZhenshilujing) {
        this.dyzzhdZhenshilujing = dyzzhdZhenshilujing == null ? null : dyzzhdZhenshilujing.trim();
    }

    public String getDyzzhdPlace() {
        return dyzzhdPlace;
    }

    public void setDyzzhdPlace(String dyzzhdPlace) {
        this.dyzzhdPlace = dyzzhdPlace == null ? null : dyzzhdPlace.trim();
    }

    public Date getDyzzhdTime() {
        return dyzzhdTime;
    }

    public void setDyzzhdTime(Date dyzzhdTime) {
        this.dyzzhdTime = dyzzhdTime;
    }

    public Integer getDyzzhdVillageid() {
        return dyzzhdVillageid;
    }

    public void setDyzzhdVillageid(Integer dyzzhdVillageid) {
        this.dyzzhdVillageid = dyzzhdVillageid;
    }

    public Integer getDyzzhdLeixing() {
        return dyzzhdLeixing;
    }

    public void setDyzzhdLeixing(Integer dyzzhdLeixing) {
        this.dyzzhdLeixing = dyzzhdLeixing;
    }

    public String getDyzzhdXvhao() {
        return dyzzhdXvhao;
    }

    public void setDyzzhdXvhao(String dyzzhdXvhao) {
        this.dyzzhdXvhao = dyzzhdXvhao == null ? null : dyzzhdXvhao.trim();
    }
}