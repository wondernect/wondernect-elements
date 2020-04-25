package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESZIPUtils
 * Author: chenxun
 * Date: 2019/3/23 16:52
 * Description:
 */
public final class ESZIPUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESZIPUtils.class);

    private static final int BUFFER = 1024;

    /**
     * 取的给定源目录下的所有文件及空的子目录
     * 递归实现
     * @param srcFile 源文件||源文件目录
     * @return 所有文件或文件目录
     */
    private static List<File> getAllFiles(File srcFile) {
        List<File> fileList = new ArrayList<>();
        File[]     files    = srcFile.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                    // System.out.println("add file: " + tmp[i].getName());
                }
                if (file.isDirectory()) {
                    File[] subFiles = file.listFiles();
                    if (subFiles != null && subFiles.length > 0) {
                        //若不是空目录，则递归添加其下的目录和文件
                        fileList.addAll(getAllFiles(file));
                    } else {
                        //若是空目录，则添加这个目录到fileList
                        fileList.add(file);
                        // System.out.println("add empty dir: " + tmp[i].getName());
                    }
                }
            }
        }
        return fileList;
    }

    /**
     * 取相对路径
     * 依据文件名和压缩源路径得到文件在压缩源路径下的相对路径
     * @param dirPath 压缩源路径
     * @param file 压缩文件
     * @return 相对路径
     */
    private static String getRelativePath(String dirPath, File file) {
        File   dir          = new File(dirPath);
        String relativePath = file.getName();
        while (true) {
            file = file.getParentFile();
            if (file == null) {
                break;
            }
            if (file.equals(dir)) {
                break;
            } else {
                relativePath = file.getName() + "/" + relativePath;
            }
        }
        return relativePath;
    }

    /**
     * 创建文件
     * 根据压缩包内文件名和解压缩目的路径，创建解压缩目标文件，
     * 生成中间目录
     * @param dstPath 解压缩目的路径
     * @param fileName 压缩包内文件名
     * @return 解压缩目标文件
     * @throws IOException IO异常
     */
    private static File createFile(String dstPath, String fileName) throws IOException {
        String[] dirs = fileName.split("/"); //将文件名的各级目录分解
        File     file = new File(dstPath);
        if (dirs.length > 1) {
            //文件有上级目录
            for (int i = 0; i < dirs.length - 1; i++) {
                //依次创建文件对象知道文件的上一级目录
                file = new File(file, dirs[i]);
            }
            if (!file.exists()) {
                //文件对应目录若不存在，则创建
                file.mkdirs();
                // System.out.println("mkdirs: " + file.getCanonicalPath());
            }
            //创建文件
            file = new File(file, dirs[dirs.length - 1]);
            return file;
        } else {
            if (!file.exists()) {
                //若目标路径的目录不存在，则创建
                file.mkdirs();
                // System.out.println("mkdirs: " + file.getCanonicalPath());
            }
            //创建文件
            file = new File(file, dirs[0]);
            return file;
        }
    }

    /**
     * 解压缩方法
     * @param zipFileName 压缩文件名
     * @param dstPath 解压目标路径
     * @return 成功|失败
     */
    public static boolean unzip(String zipFileName, String dstPath) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry       zipEntry       = null;
            byte[]         buffer         = new byte[BUFFER]; //缓冲器
            int            readLength     = 0; //每次读出来的长度
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {
                    //若是zip条目目录，则需创建这个目录
                    File dir = new File(dstPath + "/" + zipEntry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                        continue;//跳出
                    }
                }
                //若是文件，则需创建该文件
                File file = createFile(dstPath, zipEntry.getName());
                OutputStream outputStream = new FileOutputStream(file);
                while ((readLength = zipInputStream.read(buffer, 0, BUFFER)) != -1) {
                    outputStream.write(buffer, 0, readLength);
                }
                outputStream.close();
            }
        } catch (Exception e) {
            logger.error("unzip fail, " + zipFileName + " => " + dstPath, e);
            return false;
        }
        return true;
    }

    /**
     * 压缩方法
     * （可以压缩空的子目录）
     * @param srcPath 压缩源路径
     * @param zipFileName 目标压缩文件
     * @return 成功\失败
     */
    public static boolean zip(String srcPath, String zipFileName) {
        File       srcFile    = new File(srcPath);
        List<File> fileList   = getAllFiles(srcFile); //所有要压缩的文件
        byte[]     buffer     = new byte[BUFFER]; //缓冲器
        ZipEntry   zipEntry   = null;
        int        readLength = 0; //每次读出来的长度
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            for (File file : fileList) {
                if (file.isFile()){
                    //若是文件，则压缩这个文件
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file));
                    zipEntry.setSize(file.length());
                    zipEntry.setTime(file.lastModified());
                    zipOutputStream.putNextEntry(zipEntry);
                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    while ((readLength = inputStream.read(buffer, 0, BUFFER)) != -1) {
                        zipOutputStream.write(buffer, 0, readLength);
                    }
                    inputStream.close();
                    // System.out.println("file compressed: " + file.getCanonicalPath());
                }else {
                    //若是目录（即空目录）则将这个目录写入zip条目
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file) + "/");
                    zipOutputStream.putNextEntry(zipEntry);
                    // System.out.println("dir compressed: " + file.getCanonicalPath()+"/");
                }
            }
            zipOutputStream.close();
        } catch (Exception e) {
            logger.error("zip fail, " + srcPath + " => " + zipFileName, e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String targetFile = "F:\\train_person\\1.zip";
        String path = "F:\\train_person\\54558654231347200\\";
        String destPath = "F:\\train_person\\1\\";
        zip(path, targetFile);
        unzip(targetFile, destPath);
    }
}
