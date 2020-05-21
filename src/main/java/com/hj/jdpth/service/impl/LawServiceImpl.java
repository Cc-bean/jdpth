package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Law;
import com.hj.jdpth.domain.Lawtype;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.LawMapper;
import com.hj.jdpth.repository.LawtypeMapper;
import com.hj.jdpth.service.LawService;
import com.hj.jdpth.utils.WordToHtml;
import com.hj.jdpth.utils.wordtopdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LawServiceImpl implements LawService {

    @Autowired
    LawtypeMapper lawtypeMapper;
    @Autowired
    LawMapper lawMapper;

    String path0 = "E:\\HJResourse\\Law\\";


    public List<Lawtype> queryAllType() {
        List<Lawtype> lawtypeList = lawtypeMapper.queryAllType_yyq();
        return lawtypeList;
    }

    @Override
    public Page<Law> queryLaw(Integer L_name, String name) {
        String Law_typeName = null;
        switch (L_name) {
            case 0:
                Law_typeName = "";
                break;
            case 1:
                Law_typeName = "党纪党规";
                break;
            case 2:
                Law_typeName = "法律法规";
                break;
            case 3:
                Law_typeName = "政策";
                break;
            case 4:
                Law_typeName = "其他";
                break;
            default:
                Law_typeName = "";
                break;
        }
        if (name.equals("") || name == null)
            name = "";
        return lawMapper.queryAllLaw_yyq(Law_typeName, name);
    }


    @Override
    public String insertLaw(Manager manager, Integer lawtype, Law law, MultipartFile law_wenjian) {
        long s = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        Law laws = new Law();
        try {
            int j = lawtypeMapper.insertLawtype_yyq(lawtype);

            //laws.setlType(lawtype.getlKey());
            laws.setlPubdate(date);
            // laws.setlPeople(manager.getManagerId());

            if (!law_wenjian.getOriginalFilename().equals("")) {
                String path = path0;
                File savedir = new File(path0);
                if (!savedir.exists()) savedir.mkdirs();
                String realName = law_wenjian.getOriginalFilename();
                String newName = realName;
                File newFile = new File(path + newName);
                //将内存中的数据写入磁盘
                try {
                    law_wenjian.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //WordToHtml wordToHtml=new WordToHtml();
                wordtopdf wtp = new wordtopdf();
                String pdfName = realName.substring(0, realName.lastIndexOf(".")) + ".pdf";
                wtp.doc2pdf(path + realName, path + pdfName);
            /*if((realName.substring(realName.lastIndexOf(".") + 1)).equals("docx")){
                try {
                    wordToHtml.Word2007ToHtml(path,newName,htmlName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            /*if((realName.substring(realName.lastIndexOf(".") + 1)).equals("doc")){
                try {
                    wordToHtml.Word2003ToHtml(path,path,newName,htmlName);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }*/
                laws.setlContent(pdfName);
                File file = new File(path + newName);
                file.delete();
            }
            int i = 0;
            if (j > 0) {
                i = lawMapper.insertLaw_yyq(laws);
            }
            if (i > 0 && j > 0) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @Override
    public String deleteLawAndLawType(Integer law_id) {
        Law law = lawMapper.queryByLawId_yyq(law_id);
        int lawtype_id = law.getlType();
        String path = path0 + law.getlContent();
        File file = new File(path);
        if (file.exists()) file.delete();
        int i = 0, j = 0;
        i = lawMapper.deleteLaw_yyq(law_id);
        j = lawtypeMapper.deletelawType_yyq(lawtype_id);
       /* i=lawMapper.deleteLaw_yyq(law_id);
        j=lawtypeMapper.deletelawType_yyq(lawtype_id);*/
        if (j > 0 && i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String deleteLawAndLawTypeMany(Integer[] law_ids) {
        int i = 0, j = 0, a = 0;
        for (int k = 0; k < law_ids.length; k++) {
            Law law = lawMapper.queryByLawId_yyq(law_ids[k]);
            int lawtype_id = law.getlType();
            String path = path0 + law.getlContent();
            File file = new File(path);
            if (file.exists()) file.delete();
            i = lawMapper.deleteLaw_yyq(law_ids[k]);
            j = lawtypeMapper.deletelawType_yyq(lawtype_id);
            if (i > 0 && j > 0) {
                a++;
            }
        }
        if (a == law_ids.length) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String updateLaw(Integer managerId, Lawtype lawtype, Law law, MultipartFile law_wenjian, Integer law_id) {
        long s = System.currentTimeMillis();
        Law law1 = lawMapper.queryByLawId_yyq(law_id);
        if (!law_wenjian.getOriginalFilename().equals("")) {
            String oldpath = path0 + law1.getlContent();
            File file = new File(oldpath);
            if (file.exists()) file.delete();

            String path = path0;
            String realName = law_wenjian.getOriginalFilename();
            String newName = s + realName;
            File newFile = new File(path + newName);
            //将内存中的数据写入磁盘
            try {
                law_wenjian.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            law.setlContent(newName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            law.setlPubdate(date);
            law.setlPeople(managerId);
        } else {
            law.setlPeople(law1.getlPeople());
            law.setlContent(law1.getlContent());
            law.setlPubdate(law1.getlPubdate());
        }
        law.setlType(law1.getlType());
        law.setLawId(law_id);
        lawtype.setlKey(law1.getlType());
        int i = lawtypeMapper.updateLawType_yyq(lawtype);
        int j = lawMapper.updateLaw_yyq(law);
        if (i > 0 || j > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @Override
    public List<Law> queryLeaderLaw(Integer L_name, Integer zhen_id) {
        String Law_typeName = null;
        switch (L_name) {
            case 0:
                Law_typeName = "";
                break;
            case 1:
                Law_typeName = "党纪党规";
                break;
            case 2:
                Law_typeName = "法律法规";
                break;
            case 3:
                Law_typeName = "政策";
                break;
            case 4:
                Law_typeName = "其他";
                break;
            default:
                Law_typeName = "";
                break;
        }
        return lawMapper.queryLeaderLaw_yyq(Law_typeName, zhen_id);
    }

}
