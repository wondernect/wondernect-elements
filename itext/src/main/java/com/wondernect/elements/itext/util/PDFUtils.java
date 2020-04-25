package com.wondernect.elements.itext.util;

import com.itextpdf.text.pdf.BaseFont;
import com.wondernect.elements.common.utils.ESFileUtils;
import com.wondernect.elements.common.utils.ESSystemUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: PDFUtils
 * Author: chenxun
 * Date: 2019/1/26 15:37
 * Description:
 */
public final class PDFUtils {

    public static void createPDF(String htmlContent, String defaultFontPath, List<String> fontPaths, OutputStream outputStream) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(htmlContent.getBytes("UTF-8")));
        ITextRenderer iTextRenderer = new ITextRenderer();

        ITextFontResolver iTextFontResolver = iTextRenderer.getFontResolver();
        // 加载默认内置字体
        String fontPath = ESSystemUtils.getUserDir() + File.separator + defaultFontPath;
        // URL url = Thread.currentThread().getContextClassLoader().getResource("fonts");
        // if (ESObjectUtils.isNotNull(url)) {
        //     fontPath = url.getPath();
        // }
        if (ESFileUtils.exist(fontPath)) {
            iTextFontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        // 加载配置字体
        if (CollectionUtils.isNotEmpty(fontPaths)) {
            for (String path : fontPaths) {
                String font = ESSystemUtils.getUserDir() + File.separator + path;
                if (ESFileUtils.exist(font)) {
                    iTextFontResolver.addFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                }
            }
        }

        iTextRenderer.setDocument(document, null);
        iTextRenderer.layout();
        iTextRenderer.createPDF(outputStream);
    }
}
