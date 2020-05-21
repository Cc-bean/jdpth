package com.hj.jdpth.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class Manager {
    private Integer managerId;

    private String mAccountnumber;

    private String mPassword;

    private String mName;

    private String mSex;

    private Integer mDepartmentid;

    private String mPhone;

    private Integer mPost;

    private Integer mVillageid;

    private Integer mZhenid;

    private String mBeiyong1;

    private String mBeiyong2;

    private Integer mShi;

    private Integer mRegion;

    private Integer mType;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getmAccountnumber() {
        return mAccountnumber;
    }

    public void setmAccountnumber(String mAccountnumber) {
        this.mAccountnumber = mAccountnumber == null ? null : mAccountnumber.trim();
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword == null ? null : mPassword.trim();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex == null ? null : mSex.trim();
    }

    public Integer getmDepartmentid() {
        return mDepartmentid;
    }

    public void setmDepartmentid(Integer mDepartmentid) {
        this.mDepartmentid = mDepartmentid;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone == null ? null : mPhone.trim();
    }

    public Integer getmPost() {
        return mPost;
    }

    public void setmPost(Integer mPost) {
        this.mPost = mPost;
    }

    public Integer getmVillageid() {
        return mVillageid;
    }

    public void setmVillageid(Integer mVillageid) {
        this.mVillageid = mVillageid;
    }

    public Integer getmZhenid() {
        return mZhenid;
    }

    public void setmZhenid(Integer mZhenid) {
        this.mZhenid = mZhenid;
    }

    public String getmBeiyong1() {
        return mBeiyong1;
    }

    public void setmBeiyong1(String mBeiyong1) {
        this.mBeiyong1 = mBeiyong1 == null ? null : mBeiyong1.trim();
    }

    public String getmBeiyong2() {
        return mBeiyong2;
    }

    public void setmBeiyong2(String mBeiyong2) {
        this.mBeiyong2 = mBeiyong2 == null ? null : mBeiyong2.trim();
    }

    public Integer getmShi() {
        return mShi;
    }

    public void setmShi(Integer mShi) {
        this.mShi = mShi;
    }

    public Integer getmRegion() {
        return mRegion;
    }

    public void setmRegion(Integer mRegion) {
        this.mRegion = mRegion;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }


}