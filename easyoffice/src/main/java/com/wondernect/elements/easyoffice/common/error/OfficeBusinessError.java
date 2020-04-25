package com.wondernect.elements.easyoffice.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: OfficeBusinessError
 * Author: chenxun
 * Date: 2018/5/23 下午3:19
 * Description: 错误码描述
 */
public enum OfficeBusinessError {

    // excel
    EXCEL_FILE_PATH_SHOULD_NOT_NULL("EXCEL_FILE_PATH_SHOULD_NOT_NULL","excel文件路径不能为空"),
    EXCEL_FILE_SHOULD_NOT_NULL("EXCEL_FILE_SHOULD_NOT_NULL","excel文件不能为空"),
    EXCEL_IMPORT_FAILED("EXCEL_IMPORT_FAILED","excel导入失败"),
    EXCEL_EXPORT_FAILED("EXCEL_EXPORT_FAILED", "excel导出失败"),

    // word
    WORD_EXPORT_FAILED("WORD_EXPORT_FAILED", "word导出失败"),

    // pdf
    PDF_EXPORT_FAILED("PDF_EXPORT_FAILED", "PDF导出失败"),

    ;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;

    OfficeBusinessError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OfficeBusinessError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
