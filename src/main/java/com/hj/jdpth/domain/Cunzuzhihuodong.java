package com.hj.jdpth.domain;

import java.util.Date;

public class Cunzuzhihuodong {
    private Integer cdyzzhdId;

    private String cdyzzhdName;

    private String cdyzzhdContent;

    private String cdyzzhdPhoto;

    private String cdyzzhdZhenshilujing;

    private String cdyzzhdPlace;

    private Date cdyzzhdTime;

    private Integer cdyzzhdLeixing;

    private String cdyzzhdXuhao;

    public Integer getCdyzzhdId() {
        return cdyzzhdId;
    }

    public void setCdyzzhdId(Integer cdyzzhdId) {
        this.cdyzzhdId = cdyzzhdId;
    }

    public String getCdyzzhdName() {
        return cdyzzhdName;
    }

    public void setCdyzzhdName(String cdyzzhdName) {
        this.cdyzzhdName = cdyzzhdName == null ? null : cdyzzhdName.trim();
    }

    public String getCdyzzhdContent() {
        return cdyzzhdContent;
    }

    public void setCdyzzhdContent(String cdyzzhdContent) {
        this.cdyzzhdContent = cdyzzhdContent == null ? null : cdyzzhdContent.trim();
    }

    public String getCdyzzhdPhoto() {
        return cdyzzhdPhoto;
    }

    public void setCdyzzhdPhoto(String cdyzzhdPhoto) {
        this.cdyzzhdPhoto = cdyzzhdPhoto == null ? null : cdyzzhdPhoto.trim();
    }

    public String getCdyzzhdZhenshilujing() {
        return cdyzzhdZhenshilujing;
    }

    public void setCdyzzhdZhenshilujing(String cdyzzhdZhenshilujing) {
        this.cdyzzhdZhenshilujing = cdyzzhdZhenshilujing == null ? null : cdyzzhdZhenshilujing.trim();
    }

    public String getCdyzzhdPlace() {
        return cdyzzhdPlace;
    }

    public void setCdyzzhdPlace(String cdyzzhdPlace) {
        this.cdyzzhdPlace = cdyzzhdPlace == null ? null : cdyzzhdPlace.trim();
    }

    public Date getCdyzzhdTime() {
        return cdyzzhdTime;
    }

    public void setCdyzzhdTime(Date cdyzzhdTime) {
        this.cdyzzhdTime = cdyzzhdTime;
    }

    public Integer getCdyzzhdLeixing() {
        return cdyzzhdLeixing;
    }

    public void setCdyzzhdLeixing(Integer cdyzzhdLeixing) {
        this.cdyzzhdLeixing = cdyzzhdLeixing;
    }

    public String getCdyzzhdXuhao() {
        return cdyzzhdXuhao;
    }

    public void setCdyzzhdXuhao(String cdyzzhdXuhao) {
        this.cdyzzhdXuhao = cdyzzhdXuhao == null ? null : cdyzzhdXuhao.trim();
    }
}