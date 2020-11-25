package com.wondernect.elements.easyoffice.excel.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.common.exception.OfficeBusinessException;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportDataHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelImportVerifyHandler;
import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;
import com.wondernect.elements.easyoffice.excel.model.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.util.ESExcelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class ESExcelBaseService {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelBaseService.class);

    public <S> void excelDataExport(
            String templateId,
            Class<?> excelEntity,
            List<S> excelEntityDataList,
            List<ExcelExportEntity> excelExportEntityList,
            List<Map<String, Object>> excelExportEntityDataList
    ) {
        List<ESExcelItem> allExcelItemList = ESExcelUtils.getAllEntityExcelItem(excelEntity);
        if (CollectionUtils.isEmpty(allExcelItemList)) {
            logger.error("导出excel对象属性数量必须大于0");
            throw new OfficeBusinessException("导出excel对象属性数量必须大于0");
        }
        List<ESExcelItem> excelItemList = new ArrayList<>();
        List<ESExcelItemHandler> excelItemHandlerList = generateExcelItemHandlerList(templateId);
        if (CollectionUtils.isNotEmpty(excelItemHandlerList)) {
            for (ESExcelItemHandler excelItemHandler : excelItemHandlerList) {
                // 1、构造导出entity list
                ExcelExportEntity excelExportEntity = new ExcelExportEntity(
                        ESStringUtils.isNotBlank(excelItemHandler.getItemTitle()) ? excelItemHandler.getItemTitle() : excelItemHandler.getItemName(),
                        excelItemHandler.getItemName()
                );
                excelExportEntity.setOrderNum(excelItemHandler.getItemOrder());
                excelExportEntityList.add(excelExportEntity);
                // 2、构造导出item list
                for (ESExcelItem excelItem : allExcelItemList) {
                    if (ESStringUtils.equals(excelItem.getName(), excelItemHandler.getItemName())) {
                        if (ESStringUtils.isNotBlank(excelItemHandler.getItemTitle())) {
                            excelItem.setTitle(excelItemHandler.getItemTitle());
                        }
                        excelItem.setOrderNum(excelItemHandler.getItemOrder());
                        excelItem.setExportItemHandler(excelItemHandler);
                        excelItemList.add(excelItem);
                        break;
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(excelExportEntityList)) {
            logger.error("导出excel表格标题数量必须大于0");
            throw new OfficeBusinessException("导出excel表格标题数量必须大于0");
        }
        // 3、构造导出data list
        if (CollectionUtils.isNotEmpty(excelEntityDataList)) {
            for (Object excelEntityData : excelEntityDataList) {
                Map<String, Object> exportData = ESExcelUtils.getExportData(excelEntityData, excelItemList);
                if (MapUtils.isNotEmpty(exportData)) {
                    excelExportEntityDataList.add(exportData);
                }
            }
        }
    }

    public ExcelImportResult<Map<String, Object>> excelDataImport(
            String templateId,
            Class<?> excelEntity,
            ESExcelImportDataHandler excelImportDataHandler,
            ESExcelImportVerifyHandler excelImportVerifyHandler,
            int titleRows,
            int headRows,
            InputStream fileInputStream
    ) {
        List<ESExcelItem> allExcelItemList = ESExcelUtils.getAllEntityExcelItem(excelEntity);
        if (CollectionUtils.isEmpty(allExcelItemList)) {
            logger.error("导入excel对象属性数量必须大于0");
            throw new OfficeBusinessException("导入excel对象属性数量必须大于0");
        }
        // 1、数据导入
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headRows);
        if (ESObjectUtils.isNotNull(excelImportDataHandler)) {
            params.setDataHandler(excelImportDataHandler);
        }
        if (ESObjectUtils.isNotNull(excelImportVerifyHandler)) {
            params.setNeedVerify(true);
            params.setVerifyHandler(excelImportVerifyHandler);
        }
        ExcelImportResult<Map<String, Object>> result;
        try {
            result = ExcelImportUtil.importExcelMore(fileInputStream, Map.class, params);
        } catch (Exception e) {
            logger.error("excel数据导入失败", e);
            throw new OfficeBusinessException("excel数据导入失败");
        }
        // 2、成功导入数据处理
        if (CollectionUtils.isNotEmpty(result.getList())) {
            List<ESExcelItem> excelItemList = new ArrayList<>();
            List<ESExcelItemHandler> excelItemHandlerList = generateExcelItemHandlerList(templateId);
            if (CollectionUtils.isNotEmpty(excelItemHandlerList)) {
                for (ESExcelItemHandler excelItemHandler : excelItemHandlerList) {
                    // 2、构造导入item list
                    for (ESExcelItem excelItem : allExcelItemList) {
                        if (ESStringUtils.equals(excelItem.getName(), excelItemHandler.getItemName())) {
                            excelItem.setImportItemHandler(excelItemHandler);
                            excelItemList.add(excelItem);
                            break;
                        }
                    }
                }
            }
            for (Map<String, Object> map : result.getList()) {
                saveExcelImportEntityData(map, excelItemList);
            }
        }
        return result;
    }

    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        return new ArrayList<>();
    }

    public void saveExcelImportEntityData(Map<String, Object> map, List<ESExcelItem> excelItemList) {

    }
}
