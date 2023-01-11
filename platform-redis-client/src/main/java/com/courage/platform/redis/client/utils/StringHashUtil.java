package com.courage.platform.redis.client.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.CRC32;

/**
 * string hash 工具类 包含笛卡尔积
 * Created by zhangyong on 2020/10/21.
 */
public class StringHashUtil {

    private final static Logger logger = LoggerFactory.getLogger(StringHashUtil.class);

    private final static String CHARSET = "UTF-8";

    public static Integer hashSlot(String value) {
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(value.getBytes(CHARSET));
            long crcValue = crc32.getValue();
            Long hash = null;
            if (crcValue < 0) {
                hash = Math.abs(hash) % 1024;
            } else {
                hash = crcValue % 1024;
            }
            return hash.intValue();
        } catch (Exception e) {
            logger.error("hashSlot value:" + value, e);
            return null;
        }
    }

    public static List<String> descartes(List<List<String>> collections) {
        if (collections == null) {
            return Collections.emptyList();
        }
        //单维列表
        if (collections.size() == 1) {
            return collections.get(0);
        }
        //两组列表
        if (collections.size() == 2) {
            return descartes2(collections.get(0), collections.get(1));
        }
        //多维
        if (collections.size() > 2) {
            return descartes3(collections);
        }
        return null;
    }

    public static List<String> descartes2(List<String> first, List<String> second) {
        List<String> arr = new ArrayList<>(first.size() * second.size());
        for (int i = 0; i < first.size(); i++) {
            String firstItem = first.get(i);
            for (int j = 0; j < second.size(); j++) {
                String secondItem = second.get(j);
                if (StringUtils.isNotBlank(firstItem) && StringUtils.isNotBlank(secondItem)) {
                    arr.add(firstItem + secondItem);
                }
            }
        }
        return arr;
    }

    public static List<String> descartes3(List<List<String>> collections) {
        int length = collections.size();
        List<String> list1 = collections.get(0);
        List<String> list2 = collections.get(1);
        //先触发前两条记录
        List<String> trigger = descartes2(list1, list2);
        for (int i = 2; i < length; i++) {
            List<String> temp = collections.get(i);
            trigger = descartes2(trigger, temp);
        }
        return trigger;
    }

    public static int index(int slot, int shardingCount) {
        int segment = 1024 / shardingCount;
        int index = slot / segment;
        return index;
    }

    /**
     * 获取真实表名
     *
     * @param tableName
     * @param value
     * @return
     */
    public static String getTrueTableName(String tableName, String value) {
        if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(value)) {
            return tableName + "_" + StringHashUtil.index(StringHashUtil.hashSlot(value), 16);
        }
        return "";
    }
}