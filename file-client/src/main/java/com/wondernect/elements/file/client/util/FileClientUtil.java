package com.wondernect.elements.file.client.util;

import com.github.tobato.fastdfs.domain.MetaData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: MateDataUtil
 * Author: chenxun
 * Date: 2018/11/13 10:21
 * Description:
 */
public final class FileClientUtil {

    private static final String EFILE_CLIENT_MATE_DATA_FILE_NAME = "file_name";

    private static final String EFILE_CLIENT_MATE_DATA_FILE_SIZE = "file_size";

    private static final String EFILE_CLIENT_MATE_DATA_FILE_EXT = "file_ext";

    public static Map<String, String> getMetaData(Set<MetaData> metaDataSet) {
        Map<String, String> meta = new HashMap<>();
        if (CollectionUtils.isNotEmpty(metaDataSet)) {
            for (MetaData metaData : metaDataSet) {
                meta.put(metaData.getName(), metaData.getValue());
            }
        }
        return meta;
    }

    public static Set<MetaData> buildMetaData(Map<String, String> metaData) {
        Set<MetaData> mateDataSet = new HashSet<>();
        if (MapUtils.isNotEmpty(metaData)) {
            for (String key : metaData.keySet()) {
                mateDataSet.add(new MetaData(key, metaData.get(key)));
            }
        }
        return mateDataSet;
    }

    public static String getMetaDataValue(String name, Set<MetaData> metaDataSet) {
        String value = null;
        for (MetaData metaData : metaDataSet) {
            if (name.equals(metaData.getName())) {
                value = metaData.getValue();
                break;
            }
        }
        return value;
    }

    public static Set<MetaData> buildDefaultMetaData(String fileName, long fileSize, String fileExt, Map<String, String> metaData) {
        Set<MetaData> metaDataSet = buildMetaData(metaData);
        metaDataSet.add(new MetaData(EFILE_CLIENT_MATE_DATA_FILE_NAME, fileName));
        metaDataSet.add(new MetaData(EFILE_CLIENT_MATE_DATA_FILE_SIZE, String.valueOf(fileSize)));
        metaDataSet.add(new MetaData(EFILE_CLIENT_MATE_DATA_FILE_EXT, fileExt));
        return metaDataSet;
    }

    public String getFileName(Set<MetaData> metaDataSet) {
        return getMetaDataValue(EFILE_CLIENT_MATE_DATA_FILE_NAME, metaDataSet);
    }

    public long getFileSize(Set<MetaData> metaDataSet) {
        String fileSize = getMetaDataValue(EFILE_CLIENT_MATE_DATA_FILE_SIZE, metaDataSet);
        return fileSize == null ? 0 : Long.valueOf(fileSize);
    }

    public String getFileExt(Set<MetaData> metaDataSet) {
        return getMetaDataValue(EFILE_CLIENT_MATE_DATA_FILE_EXT, metaDataSet);
    }
}
