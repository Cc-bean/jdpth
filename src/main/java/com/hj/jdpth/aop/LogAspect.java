package com.hj.jdpth.aop;


import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.domain.Journal;
import com.hj.jdpth.repository.JournalMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    @Autowired
    JournalMapper journalMapper;

    @Pointcut("@annotation(com.hj.jdpth.annotation.OperationLogDetail)")
    public void operationLog() {
    }

    ;

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        try {
            res = joinPoint.proceed();
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint, res);
                System.out.println(joinPoint.getSourceLocation());
                System.out.println(joinPoint.getSignature().getName());
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Journal journal = new Journal();
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        try {
            if (annotation != null) {
                //日志记录具体的操作 三个内容
                journal.setjType(annotation.operationType().getValue() + annotation.operationUnit().getValue());
                try {
                    journal.setjPeopleid(Integer.valueOf(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            journal.setjTime(new Date());
            journalMapper.insertJournal(journal);
        } catch (Exception e) {
            System.out.println("日志添加失败");
        }


    }

    /**
     * 对当前登录用户和占位符处理
     *
     * @param argNames 方法参数名称数组
     * @param args     方法参数数组
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args) {

        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }
        //从参数中获取用户id
        if (map.containsKey("managerId")) {
            String yonghuId = String.valueOf(map.get("managerId"));
            return yonghuId;
        } else {
            return "noArg";
        }
    }


}
