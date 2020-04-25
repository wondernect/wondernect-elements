package com.wondernect.elements.itext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ITextPDF
 * Author: chenxun
 * Date: 2019/1/26 15:31
 * Description: xxx转PDF
 */
public interface ITextPDF {

    /**
     * 预览
     */
    void preview(String htmlContent, HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载
     */
    void download(String htmlContent, String fileName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 生成文件保存在本地
     */
    String save(String htmlContent, String filePath, String fileName);
}
