package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESFileUtils
 * Author: chenxun
 * Date: 2019/3/22 14:18
 * Description: file utils
 */
public final class ESFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESFileUtils.class);

    /**
     * 检查文件或文件夹是否存在
     */
    public static Boolean exist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 创建文件夹
     */
    public static Boolean createFilePath(String filePath) {
        File file = new File(filePath);
        Boolean createSuccess = true;
        try {
            if (!file.exists()) {
                createSuccess = file.mkdirs();
            }
        } catch (RuntimeException e) {
            logger.error(e.getLocalizedMessage(), e);
            createSuccess = false;
        }
        return createSuccess;
    }

    /**
     * 创建文件
     */
    public static Boolean createFile(String filePath, String fileContent) {
        File file = new File(filePath);
        Boolean createSuccess = true;
        try {
            if (!file.exists()) {
                createSuccess = file.createNewFile();
            }
            if (createSuccess) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                PrintStream printStream = new PrintStream(fileOutputStream, true, "UTF-8");
                printStream.println(fileContent);
                printStream.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            createSuccess = false;
        }
        return createSuccess;
    }

    /**
     * 创建文件
     */
    public static Boolean createFile(String filePath, InputStream inputStream) {
        File file = new File(filePath);
        Boolean createSuccess = true;
        try {
            if (!file.exists()) {
                createSuccess = file.createNewFile();
            }
            if (createSuccess) {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                //创建一个缓冲区
                byte buffer[] = new byte[1024];
                //判断输入流中的数据是否已经读完的标识
                int length;
                //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                while((length = inputStream.read(buffer)) > 0){
                    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                    fileOutputStream.write(buffer, 0, length);
                }
                //关闭输入流
                inputStream.close();
                //关闭输出流
                fileOutputStream.close();
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            createSuccess = false;
        }
        return createSuccess;
    }

    /**
     * 下载文件
     */
    public static void downloadFile(String filePath, OutputStream outputStream) {
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int length;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while((length = fileInputStream.read(buffer)) > 0){
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            fileInputStream.close();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 删除文件&文件夹
     */
    public static Boolean delete(String filePath) {
        File file = new File(filePath);
        Boolean deleteSuccess;
        try {
            deleteSuccess = file.delete();
        } catch (RuntimeException e) {
            logger.error(e.getLocalizedMessage(), e);
            deleteSuccess = false;
        }
        return deleteSuccess;
    }
}
