package com.hj.jdpth.annotation;

import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}
