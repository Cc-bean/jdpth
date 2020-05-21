package com.hj.jdpth.domain;

public class Operationmanagement {
    private Integer operationmanagementId;

    private Integer omVillageid;//村名idx

    private String omName;//项目名称x

    private Boolean omImplementation;//是否实施

    private String omTenderingmaterial;//项目编号x

    private String omImplementingparty;//实施方x

    private String omSourceoffunds;//是否立项x

    private String omImplementtheplan;//是否招标X

    private String omAcceptanceparty;//验收方x

    private String omFollowupmanagement;//监理方x

    private String omDivisionofresponsibility;//工程支付情况x

    private String omType;//备注x

    private String omTmZhaobiaowendang;//招标文档

    private String omTmShishiwendang;//实施方文档

    private String omTmSourceoffundsfile;//资金来源文档

    private String omTmDocument;//=======================

    private String omTmAcceptancepartyfile;//验收方文档

    private String omTmFollowupmanagementfile;//后续管理文档

    private String omTmDivisionofresponsibilityfile;//责任划分文档

    private String omTmBeiyong1;

    private String omTmBeiyong2;

    private String omTmBeiyong3;

    private String omXuhao;//序号

    public Integer getOperationmanagementId() {
        return operationmanagementId;
    }

    public void setOperationmanagementId(Integer operationmanagementId) {
        this.operationmanagementId = operationmanagementId;
    }

    public Integer getOmVillageid() {
        return omVillageid;
    }

    public void setOmVillageid(Integer omVillageid) {
        this.omVillageid = omVillageid;
    }

    public String getOmName() {
        return omName;
    }

    public void setOmName(String omName) {
        this.omName = omName == null ? null : omName.trim();
    }

    public Boolean getOmImplementation() {
        return omImplementation;
    }

    public void setOmImplementation(Boolean omImplementation) {
        this.omImplementation = omImplementation;
    }

    public String getOmTenderingmaterial() {
        return omTenderingmaterial;
    }

    public void setOmTenderingmaterial(String omTenderingmaterial) {
        this.omTenderingmaterial = omTenderingmaterial == null ? null : omTenderingmaterial.trim();
    }

    public String getOmImplementingparty() {
        return omImplementingparty;
    }

    public void setOmImplementingparty(String omImplementingparty) {
        this.omImplementingparty = omImplementingparty == null ? null : omImplementingparty.trim();
    }

    public String getOmSourceoffunds() {
        return omSourceoffunds;
    }

    public void setOmSourceoffunds(String omSourceoffunds) {
        this.omSourceoffunds = omSourceoffunds == null ? null : omSourceoffunds.trim();
    }

    public String getOmImplementtheplan() {
        return omImplementtheplan;
    }

    public void setOmImplementtheplan(String omImplementtheplan) {
        this.omImplementtheplan = omImplementtheplan == null ? null : omImplementtheplan.trim();
    }

    public String getOmAcceptanceparty() {
        return omAcceptanceparty;
    }

    public void setOmAcceptanceparty(String omAcceptanceparty) {
        this.omAcceptanceparty = omAcceptanceparty == null ? null : omAcceptanceparty.trim();
    }

    public String getOmFollowupmanagement() {
        return omFollowupmanagement;
    }

    public void setOmFollowupmanagement(String omFollowupmanagement) {
        this.omFollowupmanagement = omFollowupmanagement == null ? null : omFollowupmanagement.trim();
    }

    public String getOmDivisionofresponsibility() {
        return omDivisionofresponsibility;
    }

    public void setOmDivisionofresponsibility(String omDivisionofresponsibility) {
        this.omDivisionofresponsibility = omDivisionofresponsibility == null ? null : omDivisionofresponsibility.trim();
    }

    public String getOmType() {
        return omType;
    }

    public void setOmType(String omType) {
        this.omType = omType == null ? null : omType.trim();
    }

    public String getOmTmZhaobiaowendang() {
        return omTmZhaobiaowendang;
    }

    public void setOmTmZhaobiaowendang(String omTmZhaobiaowendang) {
        this.omTmZhaobiaowendang = omTmZhaobiaowendang == null ? null : omTmZhaobiaowendang.trim();
    }

    public String getOmTmShishiwendang() {
        return omTmShishiwendang;
    }

    public void setOmTmShishiwendang(String omTmShishiwendang) {
        this.omTmShishiwendang = omTmShishiwendang == null ? null : omTmShishiwendang.trim();
    }

