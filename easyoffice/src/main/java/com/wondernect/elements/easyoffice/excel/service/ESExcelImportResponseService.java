package com.wondernect.elements.easyoffice.excel.service;

import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.EasyExcel;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportDataHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportVerifyHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;
import com.wondernect.elements.easyoffice.excel.model.ESExcelItem;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public abstract class ESExcelImportResponseService extends ESExcelBaseService {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelImportResponseService.class);

    public void excelDataImport(
            String templateId,
            Class<?> excelEntity,
            ESExcelImportDataHandler excelImportDataHandler,
            ESExcelImportVerifyHandler excelImportVerifyHandler,
            int titleRows,
            int headRows,
            InputStream fileInputStream,
            String failedfileName,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ExcelImportResult<Map<String, Object>> result = super.excelDataImport(templateId, excelEntity, excelImportDataHandler, excelImportVerifyHandler, titleRows, headRows, fileInputStream);
        if (ESObjectUtils.isNotNull(result) && result.isVerfiyFail() && CollectionUtils.isNotEmpty(result.getFailList())) {
            EasyExcel.exportExcel(result.getFailWorkbook(), failedfileName, request, response);
        }
    }

    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        return new ArrayList<>();
    }

    public void saveExcelImportEntityData(Map<String, Object> map, List<ESExcelItem> excelItemList) {

    }
}
