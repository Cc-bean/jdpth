package com.hj.jdpth.service.impl;

import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.ImportExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImportExcelServiceImpl implements ImportExcelService {
    @Autowired
    VillagecadresMapper villagecadresMapper;
    @Autowired
    YonghuMapper yonghuMapper;
    @Autowired
    SubsidynameMapper subsidynameMapper;
    @Autowired
    SubsidyinformationMapper subsidyinformationMapper;
    @Autowired
    SubsidyobjectMapper subsidyobjectMapper;
    @Autowired
    SubsidizedhouseholdsMapper subsidizedhouseholdsMapper;
    @Autowired
    SubsidytypeMapper subsidytypeMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    ZuMapper zuMapper;
    @Autowired
    HuMapper huMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    NationMapper nationMapper;
    @Autowired
    PolicyStatuMapper policyStatuMapper;
    @Autowired
    PolicyMapper policyMapper;


    //正则
    //手机
    private static String PHONE = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
    //身份证
    private static String CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
    //中文
    private static String NAME = "^[\\u4E00-\\u9FA5]+$";
    //简易年份
    private static String SYEAR = "^[1,2]\\d{3}";
    //年份
    private static String YEAR = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    //数字
    private static String NUMBER = "\\d+";
    //空白字符
    private static String BLANK = "\\s*";
    //excel2013
    private static String XLSX = "^.+\\.(?i)(xlsx)$";
    //excel2007
    private static String XLS = "^.+\\.(?i)(xls)$";

    //导入村干部信息
    @Override
    @Transactional
    public Map<String, Object> ImportVillagecadres(MultipartFile file) {

        //结果
        Map<String, Object> map = new HashMap<String, Object>();
        //异常
        Exception exception = new Exception();
        //错误
        List<String> Error = new ArrayList<String>();
        //工作区间
        Workbook workbook = getWorkbook(file);
        //村Id
        Integer cunId;
        //干部
        Villagecadres villagecadres = new Villagecadres();
        try {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                //跳过标题——第二行
                for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    //村ID代码-------------------------------------------------------------------------------------------
                    if (row.getCell(0) == null) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String id = row.getCell(0).getStringCellValue();
                    id = strClean(id);
                    if ("".equals(id)) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    cunId = Integer.parseInt(id);
                    String naemId = villageMapper.selectNameByID(cunId);
                    if (naemId == null || "".equals(naemId)) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码不存在，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    villagecadres.setVcVillageid(cunId);
                    //姓名-------------------------------------------------------------------------------------------
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    if (row.getCell(0) == null) {
                        Error.add("第" + (i + 1) + "行姓名未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    String name = row.getCell(1).getStringCellValue();
                    name = strClean(name);
                    if ("".equals(name)) {
                        Error.add("第" + (i + 1) + "行姓名未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!name.matches(NAME)) {
                        Error.add("第" + (i + 1) + "行姓名格式不正确(非汉字)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    villagecadres.setVcName(name);
                    //性别-------------------------------------------------------------------------------------------
                    if (row.getCell(2) == null) {

                        row.createCell(2);
                    }
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String sex = row.getCell(2).getStringCellValue();
                    sex = strClean(sex);
                    if (!"".equals(sex)) {

                        if ((!"男".equals(sex)) && (!"女".equals(sex))) {
                            Error.add("第" + (i + 1) + "行性别格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        villagecadres.setVcSex(sex);
                    }

                    //职位-------------------------------------------------------------------------------------------
                    if (row.getCell(3) == null) {
                        Error.add("第" + (i + 1) + "行职务未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String postString = row.getCell(3).getStringCellValue();
                    postString = strClean(postString);
                    if ("".equals(postString)) {
                        Error.add("第" + (i + 1) + "行职务未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }

                    villagecadres.setVcPosts(postString);
                    //数据库表新增一个字段， VC_Posts多职务，记得更改相应所有sql
                    //身份证-------------------------------------------------------------------------------------------
                    if (row.getCell(4) == null) {
                        row.createCell(4);
                    }
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String ID = row.getCell(4).getStringCellValue();
                    ID = strClean(ID);
                    if (!"".equals(ID)) {
                        if (!ID.matches(CARD)) {
                            Error.add("第" + (i + 1) + "行身份证号格式错误，前" + (i + 1) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        villagecadres.setVcEntityid(ID);
                    }

                    //电话-------------------------------------------------------------------------------------------
                    if (row.getCell(5) == null) {
                        Error.add("第" + (i + 1) + "行联系电话未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    String phone = row.getCell(5).getStringCellValue();
                    phone = strClean(phone);
                    if ("".equals(phone)) {
                        Error.add("第" + (i + 1) + "行联系电话未填，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!phone.matches(PHONE)) {
                        Error.add("第" + (i + 1) + "行联系电话格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    villagecadres.setVcPhone(phone);
                    //当选年份-------------------------------------------------------------------------------------------
                    if (row.getCell(6) == null) {

                        row.createCell(6);
                    }
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    String year = row.getCell(6).getStringCellValue();
                    year = strClean(year);
                    if (!"".equals(year)) {

                        if (!year.matches(YEAR) && !year.matches(SYEAR)) {
                            Error.add("第" + (i + 1) + "行当选年份格式：2008-08-08/2008，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        villagecadres.setVcBeiyong1(year);
                    }


                    //工资-------------------------------------------------------------------------------------------

                    if (row.getCell(7) == null) {

                        row.createCell(7);
                    }
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String gongzi = row.getCell(7).getStringCellValue();
                    gongzi = strClean(gongzi);
                    if (!"".equals(gongzi)) {

                        Double.parseDouble(gongzi);
                        villagecadres.setVcGongzi(gongzi);
                    }
                    //职责-------------------------------------------------------------------------------------------
                    if (row.getCell(8) == null) {
                        row.createCell(8);
                    }
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    String zhize = row.getCell(8).getStringCellValue();
                    zhize = strClean(zhize);
                    if (!"".equals(zhize)) {
                        villagecadres.setVcZhize(zhize);
                    }
                    //序号-------------------------------------------------------------------------------------------
                    if (row.getCell(9) == null) {

                        row.createCell(9);
                    }
                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                    String xuhao = row.getCell(9).getStringCellValue();
                    xuhao = strClean(xuhao);
                    if (!"".equals(xuhao)) {
                        if (!xuhao.matches(NUMBER)) {
                            Error.add("第" + (i + 1) + "行序号格式不正确（非数字），前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        villagecadres.setVcBeiyong2(xuhao);
                    }
                    //插入
                    try {
                        villagecadresMapper.insertDynamic(villagecadres);
                        villagecadres.clear();
                    } catch (Exception e) {
                        Error.add("未知性错误(身份证重复？)，前" + (i + 1) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            map.put("Error", Error);
            return map;
        }
    }
    //导入村民信息--11.7beat

//    @Override
//    @Transactional
//    public Map<String, Object> ImportYongHu(MultipartFile file) {
//        //结果
//        Map<String,Object> map=new HashMap<String,Object>();
//        //异常
//        Exception exception=new Exception();
//        //错误
//        List<String> Error=new ArrayList<String>();
//        //工作区间
//        Workbook workbook=getWorkbook(file);
//        //村民
//        Yonghu yonghu=new Yonghu();
//        try {
//            for (int s=0;s<workbook.getNumberOfSheets();s++){
//                Sheet sheet=workbook.getSheetAt(s);
//                for (int i=2;i<=sheet.getLastRowNum();i++) {
//                    Row row = sheet.getRow(i);
//                    if (row == null){
//                        continue;
//                    }
//
//                    //村------------------------------------------------------------------------------------------------
//                    if (row.getCell(0)==null){
//                        Error.add("第"+(i+1)+"行行政村名称ID代码未填写，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
//                    String id=row.getCell(0).getStringCellValue();
//                    id=strClean(id);
//                    if ("".equals(id)){
//                        Error.add("第"+(i+1)+"行行政村名称ID代码未填写，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    //判存
//                    Integer idx=Integer.parseInt(id);
//                    String naemId=villageMapper.selectNameByID(idx);
//                    if (naemId==null || naemId.equals("")){
//                        Error.add("第"+(i+1)+"行行政村名称ID代码不存在，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    //设置村
//                    yonghu.setUserVillageid(idx);
//                    //设置镇
//                    yonghu.setUserTownship(villageMapper.selectZhenID(idx));
//
//                    //组 -----------------------------------------------------------------------------------------------
//                    if (row.getCell(1)==null){
//                        row.createCell(1);
//                    }
//                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
//                    String zu=row.getCell(1).getStringCellValue();
//                    zu=strClean(zu);
//                    if (!"".equals(zu)){
//                        Integer zux=Integer.parseInt(zu);
//                        String nameID=zuMapper.selectNameByID(zux);
//                        nameID=strClean(nameID);
//                        //判存
//                        if ("".equals(nameID)){
//                            Error.add("第"+(i+1)+"行村组不存在，前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserZu(zux);
//                    }
//                    //户号----------------------------------------------------------------------------------------------
//                    if (row.getCell(2)==null){
//
//                        row.createCell(2);
//                    }
//                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
//                    String huhao=row.getCell(2).getStringCellValue();
//                    huhao=strClean(huhao);
//                    if(!"".equals(huhao)){
//                        if(!huhao.matches(NUMBER)){
//                            Error.add("第"+(i+1)+"行户号：非数字，前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserHu(huhao);
//                    }
//                    //姓名----------------------------------------------------------------------------------------------
//                    if (row.getCell(3)==null){
//                        Error.add("第"+(i+1)+"行姓名未填写1，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
//                    String name=row.getCell(3).getStringCellValue();
//                    name=strClean(name);
//                    if ("".equals(name)){
//                        Error.add("第"+(i+1)+"行姓名未填写2，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    if (!name.matches(NAME)){
//                        Error.add("第"+(i+1)+"行姓名格式不正确（非汉字），前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//                    yonghu.setUserName(name);
//                    //性别----------------------------------------------------------------------------------------------
//                    if (row.getCell(4)==null){
//
//                        row.createCell(4);
//                    }
//                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
//                    String sex=row.getCell(4).getStringCellValue();
//                    sex=strClean(sex);
//                    if (!"".equals(sex)){
//                        if (!"男".equals(sex)&&!"女".equals(sex)){
//                            Error.add("第"+(i+1)+"行性别格式不正确（男，女），前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserSex(sex);
//                    }
//
//                    //民族----------------------------------------------------------------------------------------------
//                    if (row.getCell(5)==null){
//
//                        row.createCell(5);
//                    }
//                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
//                    String nation=row.getCell(5).getStringCellValue();
//                    nation=strClean(nation);
//                    if (!"".equals(nation)){
//                                                //判存
//                        Integer nationID=nationMapper.selectIDBYName(nation);
//
//                        if (nationID==null){
//                            Error.add("第"+(i+1)+"行民族不存在（正确案例：汉族），前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserNation(nationID);
//                    }
//
//                    //身份证--------------------------------------------------------------------------------------------
//                    if (row.getCell(6)==null){
//                        row.createCell(6);
//                    }
//                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
//                    String ID=row.getCell(6).getStringCellValue();
//                    ID=strClean(ID);
//                    if(!"".equals(ID)){
//                        if (!ID.matches(CARD)){
//                            Error.add("第"+(i+1)+"行身份证号格式不正确，前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserEntityid(ID);
//                    }
//                    //电话----------------------------------------------------------------------------------------------
//                    if (row.getCell(7)==null){
//                        row.createCell(7);
//                    }
//                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
//                    String phone=row.getCell(7).getStringCellValue();
//                    phone=strClean(phone);
//                    if (!"".equals(phone)){
//                        if (!phone.matches(PHONE)){
//                            Error.add("第"+(i+1)+"行联系电话格式不正确，前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserPhone(phone);
//                        yonghu.setUserAccountid(phone);
//                    }
//                    //文化程度------------------------------------------------------------------------------------------
//                    if (row.getCell(8)==null){
//                        row.createCell(8);
//                    }
//                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
//                    String edu=row.getCell(8).getStringCellValue();
//                    edu=strClean(edu);
//                    if (!edu.equals("")){
//                        yonghu.setUserEdu(edu);
//                    }
//
//                    //政治面貌------------------------------------------------------------------------------------------
//                    if (row.getCell(9)==null){
//
//                        row.createCell(9);
//                    }
//                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
//                    String status=row.getCell(9).getStringCellValue();
//                    status=strClean(status);
//                    if (!"".equals(status)){
//                        //判存
//                        Integer nameID=policyStatuMapper.seletIDBYName(status);
//                        if (nameID==null || nameID==0){
//                            Error.add("第"+(i+1)+"行政治面貌格式（党员，群众，共青团员，预备党员），前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserPoliticalstatus(nameID);
//
//                    }
//                    //住址----------------------------------------------------------------------------------------------
//                    if (row.getCell(10)==null){
//                        row.createCell(10);
//                    }
//                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
//                    String address=row.getCell(10).getStringCellValue();
//                    address=strClean(address);
//                    if (!"".equals(address)){
//                        yonghu.setUserAddress(address);
//                    }
//                    //变更----------------------------------------------------------------------------------------------
//                    if (row.getCell(11)==null){
//                        row.createCell(11);
//                    }
//                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
//                    String change=row.getCell(11).getStringCellValue();
//                    change=strClean(change);
//                    if (!"".equals(change)){
//                        yonghu.setUserChange(change);
//                    }
//                    //序号----------------------------------------------------------------------------------------------
//                    if (row.getCell(12)==null){
//                        row.createCell(12);
//                    }
//                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
//                    String xuhao=row.getCell(12).getStringCellValue();
//                    xuhao=strClean(xuhao);
//                    if (!"".equals(xuhao)){
//                        if (!xuhao.matches(NUMBER)){
//                            Error.add("第"+(i+1)+"行序号格式:数字，前"+(i)+"行已成功导入，请勿重复导入");
//                            throw exception;
//                        }
//                        yonghu.setUserBeiyong1(xuhao);
//                    }
//
//                    //插入
//                    try{
//                        yonghuMapper.insertYongHu(yonghu);
//                        System.out.println("插入"+yonghu);
//                        yonghu.clear();
//                    }catch (Exception e){
//                        Error.add("未知性错误(身份证重复？)，前"+(i)+"行已成功导入，请勿重复导入");
//                        throw exception;
//                    }
//
//
//                }
//            }
//        } catch (Exception e){
//            throw new RuntimeException();
//        }finally {
//            map.put("Error",Error);
//            return map;
//        }
//
//    }


    //导入账号
    @Override
    @Transactional
    public Map<String, Object> ImportYongHu(MultipartFile file) {
        //结果
        Map<String, Object> map = new HashMap<String, Object>();
        //异常
        Exception exception = new Exception();
        //错误
        List<String> Error = new ArrayList<String>();
        //工作区间
        Workbook workbook = getWorkbook(file);
        //村民
        Yonghu yonghu = new Yonghu();
        try {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }

                    //村------------------------------------------------------------------------------------------------
                    if (row.getCell(0) == null) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String id = row.getCell(0).getStringCellValue();
                    id = strClean(id);
                    if ("".equals(id)) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    Integer idx = Integer.parseInt(id);
                    String naemId = villageMapper.selectNameByID(idx);
                    if (naemId == null || naemId.equals("")) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码不存在，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //设置村
                    yonghu.setUserVillageid(idx);
                    //设置镇
                    yonghu.setUserTownship(villageMapper.selectZhenID(idx));

                    //姓名----------------------------------------------------------------------------------------------
                    if (row.getCell(1) == null) {
                        Error.add("第" + (i + 1) + "行姓名未填写1，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String name = row.getCell(1).getStringCellValue();
                    name = strClean(name);
                    if ("".equals(name)) {
                        Error.add("第" + (i + 1) + "行姓名未填写2，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!name.matches(NAME)) {
                        Error.add("第" + (i + 1) + "行姓名格式不正确（非汉字），前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    yonghu.setUserName(name);
                    //性别----------------------------------------------------------------------------------------------
                    if (row.getCell(2) == null) {
                        row.createCell(2);
                    }
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String sex = row.getCell(2).getStringCellValue();
                    sex = strClean(sex);
                    if (!"".equals(sex)) {
                        if (!"男".equals(sex) && !"女".equals(sex)) {
                            Error.add("第" + (i + 1) + "行性别格式不正确（男，女），前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        yonghu.setUserSex(sex);
                    }


                    //身份证--------------------------------------------------------------------------------------------
                    if (row.getCell(3) == null) {
                        row.createCell(3);
                    }
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String ID = row.getCell(3).getStringCellValue();
                    ID = strClean(ID);
                    if (!"".equals(ID)) {
                        if (!ID.matches(CARD)) {
                            Error.add("第" + (i + 1) + "行身份证号格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        yonghu.setUserEntityid(ID);
                    }
                    //电话----------------------------------------------------------------------------------------------
                    if (row.getCell(4) == null) {
                        Error.add("第" + (i + 1) + "行手机号未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String phone = row.getCell(4).getStringCellValue();
                    phone = strClean(phone);
                    if (!"".equals(phone)) {
                        if (!phone.matches(PHONE)) {
                            Error.add("第" + (i + 1) + "行联系电话（固定电话请手动添加）格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        yonghu.setUserPhone(phone);
                        yonghu.setUserAccountid(phone);
                    } else {
                        Error.add("第" + (i + 1) + "行手机号未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //插入
                    try {
                        yonghuMapper.insertYongHu(yonghu);
                        System.out.println("插入" + yonghu);
                        yonghu.clear();
                    } catch (Exception e) {
                        Error.add("未知性错误(身份证重复？)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }


                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            map.put("Error", Error);
            return map;
        }

    }

    //导入补助个人表--11.18beat
    @Override
    @Transactional
    public Map<String, Object> ImportSubsidyProsonal(MultipartFile file) {
        //结果
        Map<String, Object> map = new HashMap<String, Object>();
        //异常
        Exception exception = new Exception();
        //错误
        List<String> Error = new ArrayList<String>();
        //工作区间
        Workbook workbook = getWorkbook(file);
        //类型
        Integer type;
        //补助对象
        Subsidyobject subsidyobject = new Subsidyobject();
        try {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }

                    //村Id------------------------------------------------------------------------------------------------
                    if (row.getCell(0) == null) {
                        Error.add("第" + (i + 1) + "行行政村名称代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String id = row.getCell(0).getStringCellValue();
                    id = strClean(id);

                    if ("".equals(id)) {
                        Error.add("第" + (i + 1) + "行行政村名称代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    Integer idx = Integer.parseInt(id);
                    String naemId = villageMapper.selectNameByID(idx);
                    if (naemId == null || naemId.equals("")) {
                        Error.add("第" + (i + 1) + "行行政村名称代码不存在，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidyobject.setSoVillageid(idx);
                    //年份------------------------------------------------------------------------------------------------
                    if (row.getCell(1) == null) {

                        row.createCell(1);
                    }
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String year = row.getCell(1).getStringCellValue();
                    year = strClean(year);
                    if (!"".equals(year)) {

                        if (!year.matches(SYEAR)) {
                            Error.add("第" + (i + 1) + "行年份格式样例:2018，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                    }

                    subsidyobject.setSoBeiyong5(year);
                    //姓名------------------------------------------------------------------------------------------------
                    if (row.getCell(2) == null) {
                        Error.add("第" + (i + 1) + "行姓名未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String name = row.getCell(2).getStringCellValue();
                    name = strClean(name);
                    if ("".equals(name)) {
                        Error.add("第" + (i + 1) + "行姓名未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!name.matches(NAME)) {
                        Error.add("第" + (i + 1) + "行姓名格式不正确(非汉字)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidyobject.setSoName(name);

                    //性别------------------------------------------------------------------------------------------------
                    if (row.getCell(3) == null) {

                        row.createCell(3);

                    }
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String sex = row.getCell(3).getStringCellValue();
                    sex = strClean(sex);
                    if (!"".equals(sex)) {
                        if (!sex.equals("男") && !sex.equals("女")) {
                            Error.add("第" + (i + 1) + "行性别格式：（男，女），前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidyobject.setSoBeiyong1(sex);
                    }
                    //身份证------------------------------------------------------------------------------------------------
                    if (row.getCell(4) == null) {
                        row.createCell(4);
                    }
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String ID = row.getCell(4).getStringCellValue();
                    ID = strClean(ID);
                    if (!"".equals(ID)) {
                        if (!ID.matches(CARD)) {
                            Error.add("第" + (i + 1) + "行身份证号格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidyobject.setSoShenfenzheng(ID);
                    }
                    //类型名称------------------------------------------------------------------------------------------------
                    if (row.getCell(5) == null) {
                        Error.add("第" + (i + 1) + "行补助项名称未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    String subsname = row.getCell(5).getStringCellValue();
                    subsname = strClean(subsname);
                    if ("".equals(subsname)) {
                        Error.add("第" + (i + 1) + "行补助项名称未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    Integer nameID = subsidynameMapper.selectIdByNameAdVillage(subsname, subsidyobject.getSoVillageid());
                    if (nameID == null) {
                        Error.add("第" + (i + 1) + "行补助项不存在，请先手动添加该补助项，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //享受的补助（补助信息表主键）
                    subsidyobject.setSoEnjoythesubsidy(nameID.toString());
                    //补助金额（物）------------------------------------------------------------------------------------------------
                    if (row.getCell(6) == null) {
                        row.createCell(6);

                    }
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    String moneyAdThing = row.getCell(6).getStringCellValue();
                    moneyAdThing = strClean(moneyAdThing);
                    if (!"".equals(moneyAdThing)) {
                        subsidyobject.setSoBeiyong2(moneyAdThing);
                    }

                    //补助原因------------------------------------------------------------------------------------------------
                    if (row.getCell(7) == null) {
                        row.createCell(7);
                    }
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String bec = row.getCell(7).getStringCellValue();
                    bec = strClean(bec);
                    if (bec.length() > 100) {
                        Error.add("第" + (i + 1) + "行个人所受补助的原因简介说明超过100字，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!"".equals(bec)) {
                        subsidyobject.setSoBeiyong4(bec);
                    }
                    //序号------------------------------------------------------------------------------------------------
                    if (row.getCell(8) == null) {
                        row.createCell(8);
                    }
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    String xuhao = row.getCell(8).getStringCellValue();
                    xuhao = strClean(xuhao);
                    if (!"".equals(xuhao)) {
                        if (!xuhao.matches(NUMBER)) {
                            Error.add("第" + (i + 1) + "行序号格式：数字，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidyobject.setSoBeiyong3(xuhao);
                    }


                    //插入
                    try {
                        System.out.println("插入个人补助" + subsidyobject.toString());
                        subsidyobjectMapper.insertDynamic(subsidyobject);
                        subsidyobject.clear();
                    } catch (Exception e) {
                        Error.add("未知性错误(身份证重复？)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            map.put("Error", Error);
            return map;
        }

    }

    //导入补助家庭表--11.18beat
    @Override
    @Transactional
    public Map<String, Object> ImportSubsidyFamily(MultipartFile file) {
        //结果
        Map<String, Object> map = new HashMap<String, Object>();
        //异常
        Exception exception = new Exception();
        //结果
        List<String> Error = new ArrayList<String>();
        //工作区间
        Workbook workbook = getWorkbook(file);
        //补助户
        Subsidizedhouseholds subsidizedhouseholds = new Subsidizedhouseholds();
        try {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);

                for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }

                    //村Id------------------------------------------------------------------------------------------------
                    if (row.getCell(0) == null) {
                        Error.add("第" + (i + 1) + "行行政村名称代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String id = row.getCell(0).getStringCellValue();
                    id = strClean(id);
                    System.out.println(id);
                    if (id.equals("")) {
                        Error.add("第" + (i + 1) + "行行政村名称代码未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    Integer idx = Integer.parseInt(id);
                    String naemId = villageMapper.selectNameByID(idx);
                    if (naemId == null || naemId.equals("")) {
                        Error.add("第" + (i + 1) + "行行政村名称ID代码不存在，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidizedhouseholds.setShVillageid(idx);
                    //姓名------------------------------------------------------------------------------------------------
                    if (row.getCell(1) == null) {
                        Error.add("第" + (i + 1) + "行户主姓名未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String name = row.getCell(1).getStringCellValue();
                    name = strClean(name);
                    if ("".equals(name)) {
                        Error.add("第" + (i + 1) + "行户主姓名未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!name.matches(NAME)) {
                        Error.add("第" + (i + 1) + "行户主姓名格式不正确(非汉字)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidizedhouseholds.setShName(name);
                    //身份证------------------------------------------------------------------------------------------------
                    if (row.getCell(2) == null) {
                        row.createCell(2);
                    }
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String ID = row.getCell(2).getStringCellValue();
                    ID = strClean(ID);
                    if (!"".equals(ID)) {
                        if (!ID.matches(CARD)) {
                            Error.add("第" + (i + 1) + "行身份证号格式不正确，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidizedhouseholds.setShBeiyong2(ID);
                    }
                    //住址------------------------------------------------------------------------------------------------
                    if (row.getCell(3) == null) {
                        row.createCell(3);
                    }
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String address = row.getCell(3).getStringCellValue();
                    address = strClean(address);
                    if (!"".equals(address)) {
                        subsidizedhouseholds.setShAddress(address);
                    }
                    //享受的补助------------------------------------------------------------------------------------------------
                    if (row.getCell(4) == null) {
                        Error.add("第" + (i + 1) + "行补助项名称未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String subsName = row.getCell(4).getStringCellValue();
                    subsName = strClean(subsName);
                    if ("".equals(subsName)) {
                        Error.add("第" + (i + 1) + "行补助项名称未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    //判存
                    Integer subsId = subsidynameMapper.selectIdByNameAdVillage(subsName, subsidizedhouseholds.getShVillageid());
                    if (subsId == null) {
                        Error.add("第" + (i + 1) + "行补助项不存在，请先手动创建，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidizedhouseholds.setShEnjoythesubsidy(subsId.toString());
                    //补助金额（物）------------------------------------------------------------------------------------------------
                    if (row.getCell(5) == null) {
                        row.createCell(5);
                    }
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    String moneyAdThing = row.getCell(5).getStringCellValue();
                    moneyAdThing = strClean(moneyAdThing);
                    if (!"".equals(moneyAdThing)) {
                        subsidizedhouseholds.setShBeiyong3(moneyAdThing);
                    }
                    //补助原因------------------------------------------------------------------------------------------------
                    if (row.getCell(6) == null) {
                        row.createCell(6);
                    }
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    String bec = row.getCell(6).getStringCellValue();
                    bec = strClean(bec);
                    if (!bec.equals("")) {
                        if (bec.length() > 100) {
                            Error.add("第" + (i + 1) + "行个人所受补助的原因简介说明超过100字，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidizedhouseholds.setShFamilyinformation(bec);
                    }
                    //年份------------------------------------------------------------------------------------------------
                    if (row.getCell(7) == null) {
                        Error.add("第" + (i + 1) + "行年份未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String year = row.getCell(7).getStringCellValue();
                    year = strClean(year);
                    if ("".equals(year)) {
                        Error.add("第" + (i + 1) + "行年份未填写，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    if (!year.matches(SYEAR)) {
                        Error.add("第" + (i + 1) + "行年份格式：2018，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }
                    subsidizedhouseholds.setShHu(year);
                    //序号------------------------------------------------------------------------------------------------
                    if (row.getCell(8) == null) {
                        row.createCell(8);
                    }
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    String xuhao = row.getCell(8).getStringCellValue();
                    xuhao = strClean(xuhao);
                    if (!"".equals(xuhao)) {
                        if (!xuhao.matches(NUMBER)) {
                            Error.add("第" + (i + 1) + "行序号格式:数字，前" + (i) + "行已成功导入，请勿重复导入");
                            throw exception;
                        }
                        subsidizedhouseholds.setShBeiyong1(xuhao);
                    }

                    //插入
                    try {
                        subsidizedhouseholdsMapper.insertDynamicImportExcel(subsidizedhouseholds);
                        subsidizedhouseholds.clear();
                    } catch (Exception e) {
                        Error.add("未知性错误(身份证重复？)，前" + (i) + "行已成功导入，请勿重复导入");
                        throw exception;
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            map.put("Error", Error);
            return map;
        }

    }

    //校验excel
    @Override
    public boolean isExcel(String name) {
        if (name == null || (!name.matches(XLSX) && !name.matches(XLS))) {
            return false;
        } else {
            return true;
        }
    }

    private Workbook getWorkbook(MultipartFile file) {
        Workbook workbook = null;
        try {
            InputStream inputStream = file.getInputStream();

            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    private String strClean(String name) {
        if (name == null) {
            return "";
        }
        return name.replaceAll(BLANK, "");
    }
}