    public String getOmTmSourceoffundsfile() {
        return omTmSourceoffundsfile;
    }

    public void setOmTmSourceoffundsfile(String omTmSourceoffundsfile) {
        this.omTmSourceoffundsfile = omTmSourceoffundsfile == null ? null : omTmSourceoffundsfile.trim();
    }

    public String getOmTmDocument() {
        return omTmDocument;
    }

    public void setOmTmDocument(String omTmDocument) {
        this.omTmDocument = omTmDocument == null ? null : omTmDocument.trim();
    }

    public String getOmTmAcceptancepartyfile() {
        return omTmAcceptancepartyfile;
    }

    public void setOmTmAcceptancepartyfile(String omTmAcceptancepartyfile) {
        this.omTmAcceptancepartyfile = omTmAcceptancepartyfile == null ? null : omTmAcceptancepartyfile.trim();
    }

    public String getOmTmFollowupmanagementfile() {
        return omTmFollowupmanagementfile;
    }

    public void setOmTmFollowupmanagementfile(String omTmFollowupmanagementfile) {
        this.omTmFollowupmanagementfile = omTmFollowupmanagementfile == null ? null : omTmFollowupmanagementfile.trim();
    }

    public String getOmTmDivisionofresponsibilityfile() {
        return omTmDivisionofresponsibilityfile;
    }

    public void setOmTmDivisionofresponsibilityfile(String omTmDivisionofresponsibilityfile) {
        this.omTmDivisionofresponsibilityfile = omTmDivisionofresponsibilityfile == null ? null : omTmDivisionofresponsibilityfile.trim();
    }

    public String getOmTmBeiyong1() {
        return omTmBeiyong1;
    }

    public void setOmTmBeiyong1(String omTmBeiyong1) {
        this.omTmBeiyong1 = omTmBeiyong1 == null ? null : omTmBeiyong1.trim();
    }

    public String getOmTmBeiyong2() {
        return omTmBeiyong2;
    }

    public void setOmTmBeiyong2(String omTmBeiyong2) {
        this.omTmBeiyong2 = omTmBeiyong2 == null ? null : omTmBeiyong2.trim();
    }

    public String getOmTmBeiyong3() {
        return omTmBeiyong3;
    }

    public void setOmTmBeiyong3(String omTmBeiyong3) {
        this.omTmBeiyong3 = omTmBeiyong3 == null ? null : omTmBeiyong3.trim();
    }

    public String getOmXuhao() {
        return omXuhao;
    }

    public void setOmXuhao(String omXuhao) {
        this.omXuhao = omXuhao == null ? null : omXuhao.trim();
    }

    @Override
    public String toString() {
        return "Operationmanagement{" +
                "operationmanagementId=" + operationmanagementId +
                ", omVillageid=" + omVillageid +
                ", omName='" + omName + '\'' +
                ", omImplementation=" + omImplementation +
                ", omTenderingmaterial='" + omTenderingmaterial + '\'' +
                ", omImplementingparty='" + omImplementingparty + '\'' +
                ", omSourceoffunds='" + omSourceoffunds + '\'' +
                ", omImplementtheplan='" + omImplementtheplan + '\'' +
                ", omAcceptanceparty='" + omAcceptanceparty + '\'' +
                ", omFollowupmanagement='" + omFollowupmanagement + '\'' +
                ", omDivisionofresponsibility='" + omDivisionofresponsibility + '\'' +
                ", omType='" + omType + '\'' +
                ", omTmZhaobiaowendang='" + omTmZhaobiaowendang + '\'' +
                ", omTmShishiwendang='" + omTmShishiwendang + '\'' +
                ", omTmSourceoffundsfile='" + omTmSourceoffundsfile + '\'' +
                ", omTmDocument='" + omTmDocument + '\'' +
                ", omTmAcceptancepartyfile='" + omTmAcceptancepartyfile + '\'' +
                ", omTmFollowupmanagementfile='" + omTmFollowupmanagementfile + '\'' +
                ", omTmDivisionofresponsibilityfile='" + omTmDivisionofresponsibilityfile + '\'' +
                ", omTmBeiyong1='" + omTmBeiyong1 + '\'' +
                ", omTmBeiyong2='" + omTmBeiyong2 + '\'' +
                ", omTmBeiyong3='" + omTmBeiyong3 + '\'' +
                ", omXuhao='" + omXuhao + '\'' +
                '}';
    }
}