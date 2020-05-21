package com.hj.jdpth.aop;

public enum OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("未知"),
    DELETE("删除"),
    SELECT("查询"),
    UPDATE("更改"),
    INSERT("新增"),
    UPLOAD("上传"),
    IMPORT("导入");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}
