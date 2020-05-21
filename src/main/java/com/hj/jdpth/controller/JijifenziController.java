//
//package com.hj.jdpth.controller;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.hj.jdpth.annotation.OperationLogDetail;
//import com.hj.jdpth.aop.OperationType;
//import com.hj.jdpth.aop.OperationUnit;
//import com.hj.jdpth.domain.*;
//import com.hj.jdpth.repository.*;
//import com.hj.jdpth.service.JijifenziService;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@CrossOrigin
//    @RestController
//    public class JijifenziController {
//        @Autowired
//        private JijifenziMapper jijifenziMapper;
//    @Autowired
//    FazhanduixiangMapper fazhanduixiangMapper;
//    @Autowired
//    YvbeidangyuanMapper yvbeidangyuanMapper;
//    @Autowired
//    DangyuanzhuanzhengMapper dangyuanzhuanzhengMapper;
//    @Autowired
//    QingkuangbiangengMapper qingkuangbiangengMapper;
//
//        @Autowired
//        private JijifenziService jijifenziService;
//    @Autowired
//    NationMapper nationMapper;
//
//    private String nation=null;
//    private String villageName=null;
//    private String zuName=null;
//    private String leixinName=null;
//
//        @PostMapping("/ZuzifazhanFirst")//初始进入组织发展界面
//        public Map<String ,Object> zuzifazhanFirst(
//                                                         @RequestParam(required = false) Integer quId,//区
//                                                         @RequestParam(required = false) Integer zId,//镇
//                                                         @RequestParam(required = false) Integer vId,//村
//                                                         @RequestParam(required = false) Date time,//时间
//                                                         @RequestParam(required = false) Integer entity,//身份
//                                                         @RequestParam Integer startPage,
//                                                         @RequestParam Integer pageSize ,
//                                                         @RequestParam Integer adminId//管理员类型
//        ){
//            Map<String , Object> result = new HashMap<>();
//            if(adminId!=null && startPage !=null && pageSize !=null &&(quId != null ||zId != null ||vId!=null||time!=null||entity!=null) ) {
//                if(quId==null){
//                    quId=0;
//                }
//                if(zId==null){
//                    zId=0;
//                }
//                if(vId==null){
//                    vId=0;
//                }
//                result=jijifenziService.GetInfoAllFirst(quId, zId, vId,time, entity, startPage,pageSize,adminId);
//            }else{
//                result.put("data","数据不完整!");
//                result.put("state","false");
//            }
//            return result;
//        }
//
//    @PostMapping( "/selectJijifenz")//根据id积极分子（详情）
//    public Map<String, Object> selectJijifenz(
//            @RequestParam Integer Id
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            Jijifenzi jijifenzi = jijifenziMapper.queryAll(Id);
//            map.put("data",jijifenzi);
//        }catch (Exception e){
//            map.put("status", "null");
//            map.put("data", "没有数据");
//        }
//            return map;
//    }
//
//        @PostMapping( "/selectzuzifazhan")//根据id积极分子（详情）
//        public Map<String, Object> selectzuzifazhan(
//                @RequestParam Integer entity,
//                @RequestParam Integer Id
//        ) {
//            Map<String, Object> map = new HashMap<>();
//            if(entity==1) {
//               Jijifenzi jijifenzi = jijifenziMapper.queryAll(Id);
//                if(jijifenzi==null){
//                    map.put("record","此人不存在");
//                }else{
//                    map.put("record", jijifenzi);
//                }
//            }
//            else if(entity==2) {
//               Fazhanduixiang fazhanduixiang = fazhanduixiangMapper.queryAll(Id);
//                if(fazhanduixiang==null){
//                    map.put("record","此人不存在");
//                }else{
//                    map.put("record", fazhanduixiang);
//                }
//            }
//            else if(entity==3) {
//               Yvbeidangyuan  yvbeidangyuan = yvbeidangyuanMapper.queryAll(Id);
//                if(yvbeidangyuan==null){
//                    map.put("record","此人不存在");
//                }else{
//                    map.put("record", yvbeidangyuan);
//                }
//            }
//            else if(entity==4) {
//                Dangyuanzhuanzheng  dangyuanzhuanzheng = dangyuanzhuanzhengMapper.queryAll(Id);
//                if(dangyuanzhuanzheng==null){
//                    map.put("record","此人不存在");
//                }else{
//                    map.put("record", dangyuanzhuanzheng);
//                }
//            }
//            else {
//                Qingkuangbiangeng qingkuangbiangeng = qingkuangbiangengMapper.queryAll(Id);
//                if(qingkuangbiangeng==null){
//                    map.put("record","此人不存在");
//                }else{
//                    map.put("record", qingkuangbiangeng);
//                }
//            }
//            return map;
//        }
//        @PostMapping( "/pageFindJijifenziByZId")//根据镇id查找积极分子
//        public Map<String, Object> pageFindJijifenziByZId(
//                @RequestParam Integer zId,
//                @RequestParam Integer startPage,
//                @RequestParam Integer pageSize
//        ) {
//
//            PageHelper.startPage(startPage, pageSize);
//            Page<Jijifenzi> JijifenziPage = jijifenziMapper.pageFindJijifenziByZId(zId);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", JijifenziPage);
//            //总页数
//            result.put("total", JijifenziPage.getPages());
//            //记录总数
//            result.put("count", JijifenziPage.getTotal());
//            //当前第几页
//            result.put("nowPage", JijifenziPage.getPageNum());
//            return result;
//        }
//
//        @PostMapping( "/pageFindJijifenziByZIdandVId")//根据镇id村id查询积极分子
//        public Map<String, Object> pageFindJijifenziByZIdandVId(
//                @RequestParam Integer zId,
//                @RequestParam Integer vId,
//                @RequestParam Integer startPage,
//                @RequestParam Integer pageSize
//        ) {
//            PageHelper.startPage(startPage, pageSize);
//            Page<Jijifenzi> JijifenziPage = jijifenziMapper.pageFindJijifenziByZIdandVId(zId,vId);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", JijifenziPage);
//            //总页数
//            result.put("total", JijifenziPage.getPages());
//            //记录总数
//            result.put("count", JijifenziPage.getTotal());
//            //当前第几页
//            result.put("nowPage", JijifenziPage.getPageNum());
//            return result;
//        }
//
//        @PostMapping( "/pageFindJijifenziByZIdandVIdandJjfzTime")//根据镇id村id和的时间查询积极分子
//        public Map<String, Object> pageFindJijifenziByZIdandVIdandJjfzTime(
//                @RequestParam Integer zId,
//                @RequestParam Integer vId,
//                @RequestParam String jjfzTime,
//                @RequestParam Integer startPage,
//                @RequestParam Integer pageSize
//        ) {
//            PageHelper.startPage(startPage, pageSize);
//            Page<Jijifenzi> JijifenziPage = jijifenziMapper.pageFindJijifenziByZIdandVIdandJjfzTime(zId,vId,jjfzTime);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", JijifenziPage);
//            //总页数
//            result.put("total", JijifenziPage.getPages());
//            //记录总数
//            result.put("count", JijifenziPage.getTotal());
//            //当前第几页
//            result.put("nowPage", JijifenziPage.getPageNum());
//            return result;
//        }
//        @PostMapping( "/selectAllJijifenzi")//查询所有积极分子
//        public Map<String, Object> selectAllJijifenzi(
//                @RequestParam Integer startPage,
//                @RequestParam Integer pageSize
//        ) {
//            PageHelper.startPage(startPage, pageSize);
//            Page<Jijifenzi> JijifenziPage = jijifenziMapper.selectAllJijifenzi();
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", JijifenziPage);
//            //总页数
//            result.put("total", JijifenziPage.getPages());
//            //记录总数
//            result.put("count", JijifenziPage.getTotal());
//            //当前第几页
//            result.put("nowPage", JijifenziPage.getPageNum());
//            return result;
//        }
//        @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.ZUZHIFAZHAN)
//        @DeleteMapping("/DeleteZuzifazhan")//单个删除积极分子
//        public Map<String, Object> DeleteZuzifazhan(
//                @RequestParam Integer managerId,
//                @RequestParam Integer entity,
//                @RequestParam Integer id
//        ) {
//            Map<String, Object> map = new HashMap<>();
//            if(entity==1) {
//                Jijifenzi jijifenzi = jijifenziMapper.queryAll(id);
//            if(jijifenzi==null){
//                map.put("result","null");
//            }else {
//                int result = jijifenziMapper.DeleteJijifenzi(id);
//                if (result == 1) {
//                    map.put("result", "success");
//                } else {
//                    map.put("result", "error");
//                }
//            }
//            }else if(entity==2){
//               Fazhanduixiang fazhanduixiang = fazhanduixiangMapper.queryAll(id);
//                if(fazhanduixiang==null){
//                    map.put("result","null");
//                }else {
//                    int result = fazhanduixiangMapper.DeleteFazhanduixiang(id);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }else if(entity==3){
//                Yvbeidangyuan yvbeidangyuan = yvbeidangyuanMapper.queryAll(id);
//                if(yvbeidangyuan==null){
//                    map.put("result","null");
//                }else {
//                    int result = yvbeidangyuanMapper.DeleteYvbeidangyuan(id);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }else{
//                Dangyuanzhuanzheng dangyuanzhuanzheng = dangyuanzhuanzhengMapper.queryAll(id);
//                if(dangyuanzhuanzheng==null){
//                    map.put("result","null");
//                }else {
//                    int result = dangyuanzhuanzhengMapper.DeleteDangyuanzhuanzheng(id);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }
//            return map;
//        }
//        @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.ZUZHIFAZHAN)
//        @DeleteMapping("/DeleteDuoTiaoZuzifazhan")//批量删除积极分子
//        public Map<String, Object> DeleteDuoTiaoZuzifazhan(
//                @RequestParam Integer managerId,
//                @RequestParam Integer entity,
//                @RequestParam Integer[] id
//        ) {
//            Map<String, Object> map = new HashMap<>();
//            int result=0;
//            if(entity==1) {
//                for (int a = 0; a < id.length; a++) {
//                    result = jijifenziMapper.DeleteJijifenzi(id[a]);
//                    map.put("result",result);
//                }
//            }else if(entity==2){
//                for (int a = 0; a < id.length; a++) {
//                    result = fazhanduixiangMapper.DeleteFazhanduixiang(id[a]);
//                    map.put("result",result);
//                }
//            }else if(entity==3){
//                for (int a = 0; a < id.length; a++) {
//                    result = yvbeidangyuanMapper.DeleteYvbeidangyuan(id[a]);
//                    map.put("result",result);
//                }
//            }else{
//                for (int a = 0; a < id.length; a++) {
//                    result = dangyuanzhuanzhengMapper.DeleteDangyuanzhuanzheng(id[a]);
//                    map.put("result",result);
//                }            }
//            return map;
//        }
//        @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.ZUZHIFAZHAN)
//            @PostMapping("/InsertZuzifazhan")//添加积极分子
//            public Map<String, Object> InsertZuzifazhan(
//                    @RequestParam Integer managerId,
//                    @RequestParam String name,
//                    @RequestParam String sex,
//                    @RequestParam Integer mingzu,
//                    @RequestParam String shengfengzhenghao,
//                    @RequestParam String danwei,
//                    @RequestParam String phone,
//                    @RequestParam String zhiwu,
//                    @RequestParam String wenhua,
//                    @RequestParam String shengfen,
//                    @RequestParam Integer villageId,
//                    @RequestParam Integer zuId,
//                    @RequestParam Integer year,
//                    @RequestParam String  biangengshijian
//                    ) {
//            Date date = new Date();
//            try {
//                date = new SimpleDateFormat("yyyy-MM-dd").parse(biangengshijian);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            Map<String, Object> map = new HashMap<>();
//                        int result;
//                        if(shengfen.equals("1")) {
//                            Jijifenzi j = new Jijifenzi();
//                            j.setJjfzName(name);
//                            j.setJjfzSex(sex);
//                            j.setJjfzNative(mingzu);
//                            j.setJjfzShenfenzheng(shengfengzhenghao);
//                            j.setJjfzDanwei(danwei);
//                            j.setJjfzPhone(phone);
//                            j.setJjfzZhiwu(zhiwu);
//                            j.setJjfzWenhua(wenhua);
//                            j.setJjfzEntity(shengfen);
//                            j.setJjfzYear(year);
//                            j.setJjfzVillage(villageId);
//                            j.setJjfzZu(zuId);
//                            j.setJjfzTime(date);
//                            System.out.println(j);
//                            result = jijifenziMapper.InsertJijifenzi(j);
//
//                            if (result == 1) {
//                                map.put("result", "success");
//                            } else {
//                                map.put("result", "error");
//                            }
//                        }
//                       if(shengfen.equals("2")) {
//                           Fazhanduixiang f = new Fazhanduixiang();
//                           f.setFzdxName(name);
//                           f.setFzdxSex(sex);
//                           f.setFzdxNative(mingzu);
//                           f.setFzdxShenfenzheng(shengfengzhenghao);
//                           f.setFzdxDanwei(danwei);
//                           f.setFzdxPhone(phone);
//                           f.setFzdxZhiwu(zhiwu);
//                           f.setFzdxWenhua(wenhua);
//                           f.setFzdxEntity(shengfen);
//                           f.setFzdxYear(year);
//                           f.setFzdxVillage(villageId);
//                           f.setFzdxZu(zuId);
//                           f.setFzdxTime(date);
//                           result = fazhanduixiangMapper.InsertFazhanduixiang(f);
//                           if (result == 1) {
//                               map.put("result", "success");
//                           } else {
//                               map.put("result", "error");
//                           }
//                       }
//                        if(shengfen.equals("3")) {
//                            Yvbeidangyuan y = new Yvbeidangyuan();
//                            y.setYbdyName(name);
//                            y.setYbdySex(sex);
//                            y.setYbdyNative(mingzu);
//                            y.setYbdyShengfenzheng(shengfengzhenghao);
//                            y.setYbdyDanwei(danwei);
//                            y.setYbdyPhone(phone);
//                            y.setYbdyZhiwu(zhiwu);
//                            y.setYbdyWenhua(wenhua);
//                            y.setYbdyEntity(shengfen);
//                            y.setYbdyYear(year);
//                            y.setYbdyVillage(villageId);
//                            y.setYbdyZu(zuId);
//                            y.setYbdyTime(date);
//
//                            result = yvbeidangyuanMapper.InsertYvbeidangyuan(y);
//                            if (result == 1) {
//                                map.put("result", "success");
//                            } else {
//                                map.put("result", "error");
//                            }
//                        }
//                        if(shengfen.equals("4")) {
//                            Dangyuanzhuanzheng d = new Dangyuanzhuanzheng();
//                            d.setDyzzName(name);
//                            d.setDyzzSex(sex);
//                            d.setDyzzNative(mingzu);
//                            d.setDyzzShengfengzheng(shengfengzhenghao);
//                            d.setDyzzDanwei(danwei);
//                            d.setDyzzPhone(phone);
//                            d.setDyzzZhiwu(zhiwu);
//                            d.setDyzzWenhua(wenhua);
//                            d.setDyzzEntity(shengfen);
//                            d.setDyzzYear(year);
//                            d.setDyzzVillage(villageId);
//                            d.setDyzzZu(zuId);
//                            d.setDyzzTime(date);
//                            result = dangyuanzhuanzhengMapper.Insertdangyuanzhuanzheng(d);
//                            if (result == 1) {
//                                map.put("result", "success");
//                            } else {
//                                map.put("result", "error");
//                            }
//                }
//                return map;
//        }
//        @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.ZUZHIFAZHAN)
//        @PutMapping("/UpdateZuzifazhan")//修改积极分子
//        public Map<String, Object> UpdateZuzifazhan(
//                @RequestParam Integer managerId,
//                @RequestParam Integer id,
//                @RequestParam String name,
//                @RequestParam String sex,
//                @RequestParam Integer mingzu,
//                @RequestParam String shengfengzhenghao,
//                @RequestParam String danwei,
//                @RequestParam String phone,
//                @RequestParam String zhiwu,
//                @RequestParam String wenhua,
//                @RequestParam String shengfen,
//                @RequestParam Integer year,
//                @RequestParam String biangengshijian
//
//        ) {
//            Date date = new Date();
//            try {
//                date = new SimpleDateFormat("yyyy-MM-dd").parse(biangengshijian);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Map<String, Object> map = new HashMap<>();
//            int result;
//            if(shengfen.equals("1")) {
//               Jijifenzi j=jijifenziMapper.queryAll(id);
//               if(j==null){
//                   map.put("result", "null");
//               }else {
//                   //j.setJjfzId(id);
//                   j.setJjfzName(name);
//                   j.setJjfzSex(sex);
//                   j.setJjfzNative(mingzu);
//                   j.setJjfzShenfenzheng(shengfengzhenghao);
//                   j.setJjfzDanwei(danwei);
//                   j.setJjfzPhone(phone);
//                   j.setJjfzZhiwu(zhiwu);
//                   j.setJjfzWenhua(wenhua);
//                   j.setJjfzEntity(shengfen);
//                   j.setJjfzYear(year);
//                   j.setJjfzTime(date);
//                   result = jijifenziMapper.UpdateJijifenzi(j);
//                   if (result == 1) {
//                       map.put("result", "success");
//                   } else {
//                       map.put("result", "error");
//                   }
//               }
//            }
//            if(shengfen.equals("2")) {
//                Fazhanduixiang f = fazhanduixiangMapper.queryAll(id);
//                if(f==null){
//                    map.put("result", "null");
//                }else {
//                    // f.setFzdxId(id);
//                    f.setFzdxName(name);
//                    f.setFzdxSex(sex);
//                    f.setFzdxNative(mingzu);
//                    f.setFzdxShenfenzheng(shengfengzhenghao);
//                    f.setFzdxDanwei(danwei);
//                    f.setFzdxPhone(phone);
//                    f.setFzdxZhiwu(zhiwu);
//                    f.setFzdxWenhua(wenhua);
//                    f.setFzdxEntity(shengfen);
//                    f.setFzdxYear(year);
//                    f.setFzdxTime(date);
//
//                    result = fazhanduixiangMapper.UpdateFazhanduixiang(f);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }
//            if(shengfen.equals("3")) {
//                Yvbeidangyuan y = yvbeidangyuanMapper.queryAll(id);
//                if(y==null){
//                    map.put("result", "null");
//                }else {
//                    //y.setYbdyId(id);
//                    y.setYbdyName(name);
//                    y.setYbdySex(sex);
//                    y.setYbdyNative(mingzu);
//                    y.setYbdyShengfenzheng(shengfengzhenghao);
//                    y.setYbdyDanwei(danwei);
//                    y.setYbdyPhone(phone);
//                    y.setYbdyZhiwu(zhiwu);
//                    y.setYbdyWenhua(wenhua);
//                    y.setYbdyEntity(shengfen);
//                    y.setYbdyYear(year);
//                    y.setYbdyTime(date);
//
//                    result = yvbeidangyuanMapper.UpdateYvbeidangyuan(y);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }
//            if(shengfen.equals("4")) {
//                Dangyuanzhuanzheng d = dangyuanzhuanzhengMapper.queryAll(id);
//                if(d==null){
//                    map.put("result", "null");
//                }else {
//                    //d.setDyzzId(id);
//                    d.setDyzzName(name);
//                    d.setDyzzSex(sex);
//                    d.setDyzzNative(mingzu);
//                    d.setDyzzShengfengzheng(shengfengzhenghao);
//                    d.setDyzzDanwei(danwei);
//                    d.setDyzzPhone(phone);
//                    d.setDyzzZhiwu(zhiwu);
//                    d.setDyzzWenhua(wenhua);
//                    d.setDyzzEntity(shengfen);
//                    d.setDyzzYear(year);
//                    d.setDyzzTime(date );
//                    result = dangyuanzhuanzhengMapper.UpdateDangyuanzhuanzheng(d);
//                    if (result == 1) {
//                        map.put("result", "success");
//                    } else {
//                        map.put("result", "error");
//                    }
//                }
//            }
//            return map;
//        }
//
//        @PostMapping("/cun_List")//根据镇找村
//        public Map<String ,Object> cun_List(@RequestParam Integer zhenId
//        ){
//            Map<String , Object> map = new HashMap<>();
//            try {
//            List<Village> cuns =jijifenziMapper.cun_List(zhenId);
//                map.put("data",cuns);
//            }catch (Exception e){
//                map.put("state","error");
//                map.put("data",null);
//            }
//            return map;
//        }
//    @PostMapping("/ZuzifazhanExcelUpload")//积极分子导入excel
//    @OperationLogDetail(operationType = OperationType.IMPORT,operationUnit = OperationUnit.ZUZHIFAZHAN)
//    public Map<String ,Object> ZuzifazhanExcelUpload(
//            @RequestParam Integer managerId,
//            @RequestParam Integer entity,
//            @RequestParam("ExcelFile") MultipartFile ExcelFile
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        //try {
//           // List<Jijifenzi> demoList = new ArrayList<>();
//            Workbook book = null;
//            //判断是xls还是xlsx
//            try {
//                book = new XSSFWorkbook(ExcelFile.getInputStream());
//            } catch (Exception ex) {
//                try {
//                    book = new HSSFWorkbook(new POIFSFileSystem(ExcelFile.getInputStream()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            //获取一共有多少sheet,遍历
//            int numberOfSheets = book.getNumberOfSheets();
//            for (int i = 0; i < numberOfSheets; i++) {
//                Sheet sheet = book.getSheetAt(i);
//                //获取sheet中有多少行，遍历每一行
//                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Integer k = 0;
//                for (int j = 1; j < physicalNumberOfRows; j++) {
//                    if (j == 1) {
//                        continue;//标题行
//                    }
//                    Row row = sheet.getRow(j);//获得当前行数据
//                    if (entity == 1) {
//                        Jijifenzi jijifenzi = new Jijifenzi();
//                        if (jijifenziMapper.findJijifenziByxuhao(row.getCell(13).toString()) == null) {
//                            jijifenzi.setJjfzName(row.getCell(3).toString());
//                            if (row.getCell(10) != null) {
//                                try {
//                                    jijifenzi.setJjfzTime((Date)sdf.parse(row.getCell(10).toString()));
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                                jijifenzi.setJjfzSex(row.getCell(4).getStringCellValue());
//                                jijifenzi.setJjfzNative((int) row.getCell(5).getNumericCellValue());
//                                jijifenzi.setJjfzShenfenzheng(row.getCell(6).getStringCellValue());
//                                jijifenzi.setJjfzDanwei(row.getCell(11).getStringCellValue());
//                                jijifenzi.setJjfzPhone(row.getCell(7).toString());
//                                jijifenzi.setJjfzZhiwu(row.getCell(12).toString());
//                                jijifenzi.setJjfzWenhua(row.getCell(9).toString());
//                                jijifenzi.setJjfzVillage((int) row.getCell(1).getNumericCellValue());
//                                jijifenzi.setJjfzZu((int) row.getCell(2).getNumericCellValue());
//                                jijifenzi.setJjfzXuhao(row.getCell(13).toString());
//                                k = jijifenziMapper.InsertJijifenzi(jijifenzi);
//                            }
//                        } else if (entity == 2) {
//                            Fazhanduixiang fazhanduixiang = new Fazhanduixiang();
//                            if (fazhanduixiangMapper.findFazhanduixiangByxuhao(row.getCell(13).toString()) == null) {
//                                fazhanduixiang.setFzdxName(row.getCell(3).toString());
//                                if (row.getCell(10) != null) {
//                                    try {
//                                        fazhanduixiang.setFzdxTime((Date) sdf.parse(row.getCell(10).toString()));
//                                    } catch (ParseException e) {
//                                        System.out.println("dassds");
//                                        e.printStackTrace();
//                                    }
//                                }
//                                fazhanduixiang.setFzdxSex(row.getCell(4).getStringCellValue());
//                                fazhanduixiang.setFzdxNative((int) row.getCell(5).getNumericCellValue());
//                                fazhanduixiang.setFzdxShenfenzheng(row.getCell(6).getStringCellValue());
//                                fazhanduixiang.setFzdxDanwei(row.getCell(11).getStringCellValue());
//                                fazhanduixiang.setFzdxPhone(row.getCell(7).toString());
//                                fazhanduixiang.setFzdxZhiwu(row.getCell(12).toString());
//                                fazhanduixiang.setFzdxWenhua(row.getCell(9).toString());
//                                fazhanduixiang.setFzdxVillage((int) row.getCell(1).getNumericCellValue());
//                                fazhanduixiang.setFzdxZu((int) row.getCell(2).getNumericCellValue());
//                                fazhanduixiang.setFzdxXuhao(row.getCell(13).toString());
//                                k = fazhanduixiangMapper.InsertFazhanduixiang(fazhanduixiang);
//                            }
//                        } else if (entity == 3) {
//                            Yvbeidangyuan yvbeidangyuan = new Yvbeidangyuan();
//                            if (yvbeidangyuanMapper.findYvbeidangyuanByxuhao(row.getCell(13).toString()) == null) {
//                                yvbeidangyuan.setYbdyName(row.getCell(3).toString());
//                                if (row.getCell(10) != null) {
//                                    try {
//                                        yvbeidangyuan.setYbdyTime((Date)sdf.parse(row.getCell(10).toString()));
//                                    } catch (ParseException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                yvbeidangyuan.setYbdySex(row.getCell(4).getStringCellValue());
//                                yvbeidangyuan.setYbdyNative((int) row.getCell(5).getNumericCellValue());
//                                yvbeidangyuan.setYbdyShengfenzheng(row.getCell(6).getStringCellValue());
//                                yvbeidangyuan.setYbdyDanwei(row.getCell(11).getStringCellValue());
//                                yvbeidangyuan.setYbdyPhone(row.getCell(7).toString());
//                                yvbeidangyuan.setYbdyZhiwu(row.getCell(12).toString());
//                                yvbeidangyuan.setYbdyWenhua(row.getCell(9).toString());
//                                yvbeidangyuan.setYbdyVillage((int) row.getCell(1).getNumericCellValue());
//                                yvbeidangyuan.setYbdyZu((int) row.getCell(2).getNumericCellValue());
//                                yvbeidangyuan.setYbdyXuhao(row.getCell(13).toString());
//                                k = yvbeidangyuanMapper.InsertYvbeidangyuan(yvbeidangyuan);
//                            }
//                        } else if (entity == 4) {
//                            Dangyuanzhuanzheng dangyuanzhuanzheng = new Dangyuanzhuanzheng();
//                            if (dangyuanzhuanzhengMapper.findDangyuanzhuanzhengByxuhao(row.getCell(13).toString()) == null) {
//                                dangyuanzhuanzheng.setDyzzName(row.getCell(3).toString());
//                                if (row.getCell(10) != null) {
//                                    try {
//                                        dangyuanzhuanzheng.setDyzzTime((Date)sdf.parse(row.getCell(10).toString()));
//                                    } catch (ParseException e) {
//                                        System.out.println("dassds");
//                                        e.printStackTrace();
//                                    }
//                                }
//                                dangyuanzhuanzheng.setDyzzSex(row.getCell(4).getStringCellValue());
//                                dangyuanzhuanzheng.setDyzzNative((int) row.getCell(5).getNumericCellValue());
//                                dangyuanzhuanzheng.setDyzzShengfengzheng(row.getCell(6).getStringCellValue());
//                                dangyuanzhuanzheng.setDyzzDanwei(row.getCell(11).getStringCellValue());
//                                dangyuanzhuanzheng.setDyzzPhone(row.getCell(7).toString());
//                                dangyuanzhuanzheng.setDyzzZhiwu(row.getCell(12).toString());
//                                dangyuanzhuanzheng.setDyzzWenhua(row.getCell(9).toString());
//                                dangyuanzhuanzheng.setDyzzVillage((int) row.getCell(1).getNumericCellValue());
//                                dangyuanzhuanzheng.setDyzzZu((int) row.getCell(2).getNumericCellValue());
//                                dangyuanzhuanzheng.setDyzzXuhao(row.getCell(13).toString());
//                                k = dangyuanzhuanzhengMapper.Insertdangyuanzhuanzheng(dangyuanzhuanzheng);
//                            }
//                        }
//                        if (k != 0) {
//                            if (map.get("success") != null) {
//                                map.put("success", map.get("success") + "," + String.valueOf(j));
//                            } else {
//                                map.put("success", String.valueOf(j));
//                            }
//                        } else {
//                            if (map.get("error") != null) {
//                                map.put("error", map.get("error") + "," + String.valueOf(j));
//                            } else {
//                                map.put("error", String.valueOf(j));
//                            }
//                        }
//
//                    }
//                }
//
//
//        return map;
//    }
//    @PostMapping("/findJijifenziByxuhao")
//    public Map<String ,Object> findJijifenziByxuhao(@RequestParam String xuhao
//    ){
//        Map<String , Object> map = new HashMap<>();
//        try {
//            Jijifenzi jijifenzi=jijifenziMapper.findJijifenziByxuhao(xuhao);
//            map.put("data",jijifenzi);
//        }catch (Exception e){
//            map.put("state","error");
//            map.put("data",null);
//        }
//        return map;
//    }
//
//    @PostMapping("/findDangyaunByQu")
//    public Map<String, Object> findDangyaunByQu(
//           // @RequestParam String time
//    ) {
//        Map<String,Object> map=new HashMap<>();
//        Integer count;
//        try {
//            String time=null;
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//                Date data=new Date();
//                time=sdf.format(data);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Integer a=Integer.parseInt(time);
//            List b=new ArrayList();
//            List<String> zName=null;
//            for (int i=2012;i<=a;i++) {
//                String time1 = 2012 + "/" + 1 + "/" + 1;
//                String time2 = (i + 1) + "/" + 1 + "/" + 1;
//                for (int zId=1;zId<=8;zId++) {
//                    count = jijifenziMapper.findDangyaunByQu(zId, time1, time2);
//                    zName = jijifenziMapper.findzName(zId);
//                    b.add(zName);
//                    zName.add(count+"");
//                }
//            }
//            map.put("data", b);
//        }catch (Exception e){
//            map.put("data","error");
//        }
//        return map;
//    }
//
//    @PostMapping("/countByqu")
//    public  Map<String, Object> countByqu(
//    ) {
//        Map<String,Object> map=new HashMap<>();
//        Integer zId;
//        List list=new ArrayList();
//        try {
//            for (zId=1;zId<=8;zId++) {
//                List<String> zName=jijifenziMapper.findzName(zId);
//                Integer jjfz = jijifenziMapper.countByZhen(zId);
//                Integer fzdx=fazhanduixiangMapper.countByZhen(zId);
//                Integer ybdy=yvbeidangyuanMapper.countByZhen(zId);
//                Integer zsdy = jijifenziMapper.countdangyuanByZhen(zId);
//                list.add(zName);
//                list.add(jjfz);
//                list.add(fzdx);
//                list.add(ybdy);
//                list.add(zsdy);
//            }
//            map.put("data",list);
//        }catch (Exception e){
//            map.put("data","error");
//        }
//        return map;
//    }
//
//    @PostMapping("/FindByZhen")
//    public  Map<String, Object> FindByZhen(
//            @RequestParam Integer zId
//            //@RequestParam String time
//            //@RequestParam Integer entity
//    ) {
//        Map<String,Object> map=new HashMap<>();
//        String time=null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//            Date data=new Date();
//             time=sdf.format(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Integer a=Integer.parseInt(time);
//        try {
//            List b=new ArrayList();;
//            for (int i=2012;i<=a;i++){
//                String time1=2012+"/"+1+"/"+1;
//                String time2=(i+1)+"/"+1+"/"+1;
//                Integer jjfz = jijifenziMapper.FindByZhen(zId,time1,time2);
//                Integer fzdx = fazhanduixiangMapper.FindByZhen(zId,time1,time2);
//                Integer ybdy = yvbeidangyuanMapper.FindByZhen(zId,time1,time2);
//                Integer dyzz = dangyuanzhuanzhengMapper.countByZhen(zId,time1,time2);
//                Integer zsdy = jijifenziMapper.FinddangyuanByZhen(zId,time1,time2);
//                b.add(jjfz);
//                b.add(fzdx);
//                b.add(ybdy);
//                b.add(dyzz);
//                b.add(zsdy);
//                map.put("data",b);
//            }
//        } catch (Exception e) {
//            map.put("data", "error");
//        }
//        return map;
//    }
//
////    @PostMapping("/findDangyaunByCun")
////    public  Map<String , Object>  findDangyaunByCun(
////            @RequestParam Integer vId,
////            @RequestParam String time
////    ) {
////        Map<String , Object> map = new HashMap<>();
////        String[] strs=time.split("~");
////        String time1=strs[0];
////        String time2=strs[1];
////        try {
////        Page<Partymemberinformation> dangyuan=jijifenziMapper.findDangyaunByCun(vId, time1, time2);
//////        map.put("status", "success");
//////        //总页数
//////        map.put("total", dangyuan.getPages());
//////        //记录总数
//////        map.put("count", dangyuan.getTotal());
//////        //页面大小
//////        map.put("pageSize", dangyuan.getPageSize());
//////        //数据
////        map.put("data", dangyuan);
//////        //当前第几页
//////        map.put("nowPage", dangyuan.getPageNum());
////        }catch (Exception e){
////            map.put("state","error");
////            map.put("data",null);
////        }
////        return map;
////    }
//    @PostMapping("/findByCun")
//    public  Map<String , Object> findByCun(
//            @RequestParam Integer vId,
//            //@RequestParam String time,
//            @RequestParam Integer entity
//
//    ) {
//        Map<String , Object> map = new HashMap<>();
//        Page<Zuzifazhan> count=null;
////        String[] strs=time.split("~");
////        String time1=strs[0];
////        String time2=strs[1];
////        try {
//            if(entity==1) {
//                count = jijifenziMapper.findByCun(vId);
//                map.put("data", count);
//            }else if(entity==2){
//                count = fazhanduixiangMapper.findByCun(vId);
//                map.put("data", count);
//            }else if(entity==3){
//                count = yvbeidangyuanMapper.findByCun(vId);
//                map.put("data", count);
//            }else if(entity==4) {
//                Page<Partymember> dangyuan = jijifenziMapper.findDangyaunByCun(vId);
//                for (int i=0;i<dangyuan.size();i++){
//                    Integer a=dangyuan.get(i).getPartymemberinformationId();
//                    String name=jijifenziMapper.findpName(a);
//                    dangyuan.get(i).setPmiName(name);
//                    String sex=jijifenziMapper.findpSex(a);
//                    dangyuan.get(i).setPmiSex(sex);
//                    Integer nation=jijifenziMapper.findpNation(a);
//                    String nationname =jijifenziMapper.nation(nation);
//                    dangyuan.get(i).setNationname(nationname);
//                    String zhiwu=jijifenziMapper.zhiwu(dangyuan.get(i).getPmiPost());
//                    dangyuan.get(i).setZhuwu(zhiwu);
//
//                }
//                map.put("data", dangyuan);
//            }else if(entity==5){
//                count=dangyuanzhuanzhengMapper.GetInfoByCun(vId);
//                map.put("data", count);
//            }
////            map.put("data", count);
////            map.put("status", "success");
////            //总页数
////            map.put("total", count.getPages());
////            //记录总数
////            map.put("count", count.getTotal());
////            //页面大小
////            map.put("pageSize", count.getPageSize());
////            //当前第几页
//
////            map.put("nowPage", count.getPageNum());
////        }catch (Exception e){
////            map.put("state","error");
////            map.put("data",null);
////        }
//        return map;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
