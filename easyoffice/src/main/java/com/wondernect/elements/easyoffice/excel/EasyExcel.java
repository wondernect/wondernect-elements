package com.wondernect.elements.easyoffice.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.wondernect.elements.easyoffice.common.error.OfficeBusinessError;
import com.wondernect.elements.easyoffice.common.exception.OfficeBusinessException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EasyExcel
 * Author: chenxun
 * Date: 2018/6/22 10:42
 * Description: excel工具类
 */
public final class EasyExcel {

    private static final Logger logger = LoggerFactory.getLogger(EasyExcel.class);

    /**
     * excel导入
     * @param filePath 文件路径
     * @param titleRows 表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass 自定义excle pojo
     * @param <T> 自定义excle pojo
     * @return 自定义excle pojo list
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return importExcel(filePath, params, pojoClass);
    }

    /**
     * excel导入
     * @param filePath 文件路径
     * @param titleRows 表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass 自定义excle pojo
     * @param <T> 自定义excle pojo
     * @return 导入返回类
     */
    public static <T> ExcelImportResult<T> importExcelMore(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return importExcelMore(filePath, params, pojoClass);
    }

    /**
     * excel导入
     * @param filePath 文件路径
     * @param params 导入参数
     * @param pojoClass 自定义excel pojo
     * @param <T> 自定义excel pojo
     * @return 自定义excle pojo list
     */
    public static <T> List<T> importExcel(String filePath, ImportParams params, Class<T> pojoClass) {
        List<T> list;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_FILE_PATH_SHOULD_NOT_NULL);
        } catch (Exception e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_IMPORT_FAILED);
        }
        return list;
    }

    /**
     * Excel导入
     * @param filePath 文件路径
     * @param params 导入参数
     * @param pojoClass 自定义excel pojo
     * @return 导入返回类
     */
    public static <T> ExcelImportResult<T> importExcelMore(String filePath, ImportParams params, Class<?> pojoClass) {
        ExcelImportResult<T> excelImportResult;
        try {
            excelImportResult = ExcelImportUtil.importExcelMore(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_FILE_PATH_SHOULD_NOT_NULL);
        } catch (Exception e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_IMPORT_FAILED);
        }
        return excelImportResult;
    }

    /**
     * excel导入
     * @param file 文件流
     * @param titleRows 表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass 自定义excle pojo
     * @param <T> 自定义excle pojo
     * @return 自定义excle pojo list
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return importExcel(file, params, pojoClass);
    }

    /**
     * excel导入
     * @param file 文件流
     * @param titleRows 表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass 自定义excle pojo
     * @param <T> 自定义excle pojo
     * @return 导入返回类
     */
    public static <T> ExcelImportResult<T> importExcelMore(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return importExcelMore(file, params, pojoClass);
    }

    /**
     * excel导入
     * @param file 文件流
     * @param params 导入参数
     * @param pojoClass 自定义excle pojo
     * @param <T> 自定义excle pojo
     * @return 自定义excle pojo list
     */
    public static <T> List<T> importExcel(MultipartFile file, ImportParams params, Class<T> pojoClass) {
        List<T> list;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_FILE_SHOULD_NOT_NULL);
        } catch (Exception e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_IMPORT_FAILED);
        }
        return list;
    }

    /**
     * Excel导入
     * @param file 文件流
     * @param params 导入参数
     * @param pojoClass 自定义excle pojo
     * @return 导入返回类
     */
    public static <T> ExcelImportResult<T> importExcelMore(MultipartFile file, ImportParams params, Class<?> pojoClass) {
        ExcelImportResult<T> excelImportResult;
        try {
            excelImportResult = ExcelImportUtil.importExcelMore(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_FILE_SHOULD_NOT_NULL);
        } catch (Exception e) {
            logger.error("Excel import failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_IMPORT_FAILED);
        }
        return excelImportResult;
    }

    /**
     * 1、pojo excel导出(请求响应)
     * @param dataSet 数据集合
     * @param title 表格名称
     * @param sheetName sheetName
     * @param pojoClass 自定义excle pojo
     * @param fileName 导出文件名称
     * @param request 请求
     * @param response 响应
     */
    public static void exportExcel(Collection<?> dataSet, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletRequest request, HttpServletResponse response) {
        exportExcel(dataSet, new ExportParams(title, sheetName), pojoClass, fileName, request, response);
    }

    /**
     * 1、pojo excel导出(输出到文件)
     * @param dataSet 数据集合
     * @param title 表格名称
     * @param sheetName sheetName
     * @param pojoClass 自定义excle pojo
     * @param filePath 导出文件路径
     * @param fileName 导出文件名称
     */
    public static String exportExcel(Collection<?> dataSet, String title, String sheetName, Class<?> pojoClass, String filePath, String fileName) {
        return exportExcel(dataSet, new ExportParams(title, sheetName), pojoClass, filePath, fileName);
    }

    /**
     * 1、pojo excel导出(请求响应)
     * @param dataSet 数据集合
     * @param exportParams 表格相关参数
     * @param pojoClass 自定义excel pojo
     * @param fileName 导出文件名称
     * @param request 请求
     * @param response 响应
     */
    public static void exportExcel(Collection<?> dataSet, ExportParams exportParams, Class<?> pojoClass, String fileName, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, dataSet);
        exportExcel(workbook, fileName, request, response);
    }

    /**
     * 1、pojo excel导出(输出到文件)
     * @param dataSet 数据集合
     * @param exportParams 表格相关参数
     * @param pojoClass 自定义excel pojo
     * @param filePath 导出文件路径
     * @param fileName 导出文件名称
     */
    public static String exportExcel(Collection<?> dataSet, ExportParams exportParams, Class<?> pojoClass, String filePath, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, dataSet);
        return exportExcel(workbook, filePath, fileName);
    }

    /**
     * 2、自定义title&data excel导出(请求响应)
     * @param entityList 自定义excel header
     * @param dataSet 数据集合
     * @param title 表格名称
     * @param sheetName sheetName
     * @param fileName 导出文件名称
     * @param request 请求
     * @param response 响应
     */
    public static void exportExcel(List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet, String title, String sheetName, String fileName, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), entityList, dataSet);
        exportExcel(workbook, fileName, request, response);
    }

    /**
     * 2、自定义title&data excel导出(输出到文件)
     * @param entityList 自定义excel header
     * @param dataSet 数据集合
     * @param title 表格名称
     * @param sheetName sheetName
     * @param filePath 导出文件路径
     * @param fileName 导出文件名称
     */
    public static String exportExcel(List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet, String title, String sheetName, String filePath, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), entityList, dataSet);
        return exportExcel(workbook, filePath, fileName);
    }

    /**
     * 2、自定义title&data excel导出(请求响应)
     * @param entityList 自定义excel header
     * @param dataSet 数据集合
     * @param exportParams 表格相关参数
     * @param fileName 导出文件名称
     * @param request 请求
     * @param response 响应
     */
    public static void exportExcel(List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet, ExportParams exportParams, String fileName, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entityList, dataSet);
        exportExcel(workbook, fileName, request, response);
    }

    /**
     * 2、自定义title&data excel导出(输出到文件)
     * @param entityList 自定义excel header
     * @param dataSet 数据集合
     * @param exportParams 表格相关参数
     * @param filePath 导出文件路径
     * @param fileName 导出文件名称
     */
    public static void exportExcel(List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet, ExportParams exportParams, String filePath, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entityList, dataSet);
        exportExcel(workbook, filePath, fileName);
    }

    /**
     * 3、模板导出(请求响应)
     */
    public static void exportExcel(TemplateExportParams params, Map<String, Object> map, String fileName, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        exportExcel(workbook, fileName, request, response);
    }

    /**
     * 3、模板导出(输出到文件)
     */
    public static String exportExcel(TemplateExportParams params, Map<String, Object> map, String filePath, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        return exportExcel(workbook, filePath, fileName);
    }

    /**
     * 导出文件(请求响应)
     */
    public static void exportExcel(Workbook workbook, String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (workbook instanceof HSSFWorkbook) {
            fileName = fileName + ".xls";
        } else {
            fileName = fileName + ".xlsx";
        }
        try {
            if (isIE(request)) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("content-type", "application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            logger.error("Excel export failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_EXPORT_FAILED);
        }
    }

    /**
     * 导出文件(输出到文件)
     */
    public static String exportExcel(Workbook workbook, String filePath, String fileName) {
        if (workbook instanceof HSSFWorkbook) {
            fileName = fileName + ".xls";
        } else {
            fileName = fileName + ".xlsx";
        }
        try {
            File savefile = new File(filePath);
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            String file = filePath + File.separator + fileName;
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            logger.error("Excel export failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getCause());
            throw new OfficeBusinessException(OfficeBusinessError.EXCEL_EXPORT_FAILED);
        }
        return fileName;
    }

    private static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("edge") > 0;
    }
}
