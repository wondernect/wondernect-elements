package com.wondernect.elements.easyoffice.excel.service;

import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.common.error.OfficeBusinessError;
import com.wondernect.elements.easyoffice.common.exception.OfficeBusinessException;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportDataHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportVerifyHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;
import com.wondernect.elements.easyoffice.excel.model.ESExcelItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ESExcelService
 * Author: chenxun
 * Date: 2020/11/25 11:23
 * Description:
 */
public abstract class ESExcelImportFileService extends ESExcelBaseService {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelImportFileService.class);

    public MultipartFile excelDataImport(
            String templateId,
            Class<?> excelEntity,
            ESExcelImportDataHandler excelImportDataHandler,
            ESExcelImportVerifyHandler excelImportVerifyHandler,
            int titleRows,
            int headRows,
            InputStream fileInputStream,
            String failedfileName
    ) {
        ExcelImportResult<Map<String, Object>> result = super.excelDataImport(templateId, excelEntity, excelImportDataHandler, excelImportVerifyHandler, titleRows, headRows, fileInputStream);
        if (ESObjectUtils.isNotNull(result) && result.isVerfiyFail() && CollectionUtils.isNotEmpty(result.getFailList())) {
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.ALL_VALUE, true, failedfileName);
            try {
                result.getFailWorkbook().write(fileItem.getOutputStream());
            } catch (IOException e) {
                logger.error("Excel export failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
                throw new OfficeBusinessException(OfficeBusinessError.EXCEL_EXPORT_FAILED);
            }
            return new CommonsMultipartFile(fileItem);
        }
        return null;
    }

    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        return new ArrayList<>();
    }

    public void saveExcelImportEntityData(Map<String, Object> map, List<ESExcelItem> excelItemList) {

    }
}
