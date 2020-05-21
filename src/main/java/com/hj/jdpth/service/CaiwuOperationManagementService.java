package com.hj.jdpth.service;

import com.hj.jdpth.domain.Operationmanagement;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CaiwuOperationManagementService {
    Map<String, Object> findOperationManagement(int regionId, int zhenId, int villageId, int style, Integer startPage, Integer pageSize);

    Map<String, Object> deleteOperationManagement(int operationmanagementId);

    Map<String, Object> deleteManyOperationManagement(String[] operationmanagementId);

    Map<String, Object> insertOperationManagement(Operationmanagement operationmanagement);

    Map<String, Object> uploadFile(MultipartFile file);

    Map<String, Object> updateOperationManagement(Operationmanagement operationmanagement);

    Map<String, Object> importExcel(String path);
}
