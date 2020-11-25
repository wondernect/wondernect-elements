package com.wondernect.elements.easyoffice.excel.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.wondernect.elements.easyoffice.common.error.OfficeBusinessError;
import com.wondernect.elements.easyoffice.common.exception.OfficeBusinessException;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
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
public abstract class ESExcelExportFileService extends ESExcelBaseService {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelExportFileService.class);

    public <S> MultipartFile excelDataExport(
            String templateId,
            Class<?> excelEntity,
            List<S> excelEntityDataList,
            String title,
            String sheetName,
            String fileName
    ) {
        List<ExcelExportEntity> excelExportEntityList = new ArrayList<>();
        List<Map<String, Object>> excelExportEntityDataList = new ArrayList<>();
        super.excelDataExport(templateId, excelEntity, excelEntityDataList, excelExportEntityList, excelExportEntityDataList);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), excelExportEntityList, excelExportEntityDataList);
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.ALL_VALUE, true, fileName);
        try {
            workbook.write(fileItem.getOutputStream());
        } catch (IOException e) {
            logger.error("Excel export failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_EXPORT_FAILED);
        }
        return new CommonsMultipartFile(fileItem);
    }

    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        return new ArrayList<>();
    }
}
