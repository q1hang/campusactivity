package com.campusactivity.common.util;

/**
 * @Author q1hang
 * @Description 常量类
 * @create: 2019-12-12 21:18
 **/
public class Constant {
    public static final Integer TOKEN_INVALID = 401;
    public static final String PRIVILEGELEVEL_ORDINARY="1";
    public static final String PRIVILEGELEVEL_FINANCE="2";
    public static final String PRIVILEGELEVEL_ADMIN="3";

    /**
     * 删除状态：0-正常
     */
    public static final String DELETE_STATUS_NORMAL = "0";
    /**
     * 删除状态：1-已逻辑删除
     */
    public static final String DELETE_STATUS_DELETED = "1";
}
