package com.mcmd.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只有写库操作，才记录异常，查询异常不抓取
 *
 * @author ppliu
 * @version 1.0
 * @date 2022/3/23 10:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionRecordAop {
    /**
     * 数据库操作类型 Insert、Update、Delete
     * 项目模块
     *
     * @return
     */
    String type() default "Insert";

}
