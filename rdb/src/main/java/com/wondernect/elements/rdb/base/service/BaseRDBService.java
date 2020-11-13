package com.wondernect.elements.rdb.base.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESClassUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.excel.*;
import com.wondernect.elements.rdb.base.manager.BaseRDBManager;
import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.BaseRDBResponseDTO;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseRDBService
 * Author: chenxun
 * Date: 2020-06-26 07:55
 * Description:
 */
public abstract class BaseRDBService<RES_DTO extends BaseRDBResponseDTO, T extends BaseRDBModel, ID extends Serializable> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRDBService.class);

    @Autowired
    private BaseRDBManager<T, ID> baseRDBManager;

    public JPAQueryFactory getJpaQueryFactory() {
        return baseRDBManager.getJpaQueryFactory();
    }

    public T saveEntity(T entity) {
        return baseRDBManager.save(entity);
    }

    public RES_DTO save(T entity) {
        T entitySave = baseRDBManager.save(entity);
        return generate(entitySave);
    }

    public List<T> saveAllEntity(List<T> entityList) {
        return baseRDBManager.saveAll(entityList);
    }

    public List<RES_DTO> saveAll(List<T> entityList) {
        List<T> list = baseRDBManager.saveAll(entityList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public void deleteById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNotNull(entity)) {
            baseRDBManager.deleteById(entityId);
        }
    }

    public void deleteAll() {
        baseRDBManager.deleteAll();
    }

    public T findEntityById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return entity;
    }

    public RES_DTO findById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return generate(entity);
    }

    public boolean existById(ID entityId) {
        return baseRDBManager.existById(entityId);
    }

    public long count() {
        return baseRDBManager.count();
    }

    public long count(Criteria<T> criteria) {
        return baseRDBManager.count(criteria);
    }

    public List<T> findAllEntity(List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(sortDataList);
        if (ESObjectUtils.isNull(list)) {
            list = new ArrayList<>();
        }
        return list;
    }

    public List<T> findAllEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(criteria, sortDataList);
        if (ESObjectUtils.isNull(list)) {
            list = new ArrayList<>();
        }
        return list;
    }

    public T findOneEntity(List<SortData> sortDataList) {
        return baseRDBManager.findOne(sortDataList);
    }

    public T findOneEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBManager.findOne(criteria, sortDataList);
    }

    public List<RES_DTO> findAll(List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public List<RES_DTO> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(criteria, sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public RES_DTO findOne(List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        if (CollectionUtils.isEmpty(resDtoList)) {
            return null;
        }
        return resDtoList.get(0);
    }

    public RES_DTO findOne(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(criteria, sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        if (CollectionUtils.isEmpty(resDtoList)) {
            return null;
        }
        return resDtoList.get(0);
    }

    public PageResponseData<T> findAllEntity(PageRequestData pageRequestData) {
        return baseRDBManager.findAll(pageRequestData);
    }

    public PageResponseData<T> findAllEntity(Criteria<T> criteria, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(criteria, pageRequestData);
    }

    public PageResponseData<RES_DTO> findAll(PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public PageResponseData<RES_DTO> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(criteria, pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public <S> long count(JPAQuery<S> jpaQuery) {
        return baseRDBManager.count();
    }

    public <S> List<S> findAllEntity(JPAQuery<S> jpaQuery) {
        List<S> list = baseRDBManager.findAll(jpaQuery);
        if (ESObjectUtils.isNull(list)) {
            list = new ArrayList<>();
        }
        return list;
    }

    public List<RES_DTO> findAll(JPAQuery<T> jpaQuery) {
        List<T> list = baseRDBManager.findAll(jpaQuery);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public <S> S findOneEntity(JPAQuery<S> jpaQuery) {
        return baseRDBManager.findOne(jpaQuery);
    }

    public <S> PageResponseData<S> findAllEntity(JPAQuery<S> jpaQuery, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(jpaQuery, pageRequestData);
    }

    public PageResponseData<RES_DTO> findAll(JPAQuery<T> jpaQuery, PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(jpaQuery, pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public List<ESExcelItem> excelItemList(Class<?> cls) {
        return ESExcelUtils.getAllEntityExcelItem(cls);
    }

    public void excelDataExport(String exportServiceIdentifier, Class<RES_DTO> valueType, List<RES_DTO> resDtoList, String title, String sheetName, String fileName, HttpServletRequest request, HttpServletResponse response) {
        List<ESExcelItem> allExcelItemList = ESExcelUtils.getAllEntityExcelItem(valueType);
        if (CollectionUtils.isEmpty(allExcelItemList)) {
            logger.error("导出excel对象属性数量必须大于0");
            throw new BusinessException("导出excel对象属性数量必须大于0");
        }
        List<ExcelExportEntity> excelExportEntityList = new ArrayList<>();
        List<ESExcelItem> excelItemList = new ArrayList<>();
        List<ESExcelItemHandler> excelItemHandlerList = generateExcelExportItemHandlerList(exportServiceIdentifier);
        if (CollectionUtils.isNotEmpty(excelItemHandlerList)) {
            for (ESExcelItemHandler excelItemHandler : excelItemHandlerList) {
                // 1、构造导出entity list
                ExcelExportEntity excelExportEntity = new ExcelExportEntity(
                        ESStringUtils.isNotBlank(excelItemHandler.itemTitle()) ? excelItemHandler.itemTitle() : excelItemHandler.itemName(),
                        excelItemHandler.itemName()
                );
                excelExportEntity.setOrderNum(excelItemHandler.itemOrder());
                excelExportEntityList.add(excelExportEntity);
                // 2、构造导出item list
                for (ESExcelItem excelItem : allExcelItemList) {
                    if (ESStringUtils.equals(excelItem.getName(), excelItemHandler.itemName())) {
                        if (ESStringUtils.isNotBlank(excelItemHandler.itemTitle())) {
                            excelItem.setTitle(excelItemHandler.itemTitle());
                        }
                        excelItem.setOrderNum(excelItemHandler.itemOrder());
                        excelItem.setExportItemHandler(excelItemHandler);
                        excelItemList.add(excelItem);
                        break;
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(excelExportEntityList)) {
            logger.error("导出excel表格标题数量必须大于0");
            throw new BusinessException("导出excel表格标题数量必须大于0");
        }
        // 3、构造导出data list
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resDtoList)) {
            for (Object object : resDtoList) {
                Map<String, Object> exportData = ESExcelUtils.getExportData(object, excelItemList);
                if (MapUtils.isNotEmpty(exportData)) {
                    dataList.add(exportData);
                }
            }
        }
        // 4、导出执行
        EasyExcel.exportExcel(excelExportEntityList, dataList, title, sheetName, fileName, request, response);
    }

    public void excelDataImport(String importServiceIdentifier, Class<RES_DTO> valueType, int titleRows, int headRows, InputStream fileInputStream, String failedfileName, HttpServletRequest request, HttpServletResponse response) {
        List<ESExcelItem> allExcelItemList = ESExcelUtils.getAllEntityExcelItem(valueType);
        if (CollectionUtils.isEmpty(allExcelItemList)) {
            logger.error("导入excel对象属性数量必须大于0");
            throw new BusinessException("导入excel对象属性数量必须大于0");
        }
        // 1、数据导入
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headRows);
        ESExcelImportDataHandler excelImportDataHandler = generateExcelImportDataHandler(importServiceIdentifier);
        if (ESObjectUtils.isNotNull(excelImportDataHandler)) {
            params.setDataHandler(excelImportDataHandler);
        }
        ESExcelImportVerifyHandler excelImportVerifyHandler = generateExcelImportVerifyHandler(importServiceIdentifier);
        if (ESObjectUtils.isNotNull(excelImportVerifyHandler)) {
            params.setNeedVerify(true);
            params.setVerifyHandler(excelImportVerifyHandler);
        }
        ExcelImportResult<Map<String, Object>> result;
        try {
            result = ExcelImportUtil.importExcelMore(fileInputStream, Map.class, params);
        } catch (Exception e) {
            logger.error("excel数据导入失败", e);
            throw new BusinessException("excel数据导入失败");
        }
        // 2、成功导入数据处理
        if (CollectionUtils.isNotEmpty(result.getList())) {
            List<ESExcelItem> excelItemList = new ArrayList<>();
            List<ESExcelItemHandler> excelItemHandlerList = generateExcelImportItemHandlerList(importServiceIdentifier);
            if (CollectionUtils.isNotEmpty(excelItemHandlerList)) {
                for (ESExcelItemHandler excelItemHandler : excelItemHandlerList) {
                    // 2、构造导入item list
                    for (ESExcelItem excelItem : allExcelItemList) {
                        if (ESStringUtils.equals(excelItem.getName(), excelItemHandler.itemName())) {
                            excelItem.setImportItemHandler(excelItemHandler);
                            excelItemList.add(excelItem);
                            break;
                        }
                    }
                }
            }
            for (Map<String, Object> map : result.getList()) {
                RES_DTO responseDTO = ESExcelUtils.getImportObject(valueType, map, excelItemList);
                T entity = generate(responseDTO);
                if (ESObjectUtils.isNotNull(entity)) {
                    baseRDBManager.save(entity);
                }
            }
        }
        // 响应错误数据
        if (ESObjectUtils.isNotNull(result) && result.isVerfiyFail() && CollectionUtils.isNotEmpty(result.getFailList())) {
            EasyExcel.exportExcel(result.getFailWorkbook(), failedfileName, request, response);
        }
    }

    public RES_DTO generate(T entity) {
        return null;
    }

    public T generate(RES_DTO responseDTO) {
        return null;
    }

    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return new ArrayList<>();
    }

    public List<ESExcelItemHandler> generateExcelImportItemHandlerList(String importServiceIdentifier) {
        return new ArrayList<>();
    }

    public ESExcelImportDataHandler generateExcelImportDataHandler(String importServiceIdentifier) {
        return null;
    }

    public ESExcelImportVerifyHandler generateExcelImportVerifyHandler(String importServiceIdentifier) {
        return null;
    }
}
