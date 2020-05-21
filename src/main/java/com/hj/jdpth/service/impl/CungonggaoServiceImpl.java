package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Villagebulletin;
import com.hj.jdpth.repository.CungonggaoMapper;
import com.hj.jdpth.service.CungonggaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CungonggaoServiceImpl implements CungonggaoService {
    @Autowired
    CungonggaoMapper cungonggaoMapper;

    @Override
    public Map<String, Object> findCunGongGao(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, String gongGaoName, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (gongGaoName != null) {
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao = cungonggaoMapper.findgongGaoBygongGaoName(gongGaoName);
                if (cungonggao == null) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 2 || style == 3) {
            System.out.println("进入区级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao;
                if (cun_id != 0) {
                    cungonggao = cungonggaoMapper.findgongGaoByCunId(cun_id);
                } else if (zhen_id != 0) {
                    cungonggao = cungonggaoMapper.findgongGaoByZhenId(zhen_id);
                } else {
                    cungonggao = cungonggaoMapper.findgongGaoByRegionId(qu_id);
                }

                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 4) {
            System.out.println("进入镇级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao;
                if (cun_id != 0) {
                    cungonggao = cungonggaoMapper.findgongGaoByCunId(cun_id);
                } else {
                    cungonggao = cungonggaoMapper.findgongGaoByZhenId(zhen_id);
                }

                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 5) {
            System.out.println("进入村级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao = cungonggaoMapper.findgongGaoByCunId(cun_id);
                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> findgongGaoXingQing_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> gonggaoxiangqing = cungonggaoMapper.findgongGaoXingQing_yp(id);
        result.put("cunzuzhihuodong", gonggaoxiangqing);
        return result;
    }

    @Override
    public Map<String, Object> deleteGonggaoById_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = cungonggaoMapper.deleteGonggaoById_yp(id);
        result.put("flag", flag);
        return result;
    }

    @Override
    public Map<String, Object> deleteManyGonggaoById_yp(String Ids) {
        String[] ids = Ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String id : ids) {
            Boolean flag = cungonggaoMapper.deleteGonggaoById_yp(Integer.parseInt(id));
            if (flag == true) {
                if (result.get("success") != null) {
                    result.put("success", id + "," + result.get("success"));
                } else {
                    result.put("success", id);
                }
            } else {
                if (result.get("error") != null) {
                    result.put("error", id + "," + result.get("error"));
                } else {
                    result.put("error", id);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> insertGonggao_yp(Villagebulletin villagebulletin, String ggTime) {
        Map<String, Object> result = new HashMap<>();

        villagebulletin.setVbLanchtime(Date.valueOf(ggTime));
        int i = cungonggaoMapper.insertGonggao_yp(villagebulletin);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> daoruGonggao_yp(Villagebulletin villagebulletin) {
        Map<String, Object> result = new HashMap<>();
        int i = cungonggaoMapper.insertGonggao_yp(villagebulletin);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> updateGonggao_yp(Villagebulletin villagebulletin, String ggTime) {
        Map<String, Object> result = new HashMap<>();

        villagebulletin.setVbLanchtime(Date.valueOf(ggTime));
        Boolean flag = cungonggaoMapper.updateGonggao_yp(villagebulletin);
        if (flag == true) {
            result.put("result", "更新成功");
        } else {
            result.put("result", "更新失败");
        }
        return result;
    }

    @Override
    public Boolean findgonggaoByxuhao_yp(String vbXuhao) {
        if (cungonggaoMapper.findgonggaoByxuhao_yp(vbXuhao) != null) {
            return false;
        } else {
            return true;
        }
    }
}
