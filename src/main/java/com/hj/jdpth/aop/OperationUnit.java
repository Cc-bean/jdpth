package com.hj.jdpth.aop;

public enum OperationUnit {
    /**
     * 被操作的单元
     */
    UNKNOWN("未知"),
    MANAGER("管理员"),
    ASSETS("资产"),
    ASSETSDETAILS("资产详情"),
    BACKTYPE("反馈类型"),
    CAPITAL("资金"),
    CAPITALDETAILS("资金详情"),
    CHANGE("情况变更"),
    CUNFAZHAN("村发展"),
    CUNHUODONGLEIXING("村活动类型"),
    CUNZUZHIHUODONG("村活动"),
    DANGYUANZHUANZHENG("党员变更"),
    DANGYUANZUZHIHUODONG("党员组织活动"),
    DEPARTMENT("部门"),
    FAMILYINFORMATION("家属信息"),
    FAZHANDUIXIANG("发展对象"),
    FEEDBACK("反馈"),
    FEEDBACKREPLY("反馈回复"),
    HU("户"),
    HUKOUQIANYI("户口迁移"),
    HUKOUQIANYILEIXING("户口迁移类型"),
    HUODONGLEIXING("活动类型"),
    JIAOYV("教育资讯"),
    JIJIFENZI("积极分子"),
    JOB("职务"),
    JOURNAL("日志"),
    LAW("法律"),
    LAWTYPE("法律类型"),
    LICAIQINGKUANG("资金理财"),
    MANAGERTYPE("管理员类型"),
    NATION("民族"),
    ZUZHIFAZHAN("组织发展"),
    OPERATIONMANAGEMENT("项目建设"),
    PARTYMEMBERINFORMATION("党员信息"),
    POLICY("政策"),
    POLICYSTATU("政治面貌"),
    POLICYTYPE("政策类型"),
    QINGKUANGBIANGENG("党员情况变更"),
    QINGKUANGBIANGENGLEIXING("党员情况变更类型"),
    REGION("区级"),
    RESOURCES("资源"),
    RESOURCESDETAIL("资源详情"),
    RESOURCETYPE("资源类型"),
    SHENG("省"),
    SHI("市"),
    SUBSIDIZEDHOUSEHOLDS("补助户"),
    SUBSIDYINFORMATION("补助项信息"),
    SUBSIDTNAME("补助项名字"),
    SUBSIDYOBJECT("补助对象"),
    SUBSIDYTYPE("补助项类型"),
    SUPERVISORYOBJECT("监察对象"),
    SUPERVISORYOBJECTSOCIALRELATIONS("监察对象社会关系"),
    VILLAGE("行政村"),
    VILLAGEAFFAIR("行政村事务"),
    VILLAGEBULLETIN("村公告栏"),
    VILLAGECADRES("村情村貌村干部信息"),
    VILLAGEINFORMATION("村庄信息公开"),
    VILLAGEMANAGEMENTOFTHREECAPITAL("行政村资金三资管理"),
    VILLAGESURVEY("村情村貌行政村概况"),
    YONGHU("行政村村民"),
    YVBEIDANGYUAN("预备党员"),
    ZHEN("镇"),
    ZICHANJINGYING("资产经营"),
    ZICHANMINGZI("资产名字"),
    ZICHANTAIZHANG("资产台账"),
    ZU("组"),
    ZUZHIFAZHANYEAR("组织发展年份"),
    ZICHANMINGZIASSETS("资产名字,资产"),
    RESOURCESDETAILRESOURCES("资源详情资源"),
    CAPITALANDCAPITALDETAILS("资金，资金详情");


    private String value;

    OperationUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
