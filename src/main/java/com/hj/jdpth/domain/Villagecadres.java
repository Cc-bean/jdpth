package com.hj.jdpth.domain;

public class Villagecadres {
    private Integer villagecadresId;//干部ID

    private Integer vcVillageid;//村Id

    private Integer vcPost;//职务x

    private String vcPosts;//多职务

    private String vcName;//x

    private String vcPhone;//x

    private String vcSex;//x

    private String vcEntityid;//身份IDx

    private String vcBeiyong1;//入职年份x

    private String vcBeiyong2;//（图片路径）

    private String vcGongzi;//工资

    private String vcZhize;//职责

    private String vcOldimagename;//旧名字

    private String vcNewimagename;//新名字

    public String getVcOldimagename() {
        return vcOldimagename;
    }

    public void setVcOldimagename(String vcOldimagename) {
        this.vcOldimagename = vcOldimagename;
    }

    public String getVcNewimagename() {
        return vcNewimagename;
    }

    public void setVcNewimagename(String vcNewimagename) {
        this.vcNewimagename = vcNewimagename;
    }

    public Integer getVillagecadresId() {
        return villagecadresId;
    }

    public void setVillagecadresId(Integer villagecadresId) {
        this.villagecadresId = villagecadresId;
    }

    public Integer getVcVillageid() {
        return vcVillageid;
    }

    public void setVcVillageid(Integer vcVillageid) {
        this.vcVillageid = vcVillageid;
    }

    public Integer getVcPost() {
        return vcPost;
    }

    public void setVcPost(Integer vcPost) {
        this.vcPost = vcPost;
    }

    public String getVcName() {
        return vcName;
    }

    public void setVcName(String vcName) {
        this.vcName = vcName == null ? null : vcName.trim();
    }

    public String getVcPhone() {
        return vcPhone;
    }

    public void setVcPhone(String vcPhone) {
        this.vcPhone = vcPhone == null ? null : vcPhone.trim();
    }

    public String getVcSex() {
        return vcSex;
    }

    public void setVcSex(String vcSex) {
        this.vcSex = vcSex == null ? null : vcSex.trim();
    }

    public String getVcEntityid() {
        return vcEntityid;
    }

    public void setVcEntityid(String vcEntityid) {
        this.vcEntityid = vcEntityid == null ? null : vcEntityid.trim();
    }

    public String getVcBeiyong1() {
        return vcBeiyong1;
    }

    public void setVcBeiyong1(String vcBeiyong1) {
        this.vcBeiyong1 = vcBeiyong1 == null ? null : vcBeiyong1.trim();
    }

    public String getVcBeiyong2() {
        return vcBeiyong2;
    }

    public void setVcBeiyong2(String vcBeiyong2) {
        this.vcBeiyong2 = vcBeiyong2 == null ? null : vcBeiyong2.trim();
    }

    public String getVcGongzi() {
        return vcGongzi;
    }

    public void setVcGongzi(String vcGongzi) {
        this.vcGongzi = vcGongzi == null ? null : vcGongzi.trim();
    }

    public String getVcZhize() {
        return vcZhize;
    }

    public void setVcZhize(String vcZhize) {
        this.vcZhize = vcZhize == null ? null : vcZhize.trim();
    }

    public String getVcPosts() {
        return vcPosts;
    }

    public void setVcPosts(String vcPosts) {
        this.vcPosts = vcPosts;
    }

    @Override
    public String toString() {
        return "Villagecadres{" +
                "villagecadresId=" + villagecadresId +
                ", vcVillageid=" + vcVillageid +
                ", vcPost=" + vcPost +
                ", vcPosts='" + vcPosts + '\'' +
                ", vcName='" + vcName + '\'' +
                ", vcPhone='" + vcPhone + '\'' +
                ", vcSex='" + vcSex + '\'' +
                ", vcEntityid='" + vcEntityid + '\'' +
                ", vcBeiyong1='" + vcBeiyong1 + '\'' +
                ", vcBeiyong2='" + vcBeiyong2 + '\'' +
                ", vcGongzi='" + vcGongzi + '\'' +
                ", vcZhize='" + vcZhize + '\'' +
                ", vcOldimagename='" + vcOldimagename + '\'' +
                ", vcNewimagename='" + vcNewimagename + '\'' +
                '}';
    }

    public void clear() {
        this.villagecadresId = null;

        this.vcVillageid = null;

        this.vcPost = null;

        this.vcPosts = null;

        this.vcName = null;

        this.vcPhone = null;

        this.vcSex = null;

        this.vcEntityid = null;

        this.vcBeiyong1 = null;

        this.vcBeiyong2 = null;

        this.vcGongzi = null;

        this.vcZhize = null;

        this.vcOldimagename = null;

        this.vcNewimagename = null;
    }
}