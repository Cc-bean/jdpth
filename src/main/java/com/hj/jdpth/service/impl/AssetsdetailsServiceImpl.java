package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.AssetsdetailsService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetsdetailsServiceImpl implements AssetsdetailsService {
    @Autowired
    AssetsdetailsMapper assetsdetailsMapper;
    @Autowired
    AssetsMapper assetsMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;

    @Override
    public Map<String, Object> quaryAssetsdetails(int mtyle, int mRegion, int mZhenid, int mVillageid, String value, int startPage, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List list2 = new ArrayList();
        Page<AssetsAndAssetsdetails> assetsPage = null;
        if (mtyle == 2 || mtyle == 3) {
            PageHelper.startPage(startPage, pageSize);
            assetsPage = assetsdetailsMapper.quaryQZC(mRegion, 0, 0, value);
        }
        if (mtyle == 4) {
            PageHelper.startPage(startPage, pageSize);
            assetsPage = assetsdetailsMapper.quaryQZC(mRegion, mZhenid, 0, value);
        }
        if (mtyle == 5) {
            PageHelper.startPage(startPage, pageSize);
            assetsPage = assetsdetailsMapper.quaryQZC(mRegion, mZhenid, mVillageid, value);
        }
        if (mtyle != 0) {
            List<Zhen> zhenList = zhenMapper.quaryByRegion_gsh(mRegion);
            List list = new ArrayList();

            for (Zhen zhen : zhenList) {
                List<Village> villagelist = villageMapper.queryByZhen_gsh(zhen.getZhenId());
                for (Village village : villagelist) {
                    Map<String, Object> map1 = new HashMap<>();
                    List<Assets> assetsList = assetsdetailsMapper.quaryA_Type_gsh(village.getVillageId());
                    map1.put("village", village);
                    map1.put("Assetstype", assetsList);
                    list.add(map1);
                }
            }
            result.put("region", list);
        } else {
            PageHelper.startPage(startPage, pageSize);
            assetsPage = assetsdetailsMapper.quaryQZC(mRegion, mZhenid, mVillageid, value);
            for (AssetsAndAssetsdetails assetsAndAssetsdetails : assetsPage
            ) {
                Integer ZcmzId = assetsAndAssetsdetails.getZcmzId();
                Map<String, Object> map2 = new HashMap<>();
                Zichantaizhang zichantaizhang = assetsdetailsMapper.quaryZichantaizhang(ZcmzId);
                Zichanjingying zichanjingying = assetsdetailsMapper.quaryZiChanJingYing(ZcmzId);
                map2.put("Zichanmingzi", assetsAndAssetsdetails.getZcmzId());
                map2.put("Zichantaizhang", zichantaizhang);
                map2.put("Zichanjingying", zichanjingying);
                list2.add(map2);
            }

        }
        if (assetsPage.isEmpty()) {
            List<Assets> a = null;
            result.put("status", "null");
            result.put("data", a);
        } else {
            result.put("status", "success");
            result.put("data", assetsPage);
            result.put("data2", list2);
            //总页数
            result.put("total", assetsPage.getPages());
            //记录总数
            result.put("count", assetsPage.getTotal());
            //当前第几页
            result.put("nowPage", assetsPage.getPageNum());
        }
        return result;
    }

    @Override
    public Map<String, Object> quaryZichanmingziByType(String aType, int startPage, int pageSize) {

        Map<String, Object> result = new HashMap<>();
        try {
            PageHelper.startPage(startPage, pageSize);
            Page<Map<Assets, Zichanmingzi>> assetsPage = assetsdetailsMapper.quaryByType(aType);
            if (assetsPage.isEmpty()) {
                result.put("status", "null");
                result.put("data", "没有数据");
            } else {
                result.put("data", assetsPage);
                result.put("total", assetsPage.getPages());
                result.put("count", assetsPage.getTotal());
                result.put("nowPage", assetsPage.getPageNum());
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "查询出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //try{
        Zichanmingzi zichanmingzi = assetsdetailsMapper.quaryById(id);
        if (zichanmingzi != null) {
            Boolean flag = assetsdetailsMapper.deleteZichanmingzi(id);
            if (flag == true) {
                result.put("status", "success");
                result.put("data", "删除成功");
            } else {
                result.put("status", "false");
                result.put("data", "删除失败");
            }
        } else {
            result.put("status", "null");
            result.put("data", "没有数据，无法删除");
        }
        /*}catch (Exception e){
            result.put("status","error");
            result.put("data","error");
        }*/
        return result;
    }

    @Override
    public Map<String, Object> deleteMany(String ids) {
        Map<String, Object> result = new HashMap<>();
        String[] arrayId = ids.split(",");
        for (String Aid : arrayId) {
            int flagb = 0;
            Integer aid = Integer.valueOf(Aid);
            Zichanmingzi zichanmingzi = assetsdetailsMapper.quaryById(aid);
            if (zichanmingzi != null) {
                Boolean flag = assetsdetailsMapper.deleteZichanmingzi(aid);
                if (flag == true) {
                    flagb++;
                }
                if (flagb != 0) {
                    result.put("status", "success");
                    result.put("data", "删除成功");
                } else {
                    result.put("status", "false");
                    result.put("data", "删除失败");
                }
            } else {
                result.put("status", "null");
                result.put("data", "没有数据，无法删除");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> quaryA_Type(Integer A_Villageid, Integer A_Zu) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Assets> list = assetsdetailsMapper.quaryA_Type(A_Villageid, A_Zu);
            map.put("data", list);
        } catch (Exception e) {
            map.put("data", "error");
            map.put("error", "error");
        }
        return map;
    }

    //同时插入资产名字表与资产表
    @Override
    public Map<String, Object> addZCMZ_Name(Integer A_Zu, Integer A_Villageid, String ZCMZ_Name, Integer A_Type) {
        Map<String, Object> result = new HashMap<>();

        Zichanmingzi zichanmingzi = assetsdetailsMapper.quaryZCMZ_gsh(ZCMZ_Name.trim(), A_Type);
        if (zichanmingzi == null) {
            zichanmingzi = new Zichanmingzi();
            zichanmingzi.setZcmzLeixing(A_Type);
            zichanmingzi.setZcmzName(ZCMZ_Name);
            assetsdetailsMapper.addAssetsdetails(zichanmingzi);
            result.put("status", "success");
        } else {
            result.put("status", "改资产名字已存在");
            return result;
        }
        return result;
    }

    //插入资产台账表
    @Override
    public Map<String, Object> addZCTZ(Integer zjtzName, String zjtzJilangdanwei, Integer zjtzShuliang, Float zjtzDanjian, String zjtzYuanzhi, String zjtzBiandongqingkuang, String zjtzXianzhi, String zjtzPinpai, String zjtzXinghao, Date zjtzGoujianshijian, String zjtzLaiyuan, String zjtzYvjinianxian, String zjtzDepartment, String zjtzPeople, String zjtzBeizhu, String zjtzZhuangtai) {
        Map<String, Object> result = new HashMap<>();
        Zichantaizhang zichantaizhang = new Zichantaizhang();
        Zichanmingzi zichanmingzi = assetsdetailsMapper.quaryZCMZ(zjtzName);
        if (zichanmingzi != null) {
            zichantaizhang.setZjtzName(zjtzName);
            zichantaizhang.setZjtzJilangdanwei(zjtzJilangdanwei);
            zichantaizhang.setZjtzShuliang(zjtzShuliang);
            zichantaizhang.setZjtzDanjian(zjtzDanjian);
            zichantaizhang.setZjtzYuanzhi(zjtzYuanzhi);
            zichantaizhang.setZjtzBiandongqingkuang(zjtzBiandongqingkuang);
            zichantaizhang.setZjtzXianzhi(zjtzXianzhi);
            zichantaizhang.setZjtzPinpai(zjtzPinpai);
            zichantaizhang.setZjtzXinghao(zjtzXinghao);
            zichantaizhang.setZjtzGoujianshijian(zjtzGoujianshijian);
            zichantaizhang.setZjtzLaiyuan(zjtzLaiyuan);
            zichantaizhang.setZjtzYvjinianxian(zjtzYvjinianxian);
            zichantaizhang.setZjtzDepartment(zjtzDepartment);
            zichantaizhang.setZjtzPeople(zjtzPeople);
            zichantaizhang.setZjtzBeizhu(zjtzBeizhu);
            zichantaizhang.setZjtzZhuangtai(zjtzZhuangtai);
            Integer flag = assetsdetailsMapper.insertZCTZ(zichantaizhang);
            if (flag > 0) {
                result.put("status", "新增成功");
            }
        } else {
            result.put("status", "资产类型名称不存在");
        }
        return result;
    }

    //插入资产经营表
    @Override
    public Map<String, Object> addZCJY(Integer zcjyName, String zcjyPhoto, String zcjyZhenshilujing, String zcjyZhuangtai, String zcjyChengzhuren, Boolean zcjyHetong, String zcjyHetongqixian, Float zcjyHetongjine, String zcjyZhifuqinkuang, String zcjyBeizhu, String zcjyXvhao) {
        Map<String, Object> result = new HashMap<>();
        Zichanmingzi zichanmingzi = assetsdetailsMapper.quaryZCMZ(zcjyName);
        if (zichanmingzi != null) {
            Zichanjingying zichanjingying = new Zichanjingying();
            zichanjingying.setZcjyName(zcjyName);
            zichanjingying.setZcjyPhoto(zcjyPhoto);
            zichanjingying.setZcjyZhenshilujing(zcjyZhenshilujing);
            zichanjingying.setZcjyZhuangtai(zcjyZhuangtai);
            zichanjingying.setZcjyChengzhuren(zcjyChengzhuren);
            zichanjingying.setZcjyHetong(zcjyHetong);
            zichanjingying.setZcjyHetongqixian(zcjyHetongqixian);
            zichanjingying.setZcjyHetongjine(zcjyHetongjine);
            zichanjingying.setZcjyZhifuqinkuang(zcjyZhifuqinkuang);
            zichanjingying.setZcjyBeizhu(zcjyBeizhu);
            zichanjingying.setZcjyXvhao(zcjyXvhao);
            Integer flag = assetsdetailsMapper.insertZCJY(zichanjingying);
            if (flag > 0) {
                result.put("status", "新增成功");
            }
        } else {
            result.put("status", "资产类型名称不存在");
        }
        return result;
    }

    //更新资产台账表
    @Override
    public Map<String, Object> insertZCTZ(Integer zjtzId, String zjtzJilangdanwei, Integer zjtzShuliang, Float zjtzDanjian, String zjtzBiandongqingkuang, String zjtzXianzhi, String zjtzPinpai, String zjtzXinghao, Date zjtzGoujianshijian, String zjtzLaiyuan, String zjtzYvjinianxian, String zjtzDepartment, String zjtzPeople, String zjtzBeizhu, String zjtzZhuangtai) {
        Map<String, Object> result = new HashMap<>();
        Zichantaizhang zichantaizhang = assetsdetailsMapper.quaryZCTZ(zjtzId);
        if (zichantaizhang != null) {
            zichantaizhang.setZjtzId(zjtzId);
            zichantaizhang.setZjtzDepartment(zjtzDepartment);
            zichantaizhang.setZjtzPeople(zjtzPeople);
            zichantaizhang.setZjtzGoujianshijian(zjtzGoujianshijian);
            zichantaizhang.setZjtzLaiyuan(zjtzLaiyuan);
            zichantaizhang.setZjtzYvjinianxian(zjtzYvjinianxian);
            zichantaizhang.setZjtzDanjian(zjtzDanjian);
            zichantaizhang.setZjtzJilangdanwei(zjtzJilangdanwei);
            zichantaizhang.setZjtzShuliang(zjtzShuliang);
            zichantaizhang.setZjtzBiandongqingkuang(zjtzBiandongqingkuang);
            zichantaizhang.setZjtzXinghao(zjtzXinghao);
            zichantaizhang.setZjtzPinpai(zjtzPinpai);
            zichantaizhang.setZjtzXianzhi(zjtzXianzhi);
            zichantaizhang.setZjtzBeizhu(zjtzBeizhu);
            zichantaizhang.setZjtzZhuangtai(zjtzZhuangtai);
            Integer flag = assetsdetailsMapper.updateZichantaizhang(zichantaizhang);
            if (flag > 0) {
                result.put("status", "更新成功");
            }
        } else {
            result.put("status", "不存在，无法修改");
        }
        return result;
    }

    //更新资产经营表
    @Override
    public Map<String, Object> insertZCJY(Integer zcjyId, String zcjyPhoto, String zcjyZhenshilujing, String zcjyZhuangtai, String zcjyChengzhuren, Boolean zcjyHetong, String zcjyHetongqixian, Float zcjyHetongjine, String zcjyZhifuqinkuang, String zcjyBeizhu, String zcjyXvhao) {
        Map<String, Object> result = new HashMap<>();
        Zichanjingying zichanjingying = assetsdetailsMapper.quaryZCJY(zcjyId);
        if (zichanjingying != null) {
            zichanjingying.setZcjyId(zcjyId);
            zichanjingying.setZcjyPhoto(zcjyPhoto);
            zichanjingying.setZcjyZhenshilujing(zcjyZhenshilujing);
            zichanjingying.setZcjyZhuangtai(zcjyZhuangtai);
            zichanjingying.setZcjyChengzhuren(zcjyChengzhuren);
            zichanjingying.setZcjyHetong(zcjyHetong);
            zichanjingying.setZcjyHetongqixian(zcjyHetongqixian);
            zichanjingying.setZcjyHetongjine(zcjyHetongjine);
            zichanjingying.setZcjyZhifuqinkuang(zcjyZhifuqinkuang);
            zichanjingying.setZcjyBeizhu(zcjyBeizhu);
            zichanjingying.setZcjyXvhao(zcjyXvhao);
            Integer flag = assetsdetailsMapper.updateZiChanJingYing(zichanjingying);
            if (flag > 0) {
                result.put("status", "更新成功");
            }
        } else {
            result.put("status", "不存在，无法修改");
        }
        return result;
    }

    @Override
    public Map<String, Object> uploadFile_ZiChanJingYing(MultipartFile file) {
        String path = "";
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\ZiChanJingYing\\";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "." + type;
        File targerFile = new File(path, fileName);
        try {
            file.transferTo(targerFile);
            map.put("path", path + fileName);
            map.put("name", originalFileName);
            map.put("type", type);
            map.put("state", "success");
        } catch (IOException e) {
            map.put("path", "");
            map.put("name", "此文件上传失败");
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> uploadFile_ZiChanTaiZhang(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\ZiChanTaiZhang\\";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "." + type;
        File targerFile = new File(path, fileName);
        try {
            file.transferTo(targerFile);
            map.put("path", path + fileName);
            map.put("name", originalFileName);
            map.put("type", type);
            map.put("state", "success");
        } catch (IOException e) {
            map.put("path", "");
            map.put("name", "此文件上传失败");
            map.put("state", "error");
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> uploadExcel_ZiChanJingYing(String path) {
        Map<String, Object> map = new HashMap<>();
        Workbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(path);
                workbook = new XSSFWorkbook(fileInputStream);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);

        try {
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {

                Zichanjingying zichanjingying = new Zichanjingying();
                Zichanmingzi zichanmingzi = new Zichanmingzi();
                //从第三行
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                Cell cell = row.getCell(0);
                int vid;
                if (!getCellValue(cell).equals("")) {
                    vid = (int) cell.getNumericCellValue();
                } else {
                    continue;
                }
                cell = row.getCell(1);
                int zuid;
                if (getCellValue(cell).equals("")) {
                    List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(vid);
                    if (zuList.size() > 1) {
                        map.put("status", "第" + (i + 1) + "行缺少组id");
                        return map;
                    } else {
                        zuid = caiwuCapitalMapper.findAllZuByID(vid).get(0).getzKey();
                    }
                } else {
                    zuid = (int) cell.getNumericCellValue();
                }
                cell = row.getCell(2);
                Integer zcType;
                String zcTypeName = getCellValue(cell);
                if (zcTypeName.equals("")) {
                    map.put("status", "第" + (i + 1) + "行未填写资产类型");
                    return map;
                } else {
                    zcType = assetsdetailsMapper.quaryZCLXId(vid, zcTypeName, zuid);
                    if (zcType == null) {
                        map.put("status", "请在后台管理员页面资产类型新增--" + zcTypeName + "--后重新导入");
                        return map;
                    }
                }
                cell = row.getCell(3);
                String zcMZ = getCellValue(cell);

                cell = row.getCell(4);
                String zcZhuangTai = getCellValue(cell);

                cell = row.getCell(5);
                String zcChengZuRen = getCellValue(cell);

                cell = row.getCell(6);
                Boolean zcHeTong;
                if (getCellValue(cell).equals("有")) {
                    zcHeTong = true;
                } else {
                    zcHeTong = false;
                }
                cell = row.getCell(7);
                String zcHeTongQiXian = getCellValue(cell);

                cell = row.getCell(8);
                String zcZhiFuQinKuang = getCellValue(cell);

                cell = row.getCell(9);
                String zcBeiZhu = getCellValue(cell);

                zichanmingzi.setZcmzLeixing(zcType);
                zichanmingzi.setZcmzName(zcMZ);
                Zichanmingzi zichanmingzi1 = assetsdetailsMapper.quaryZCMZ_gsh(zcMZ, zcType);
                Integer ZCMZ_Id = 0;
                if (zichanmingzi1 == null) {
                    assetsdetailsMapper.addAssetsdetails(zichanmingzi);
                    ZCMZ_Id = zichanmingzi.getZcmzId();
                } else {
                    ZCMZ_Id = zichanmingzi1.getZcmzId();
                }
                if (assetsdetailsMapper.quaryZiChanJingYing(ZCMZ_Id) == null) {
                    zichanjingying.setZcjyName(ZCMZ_Id);
                    zichanjingying.setZcjyBeizhu(zcBeiZhu);
                    zichanjingying.setZcjyHetong(zcHeTong);
                    zichanjingying.setZcjyZhifuqinkuang(zcZhiFuQinKuang);
                    zichanjingying.setZcjyHetongqixian(zcHeTongQiXian);
                    zichanjingying.setZcjyChengzhuren(zcChengZuRen);
                    zichanjingying.setZcjyZhuangtai(zcZhuangTai);
                    assetsdetailsMapper.insertZCJY(zichanjingying);
                }
            }
            map.put("status", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "出现错误");
            throw new RuntimeException();
        } finally {
            return map;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> uploadExcel_ZiChanTaiZhang(String path) {
        Map<String, Object> map = new HashMap<>();
        Workbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(path);
                workbook = new XSSFWorkbook(fileInputStream);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);

        try {
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Cell cell;
                Zichanmingzi zichanmingzi = new Zichanmingzi();
                Zichantaizhang zichantaizhang = new Zichantaizhang();
                //从第三行
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                cell = row.getCell(0);
                int vid;
                if (!getCellValue(cell).equals("")) {
                    vid = (int) cell.getNumericCellValue();
                } else {
                    continue;
                }
                cell = row.getCell(1);
                int zuid;
                if (getCellValue(cell).equals("")) {
                    List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(vid);
                    if (zuList.size() > 1) {
                        map.put("status", "第" + (i + 1) + "行缺少组id");
                        return map;
                    } else {
                        zuid = caiwuCapitalMapper.findAllZuByID(vid).get(0).getzKey();
                    }
                } else {
                    zuid = (int) cell.getNumericCellValue();
                }

                cell = row.getCell(2);
                Integer zcType;
                String zcTypeName = getCellValue(cell);
                if (zcTypeName.equals("")) {
                    map.put("status", "第" + (i + 1) + "行未填写资产类型");
                    return map;
                } else {
                    System.out.println(zcTypeName);
                    zcType = assetsdetailsMapper.quaryZCLXId(vid, zcTypeName, zuid);
                    if (zcType == null) {
                        map.put("status", "请在后台管理员页面的资产类型处新增--" + zcTypeName + "--后重新导入");
                        return map;
                    }
                }
                cell = row.getCell(3);
                String zcName = getCellValue(cell);

                cell = row.getCell(4);
                String zcDanWei = getCellValue(cell);

                cell = row.getCell(5);
                int zcShuLiang = 0;
                if (!getCellValue(cell).equals("")) {
                    zcShuLiang = (int) cell.getNumericCellValue();
                }

                cell = row.getCell(6);
                float zcDanJian = 0;
                if (!getCellValue(cell).equals("")) {
                    zcDanJian = (float) cell.getNumericCellValue();
                }
                cell = row.getCell(7);
                String zcYuanZhi = getCellValue(cell);

                cell = row.getCell(8);
                String zcBianDongQingKuang = getCellValue(cell);

                cell = row.getCell(9);
                String zcXianZhi = getCellValue(cell);


                cell = row.getCell(10);
                String zcPinPai = getCellValue(cell);

                cell = row.getCell(11);
                String zcXingHaoo;
                if (getCellValue(cell).equals("")) {
                    zcXingHaoo = null;
                } else {
                    zcXingHaoo = getCellValue(cell);
                }

                cell = row.getCell(12);
                java.util.Date zcGouJianShiJian = null;
                if (cell != null) {
                    zcGouJianShiJian = cell.getDateCellValue();
                }
                cell = row.getCell(13);
                String zcLaiYuan = getCellValue(cell);

                cell = row.getCell(14);
                String zcYuJiShiYongNianXian = "";
                if (!getCellValue(cell).equals("")) {
                    zcYuJiShiYongNianXian = getCellValue(cell).replace(".00", "");
                }
                cell = row.getCell(15);
                String zcDepartment = getCellValue(cell);

                cell = row.getCell(16);
                String zcPeople = getCellValue(cell);

                cell = row.getCell(17);
                String zcZhuangTai = getCellValue(cell);

                cell = row.getCell(18);
                String zcBeiZhu = getCellValue(cell);

                zichanmingzi.setZcmzLeixing(zcType);
                zichanmingzi.setZcmzName(zcName);
                Zichanmingzi zichanmingzi1 = assetsdetailsMapper.quaryZCMZ_gsh(zcName, zcType);
                Integer ZCMZ_Id;
                if (zichanmingzi1 == null) {
                    assetsdetailsMapper.addAssetsdetails(zichanmingzi);
                    ZCMZ_Id = zichanmingzi.getZcmzId();
                } else {
                    ZCMZ_Id = zichanmingzi1.getZcmzId();
                }
                if (assetsdetailsMapper.quaryZichantaizhang(ZCMZ_Id) == null) {
                    zichantaizhang.setZjtzName(ZCMZ_Id);
                    zichantaizhang.setZjtzYuanzhi(zcYuanZhi);
                    zichantaizhang.setZjtzPeople(zcPeople);
                    zichantaizhang.setZjtzDepartment(zcDepartment);
                    zichantaizhang.setZjtzLaiyuan(zcLaiYuan);
                    zichantaizhang.setZjtzGoujianshijian(zcGouJianShiJian);
                    zichantaizhang.setZjtzXinghao(zcXingHaoo);
                    zichantaizhang.setZjtzBeizhu(zcBeiZhu);
                    zichantaizhang.setZjtzZhuangtai(zcZhuangTai);
                    zichantaizhang.setZjtzYvjinianxian(zcYuJiShiYongNianXian);
                    zichantaizhang.setZjtzXianzhi(zcXianZhi);
                    zichantaizhang.setZjtzBiandongqingkuang(zcBianDongQingKuang);
                    zichantaizhang.setZjtzPinpai(zcPinPai);
                    zichantaizhang.setZjtzShuliang(zcShuLiang);
                    zichantaizhang.setZjtzDanjian(zcDanJian);
                    zichantaizhang.setZjtzJilangdanwei(zcDanWei);
                    assetsdetailsMapper.insertZCTZ(zichantaizhang);
                }
            }
            map.put("status", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "出现错误");
            throw new RuntimeException();
        } finally {
            return map;
        }
    }


    private String getCellValue(Cell cell) {
        String cellValue = "";
        java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString().replaceAll(" ", "");
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    cellValue = df.format(cell.getNumericCellValue()).toString();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue()).replaceAll(" ", "");
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    cellValue = "";
            }
        }
        return cellValue;
    }
}
