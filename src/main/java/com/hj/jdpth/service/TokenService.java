package com.hj.jdpth.service;

import com.hj.jdpth.domain.Manager;

import javax.servlet.ServletContext;

public interface TokenService {
    //根据用户身份获取token
    public String getToken(Manager manager, ServletContext context);

    //验证用户登录情况
    public boolean checkSinger(ServletContext context, String accountMember);
}
