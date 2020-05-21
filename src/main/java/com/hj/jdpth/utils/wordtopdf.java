package com.hj.jdpth.utils;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class wordtopdf {


    /*public static void main(String[] args) {
        // TODO Auto-generated method stub
        doc2pdf("C:\\Users\\闫永强\\Desktop\\政策\\中华人民共和国监察法.doc", "C:\\Users\\闫永强\\Desktop\\政策\\中华人民共和国监察法.pdf");
    }*/
    public void doc2pdf(String inPath, String outPath) {
        File wordFile = new File(inPath);
        if (!wordFile.exists()) {
            System.out.println("源文件不存在:{}" + inPath);
            return;
        }
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            //开始时间
            long old = System.currentTimeMillis();
            //获取文件
            File file = new File(outPath);
            //获取文件流
            FileOutputStream os = new FileOutputStream(file);
            // Address是将要被转化的word文档
            Document doc = new Document(inPath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF
            doc.save(os, SaveFormat.PDF);
            //结束时间
            long now = System.currentTimeMillis();
            System.out.println(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getLicense() {
        boolean result = false;
        try {
            // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            InputStream is = wordtopdf.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
