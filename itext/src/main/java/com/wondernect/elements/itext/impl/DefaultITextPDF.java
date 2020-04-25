package com.wondernect.elements.itext.impl;

import com.wondernect.elements.itext.ITextPDF;
import com.wondernect.elements.itext.config.ITextConfigProperties;
import com.wondernect.elements.itext.util.PDFUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultITextPDF
 * Author: chenxun
 * Date: 2019/1/26 15:58
 * Description:
 */
@Service(value = "itext_pdf")
public class DefaultITextPDF implements ITextPDF {

    private static final Logger logger = LoggerFactory.getLogger(DefaultITextPDF.class);

    @Autowired
    private ITextConfigProperties iTextConfigProperties;

    @Override
    public void preview(String htmlContent, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/pdf");
        try {
            PDFUtils.createPDF(htmlContent, iTextConfigProperties.getDefaultFontPath(), iTextConfigProperties.getFontPaths(), response.getOutputStream());
            response.getOutputStream().close();
        } catch (Exception e) {
            logger.error("ITextPDF create PDF failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
        }
    }

    @Override
    public void download(String htmlContent, String fileName, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/pdf");
        fileName += ".pdf";
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            logger.error("ITextPDF create PDF error, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
        }
        try {
            PDFUtils.createPDF(htmlContent, iTextConfigProperties.getDefaultFontPath(), iTextConfigProperties.getFontPaths(), response.getOutputStream());
            response.getOutputStream().close();
        } catch (Exception e) {
            logger.error("ITextPDF create PDF failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
        }
    }

    @Override
    public String save(String htmlContent, String filePath, String fileName) {
        filePath = filePath + File.separator + fileName + ".pdf";
        try {
            PDFUtils.createPDF(htmlContent, iTextConfigProperties.getDefaultFontPath(), iTextConfigProperties.getFontPaths(), new FileOutputStream(new File(filePath)));
        } catch (Exception e) {
            logger.error("ITextPDF save PDF failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
            filePath = null;
        }
        return filePath;
    }
}
