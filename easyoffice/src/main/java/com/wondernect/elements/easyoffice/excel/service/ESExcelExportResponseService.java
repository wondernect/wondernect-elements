package com.wondernect.elements.easyoffice.excel.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.wondernect.elements.easyoffice.excel.EasyExcel;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public abstract class ESExcelExportResponseService extends ESExcelBaseService {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelExportResponseService.class);

    public <S> void excelDataExport(
            String templateId,
            Class<?> excelEntity,
            List<S> excelEntityDataList,
            String title,
            String sheetName,
            String fileName,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        List<ExcelExportEntity> excelExportEntityList = new ArrayList<>();
        List<Map<String, Object>> excelExportEntityDataList = new ArrayList<>();
        super.excelDataExport(templateId, excelEntity, excelEntityDataList, excelExportEntityList, excelExportEntityDataList);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), excelExportEntityList, excelExportEntityDataList);
        EasyExcel.exportExcel(workbook, fileName, request, response);
    }

    @Override
    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        return new ArrayList<>();
    }
}
