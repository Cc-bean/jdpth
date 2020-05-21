package com.hj.jdpth.service.impl;

import com.hj.jdpth.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //String path = "";
            /* System.out.println("进入");;*/
            String path = "";

            //获取原始文件名称
            String originalFileName = file.getOriginalFilename();
            //System.out.println("原始文件的名称"+originalFileName);

            //获取文件的类型，以“.”作为标识
            String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            path = "E:\\HJResourse\\Zuzhihuodong\\";
            File filePath = new File(path);
            System.out.println("文件保存的路径" + path);
            if (!filePath.exists() && !filePath.isDirectory()) {
                //System.out.println("图片目录不存在，创建图片路径"+filePath);
                filePath.mkdir();
            }
            String fileName = System.currentTimeMillis() + "." + type;
            //System.out.println("文件新名称"+fileName);
            //在指定的路径下面创建一个新的文件
            File targerFile = new File(path, fileName);
            //将文件保存到服务器的指定位置
            try {
                file.transferTo(targerFile);
                map.put("path", fileName);
                map.put("name", fileName);
                map.put("type", type);
                map.put("state", "success");
                return map;
            } catch (IllegalStateException | IOException e) {
                System.out.println("文件保存错误");
                map.put("path", "");
                map.put("name", "此文件上传失败1");
                map.put("state", "error1");
                e.printStackTrace();
            }
        } catch (Exception e) {
            map.put("path", "");
            map.put("name", "此文件上传失败2");
            map.put("state", "error2");
            e.printStackTrace();
        }
        return map;
    }
}
