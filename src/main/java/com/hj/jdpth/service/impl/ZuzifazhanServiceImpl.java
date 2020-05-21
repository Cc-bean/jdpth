package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Zuzifazhan;
import com.hj.jdpth.repository.ZuzifazhanMapper;
import com.hj.jdpth.service.ZuzifazhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZuzifazhanServiceImpl implements ZuzifazhanService {

    @Autowired
    ZuzifazhanMapper zuzifazhanMapper;

    @Override
    public Map<String, Object> GetInfo(Integer quId, Integer zId, Integer vId, String name, String entity, Integer startPage, Integer pageSize, Integer adminId) {
        Map<String, Object> map = new HashMap<>();
        Page<Zuzifazhan> zuzifazhan = null;
        if (adminId == 2 || adminId == 3) {
            PageHelper.startPage(startPage, pageSize);
            if (vId != 0) {
                if (name != null && entity == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByNameAndvId(name, vId);
                } else if (entity != null && name == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndvId(vId, entity);
                } else if (name != null && entity != null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndvIdandName(name, vId, entity);
                } else {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetInfoByCun(vId);
                }
            } else if (zId != 0) {
                if (name != null && entity == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByNameAndzId(name, zId);
                } else if (entity != null && name == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, entity);
                } else if (name != null && entity != null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndzIdandName(name, zId, entity);
                } else {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetInfoByZhen(zId);
                }
            } else if (name != null && entity == null && zId == 0 && vId == 0) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetByName(name);
            } else if (entity != null && name == null && zId == 0 && vId == 0) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetInfoByEntity(entity);
            } else if (name != null && entity != null && zId == 0 && vId == 0) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetByNameAndEntity(name, entity);
            } else {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GentInfoByQu(quId);
            }
        } else if (adminId == 4) {
            if (vId != 0) {
                if (name != null && entity == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByNameAndvId(name, vId);
                } else if (entity != null && name == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndvId(vId, entity);
                } else if (name != null && entity != null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndvIdandName(name, vId, entity);
                } else {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetInfoByCun(vId);
                }
            } else {
                if (name != null && entity == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByNameAndzId(name, zId);
                } else if (entity != null && name == null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, entity);
                } else if (name != null && entity != null) {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetByEntityAndzIdandName(name, zId, entity);
                } else {
                    PageHelper.startPage(startPage, pageSize);
                    zuzifazhan = zuzifazhanMapper.GetInfoByZhen(zId);
                }
            }
        } else if (adminId == 5) {
            if (name != null && entity == null) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetByNameAndvId(name, vId);
            } else if (entity != null && name == null) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetByEntityAndvId(vId, entity);
            } else if (name != null && entity != null) {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetByEntityAndvIdandName(name, vId, entity);
            } else {
                PageHelper.startPage(startPage, pageSize);
                zuzifazhan = zuzifazhanMapper.GetInfoByCun(vId);
            }
        }
        if (zuzifazhan == null) {
            map.put("status", "null");
            map.put("data", "没有数据");
        } else {
            for (int i = 0; i < zuzifazhan.size(); i++) {
                String villageName = zuzifazhanMapper.GetVillageById(zuzifazhan.get(i).getZzfzVillage());
                zuzifazhan.get(i).setVillageName(villageName);
                String zuName = zuzifazhanMapper.GetZuById(zuzifazhan.get(i).getZzfzZu());
                zuzifazhan.get(i).setZuName(zuName);
            }
            map.put("status", "success");
            //总页数
            map.put("total", zuzifazhan.getPages());
            //记录总数
            map.put("count", zuzifazhan.getTotal());
            //页面大小
            map.put("pageSize", zuzifazhan.getPageSize());
            //数据
            map.put("data", zuzifazhan);
            //当前第几页
            map.put("nowPage", zuzifazhan.getPageNum());
        }
        return map;
    }

    @Override
    public Map<String, Object> Insert(Zuzifazhan zuzifazhan) {
        Map<String, Object> map = new HashMap<>();
        Integer count = zuzifazhanMapper.InsertZuzifazhan(zuzifazhan);
        if (count == 1) {
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> Update(Integer zzfzId) {
        Map<String, Object> map = new HashMap<>();
        Zuzifazhan zuzifazhan = zuzifazhanMapper.GetById(zzfzId);
        if (zuzifazhan == null) {
            map.put("data", "此人不存在");
        } else {
            Integer count = zuzifazhanMapper.UpdateZuzifazhan(zuzifazhan);
            if (count == 1) {
                map.put("status", "success");
            } else {
                map.put("status", "error");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> Delete(Integer zzfzId) {
        Map<String, Object> map = new HashMap<>();
        Integer count = zuzifazhanMapper.Delete(zzfzId);
        if (count == 1) {
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> Select(String name, String entity, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Page<Zuzifazhan> zuzifazhan = null;
        if (name != null && entity == null) {
            PageHelper.startPage(startPage, pageSize);
            zuzifazhan = zuzifazhanMapper.GetByName(name);
            for (int i = 0; i < zuzifazhan.size(); i++) {
                String villageName = zuzifazhanMapper.GetVillageById(zuzifazhan.get(i).getZzfzVillage());
                zuzifazhan.get(i).setVillageName(villageName);
                String zuName = zuzifazhanMapper.GetZuById(zuzifazhan.get(i).getZzfzZu());
                zuzifazhan.get(i).setZuName(zuName);

            }
            if (zuzifazhan == null) {
                map.put("status", "null");
                map.put("data", "没有数据");
            } else {
                map.put("status", "success");
                //总页数
                map.put("total", zuzifazhan.getPages());
                //记录总数
                map.put("count", zuzifazhan.getTotal());
                //页面大小
                map.put("pageSize", zuzifazhan.getPageSize());
                //数据
                map.put("data", zuzifazhan);
                //当前第几页
                map.put("nowPage", zuzifazhan.getPageNum());
            }
        }
        if (entity != null && name == null) {
            PageHelper.startPage(startPage, pageSize);
            zuzifazhan = zuzifazhanMapper.GetInfoByEntity(entity);
            for (int i = 0; i < zuzifazhan.size(); i++) {
                String villageName = zuzifazhanMapper.GetVillageById(zuzifazhan.get(i).getZzfzVillage());
                zuzifazhan.get(i).setVillageName(villageName);
                String zuName = zuzifazhanMapper.GetZuById(zuzifazhan.get(i).getZzfzZu());
                zuzifazhan.get(i).setZuName(zuName);

            }
            if (zuzifazhan == null) {
                map.put("status", "null");
                map.put("data", "没有数据");
            } else {
                map.put("status", "success");
                //总页数
                map.put("total", zuzifazhan.getPages());
                //记录总数
                map.put("count", zuzifazhan.getTotal());
                //页面大小
                map.put("pageSize", zuzifazhan.getPageSize());
                //数据
                map.put("data", zuzifazhan);
                //当前第几页
                map.put("nowPage", zuzifazhan.getPageNum());
            }
        }
        if (name != null && entity != null) {
            PageHelper.startPage(startPage, pageSize);
            zuzifazhan = zuzifazhanMapper.GetByNameAndEntity(name, entity);
            for (int i = 0; i < zuzifazhan.size(); i++) {
                String villageName = zuzifazhanMapper.GetVillageById(zuzifazhan.get(i).getZzfzVillage());
                zuzifazhan.get(i).setVillageName(villageName);
                String zuName = zuzifazhanMapper.GetZuById(zuzifazhan.get(i).getZzfzZu());
                zuzifazhan.get(i).setZuName(zuName);
            }
            if (zuzifazhan == null) {
                map.put("status", "null");
                map.put("data", "没有数据");
            } else {
                map.put("status", "success");
                //总页数
                map.put("total", zuzifazhan.getPages());
                //记录总数
                map.put("count", zuzifazhan.getTotal());
                //页面大小
                map.put("pageSize", zuzifazhan.getPageSize());
                //数据
                map.put("data", zuzifazhan);
                //当前第几页
                map.put("nowPage", zuzifazhan.getPageNum());
            }
        } else {
            //什么都不做
        }

        return map;
    }
}
