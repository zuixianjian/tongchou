package cn.exrick.xboot.core.common.annotation;

import cn.exrick.xboot.core.common.enums.LogType;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 * @author Exrickx
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

        /**
         * 日志名称
         * @return
         */
        String description() default "";

        /**
         * 日志类型
         * @return
         */
        LogType type() default LogType.OPERATION;

        /**
         * 数据标识 便于检索
         * @return
         */
        String dataFlag() default "";

        /**
         * 日志描述-----便于记录(主要用来标记操作结果)
         * @return
         */
        String  desc() default "";

        /**
         * 特别说明
         * @return
         */
        String  specialDesc() default "";
}
