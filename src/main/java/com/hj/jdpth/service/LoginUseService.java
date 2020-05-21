package com.hj.jdpth.service;

import java.util.Map;

public interface LoginUseService {


    public Map<String, Object> checkLogin(String mAccountnumber, String mPassword, Integer mType);

    public Map<String, Object> checkLLogin(String mAccountnumber, String mPassword);
}
