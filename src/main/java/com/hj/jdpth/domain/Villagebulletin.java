package com.hj.jdpth.domain;

import java.util.Date;

public class Villagebulletin {
    private Integer villagebulletinId;

    private Integer vbLanchpersonid;

    private Integer vbVillageid;

    private String vbTitle;

    private String vbContent;

    private Date vbLanchtime;

    private String vbBeiyong1;

    private String vbBeiyong2;

    private String vbBeiyong3;

    private String vbXuhao;

    public Integer getVillagebulletinId() {
        return villagebulletinId;
    }

    public void setVillagebulletinId(Integer villagebulletinId) {
        this.villagebulletinId = villagebulletinId;
    }

    public Integer getVbLanchpersonid() {
        return vbLanchpersonid;
    }

    public void setVbLanchpersonid(Integer vbLanchpersonid) {
        this.vbLanchpersonid = vbLanchpersonid;
    }

    public Integer getVbVillageid() {
        return vbVillageid;
    }

    public void setVbVillageid(Integer vbVillageid) {
        this.vbVillageid = vbVillageid;
    }

    public String getVbTitle() {
        return vbTitle;
    }

    public void setVbTitle(String vbTitle) {
        this.vbTitle = vbTitle == null ? null : vbTitle.trim();
    }

    public String getVbContent() {
        return vbContent;
    }

    public void setVbContent(String vbContent) {
        this.vbContent = vbContent == null ? null : vbContent.trim();
    }

    public Date getVbLanchtime() {
        return vbLanchtime;
    }

    public void setVbLanchtime(Date vbLanchtime) {
        this.vbLanchtime = vbLanchtime;
    }

    public String getVbBeiyong1() {
        return vbBeiyong1;
    }

    public void setVbBeiyong1(String vbBeiyong1) {
        this.vbBeiyong1 = vbBeiyong1 == null ? null : vbBeiyong1.trim();
    }

    public String getVbBeiyong2() {
        return vbBeiyong2;
    }

    public void setVbBeiyong2(String vbBeiyong2) {
        this.vbBeiyong2 = vbBeiyong2 == null ? null : vbBeiyong2.trim();
    }

    public String getVbBeiyong3() {
        return vbBeiyong3;
    }

    public void setVbBeiyong3(String vbBeiyong3) {
        this.vbBeiyong3 = vbBeiyong3 == null ? null : vbBeiyong3.trim();
    }

    public String getVbXuhao() {
        return vbXuhao;
    }

    public void setVbXuhao(String vbXuhao) {
        this.vbXuhao = vbXuhao == null ? null : vbXuhao.trim();
    }
}