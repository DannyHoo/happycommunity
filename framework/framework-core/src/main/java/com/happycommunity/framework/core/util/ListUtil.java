package com.happycommunity.framework.core.util;

import java.util.List;

/**
 * @author Danny
 * @Title: ListUtil
 * @Description:
 * @Created on 2019-02-25 17:58:00
 */
public class ListUtil {
    public static boolean isEmpty(List list) {
        return list == null?true:list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
