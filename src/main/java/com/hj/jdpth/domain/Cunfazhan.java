package com.hj.jdpth.domain;

public class Cunfazhan {
    private Integer cfzId;

    private String cfzContent;

    private Integer cfzVillage;

    private String cfzXuhao;

    public Integer getCfzId() {
        return cfzId;
    }

    public void setCfzId(Integer cfzId) {
        this.cfzId = cfzId;
    }

    public String getCfzContent() {
        return cfzContent;
    }

    public void setCfzContent(String cfzContent) {
        this.cfzContent = cfzContent == null ? null : cfzContent.trim();
    }

    public Integer getCfzVillage() {
        return cfzVillage;
    }

    public void setCfzVillage(Integer cfzVillage) {
        this.cfzVillage = cfzVillage;
    }

    public String getCfzXuhao() {
        return cfzXuhao;
    }

    public void setCfzXuhao(String cfzXuhao) {
        this.cfzXuhao = cfzXuhao == null ? null : cfzXuhao.trim();
    }
}