package com.zerods.mall.common.utils;

import java.util.Random;

/**
 * @author zerods
 * @version 1.0 06/09/2017
 */
public class IDUtils {

    /**
     * 根据当前时间和随机数生成商品id
     * @return
     */
    public static long getItemId() {
        long mills = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(100);
        String str = mills + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }
}
