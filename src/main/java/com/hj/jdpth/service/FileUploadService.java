package com.hj.jdpth.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public interface FileUploadService {
    public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest request);
}
