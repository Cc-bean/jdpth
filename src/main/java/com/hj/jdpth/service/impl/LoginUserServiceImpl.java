package com.hj.jdpth.service.impl;

import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.ManagerMapper;
import com.hj.jdpth.service.LoginUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUserServiceImpl implements LoginUseService {

    @Autowired
    ManagerMapper managerMapper;

    //定义处理登录的方法
    @Override
    public Map<String, Object> checkLogin(String mAccountnumber, String mPassword, Integer mType) {
        Map<String, Object> map = new HashMap<>();
        Manager manager = new Manager();
        try {
            manager = managerMapper.checkLogin(mAccountnumber, mPassword, mType);
            if (manager == null) {
                map.put("data", "无相关管理员信息！");
                map.put("state", "success");
            } else {
                map.put("data", manager);
                map.put("state", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "serror");
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> checkLLogin(String mAccountnumber, String mPassword) {
        Map<String, Object> map = new HashMap<>();
        try {
            HashMap hashMap = managerMapper.checkLLogin(mAccountnumber, mPassword);
            if (hashMap.isEmpty()) {
                map.put("data", "无相关管理员信息！");
                map.put("state", "success");
            } else {
                map.put("data", hashMap);
                map.put("state", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "error");
            map.put("state", "error");
        }
        return map;
    }
}
