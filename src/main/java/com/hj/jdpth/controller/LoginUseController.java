package com.hj.jdpth.controller;

import com.auth0.jwt.JWT;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.annotation.UserLoginToken;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.domain.Yonghu;
import com.hj.jdpth.repository.ManagerMapper;
import com.hj.jdpth.service.LoginUseService;
import com.hj.jdpth.service.TokenService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LoginUseController {
    @Autowired
    LoginUseService loginUseService;
    @Autowired
    TokenService tokenService;
    @Autowired
    ManagerMapper managerMapper;


    @PostMapping("/h/login")        //验证登录
    public Map<String, Object> checkLogin(@RequestParam String mAccountnumber,
                                          @RequestParam String mPassword,
                                          @RequestParam Integer mType) {
        Map<String, Object> map = new HashMap<>();
        if (mAccountnumber != null && mPassword != null && mType != null) {
            map = loginUseService.checkLogin(mAccountnumber, mPassword, mType);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping("/h/llogin")        //验证登录
    public Map<String, Object> checkLLogin(@RequestParam String mAccountnumber,
                                           @RequestParam String mPassword) {
        Map<String, Object> map = new HashMap<>();
        if (mAccountnumber != null && mPassword != null) {
            map = loginUseService.checkLLogin(mAccountnumber, mPassword);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    //token登录
    @PostMapping("/tlogin")
    public Map<String, Object> ctLogin(@RequestParam String mAccountnumber,
                                       @RequestParam String mPassword,
                                       @RequestParam Integer mType,
                                       HttpServletResponse response,
                                       ServletContext e) {
        Map<String, Object> map = new HashMap<>();
        Manager manager1 = managerMapper.checkLogin(mAccountnumber, mPassword, mType);
        if (manager1 == null) {
            map.put("message", "无用户信息");
            response.setStatus(204);
        } else {
            String token = tokenService.getToken(manager1, e);
            manager1.setmPassword(null);
            map.put("token", token);
            map.put("manager", manager1);
        }
        return map;
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

    //通过token来获取对应的用户信息
    @UserLoginToken
    @GetMapping("/w/getManager")
    public Map<String, Object> ccCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        String managerId = JWT.decode(token).getAudience().get(0);
        Manager manager = managerMapper.FindManager(Integer.valueOf(managerId));
        manager.setmPassword(null);
        map.put("manager", manager);
        return map;
    }


}
