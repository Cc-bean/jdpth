package com.hj.jdpth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.service.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    public static final long EXPIRE = 1000 * 60 * 24; //过期时间

    //将管理员的id存入token
    @Override
    public String getToken(Manager manager, ServletContext servletContext) {
        String token = "";
        token = JWT.create()
                .withAudience(manager.getManagerId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(manager.getmPassword()));
        //存入context
        servletContext.setAttribute(manager.getmAccountnumber(), token);
        return token;
    }

    @Override
    public boolean checkSinger(ServletContext context, String accountMember) {
        if (context.getAttribute(accountMember) == null && context.getAttribute(accountMember).toString().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
