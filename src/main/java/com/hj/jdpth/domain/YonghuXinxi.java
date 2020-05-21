package com.hj.jdpth.domain;

public class YonghuXinxi {
    private Integer userId;//用户id

    private Integer userVillageid;//村ID

    private Integer userZu;//组

    private String userHu;//户主?户号

    private String userName;//姓名

    private String userSex;//性别

    private Integer userNation;//民族

    private String userEntityid;//身份证

    private String userPhone;//联系方式

    private String userEdu;//文化程度

    private Integer userPoliticalstatus;//政治面貌

    private String userBeiyong1;//序号

    private String userBeiyong2;

    private String userAddress;//住址

    private String userAccountid;//账号

    private String userPassword;//密码

    private Float userField;//田地

    private Integer userProvince;

    private Integer userShi;

    private Integer userRegion;

    private Integer userTownship;//乡镇

    private String userFamily;

    private String userBeiyong3;

    private String userHunumber;//家庭人口数量

    private String userChange;//变更

    private String userWorkingSpace;

    private String userJob;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserVillageid() {
        return userVillageid;
    }

    public void setUserVillageid(Integer userVillageid) {
        this.userVillageid = userVillageid;
    }

    public Integer getUserZu() {
        return userZu;
    }

    public void setUserZu(Integer userZu) {
        this.userZu = userZu;
    }

    public String getUserHu() {
        return userHu;
    }

    public void setUserHu(String userHu) {
        this.userHu = userHu == null ? null : userHu.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Integer getUserNation() {
        return userNation;
    }

    public void setUserNation(Integer userNation) {
        this.userNation = userNation;
    }

    public String getUserEntityid() {
        return userEntityid;
    }

    public void setUserEntityid(String userEntityid) {
        this.userEntityid = userEntityid == null ? null : userEntityid.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEdu() {
        return userEdu;
    }

    public void setUserEdu(String userEdu) {
        this.userEdu = userEdu == null ? null : userEdu.trim();
    }

    public Integer getUserPoliticalstatus() {
        return userPoliticalstatus;
    }

    public void setUserPoliticalstatus(Integer userPoliticalstatus) {
        this.userPoliticalstatus = userPoliticalstatus;
    }

    public String getUserBeiyong1() {
        return userBeiyong1;
    }

    public void setUserBeiyong1(String userBeiyong1) {
        this.userBeiyong1 = userBeiyong1 == null ? null : userBeiyong1.trim();
    }

    public String getUserBeiyong2() {
        return userBeiyong2;
    }

    public void setUserBeiyong2(String userBeiyong2) {
        this.userBeiyong2 = userBeiyong2 == null ? null : userBeiyong2.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserAccountid() {
        return userAccountid;
    }

    public void setUserAccountid(String userAccountid) {
        this.userAccountid = userAccountid == null ? null : userAccountid.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Float getUserField() {
        return userField;
    }

    public void setUserField(Float userField) {
        this.userField = userField;
    }

    public Integer getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(Integer userProvince) {
        this.userProvince = userProvince;
    }

    public Integer getUserShi() {
        return userShi;
    }

    public void setUserShi(Integer userShi) {
        this.userShi = userShi;
    }

    public Integer getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(Integer userRegion) {
        this.userRegion = userRegion;
    }

    public Integer getUserTownship() {
        return userTownship;
    }

    public void setUserTownship(Integer userTownship) {
        this.userTownship = userTownship;
    }

    public String getUserFamily() {
        return userFamily;
    }

    public void setUserFamily(String userFamily) {
        this.userFamily = userFamily == null ? null : userFamily.trim();
    }

    public String getUserBeiyong3() {
        return userBeiyong3;
    }

    public void setUserBeiyong3(String userBeiyong3) {
        this.userBeiyong3 = userBeiyong3 == null ? null : userBeiyong3.trim();
    }

    public String getUserHunumber() {
        return userHunumber;
    }

    public void setUserHunumber(String userHunumber) {
        this.userHunumber = userHunumber == null ? null : userHunumber.trim();
    }

    public String getUserChange() {
        return userChange;
    }

    public void setUserChange(String userChange) {
        this.userChange = userChange == null ? null : userChange.trim();
    }

    public String getUserWorkingSpace() {
        return userWorkingSpace;
    }

    public void setUserWorkingSpace(String userWorkingSpace) {
        this.userWorkingSpace = userWorkingSpace;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    @Override
    public String toString() {
        return "Yonghu{" +
                "userId=" + userId +
                ", userVillageid=" + userVillageid +
                ", userZu=" + userZu +
                ", userHu='" + userHu + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userNation=" + userNation +
                ", userEntityid='" + userEntityid + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEdu='" + userEdu + '\'' +
                ", userPoliticalstatus=" + userPoliticalstatus +
                ", userBeiyong1='" + userBeiyong1 + '\'' +
                ", userBeiyong2='" + userBeiyong2 + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userAccountid='" + userAccountid + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userField=" + userField +
                ", userProvince=" + userProvince +
                ", userShi=" + userShi +
                ", userRegion=" + userRegion +
                ", userTownship=" + userTownship +
                ", userFamily='" + userFamily + '\'' +
                ", userBeiyong3='" + userBeiyong3 + '\'' +
                ", userHunumber='" + userHunumber + '\'' +
                ", userChange='" + userChange + '\'' +
                '}';
    }
}