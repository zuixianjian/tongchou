package cn.exrick.xboot.your.utils.security;

import java.lang.annotation.*;


/**
 * 自定义注解
 * 加解密
 * @author wyx
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecretAnnotation {
	
	/**
	 * 是否加密
	 * 默认false
	 * 加密时传值为true
	 * @return
	 */
	
    boolean encode() default false;
    /**
     * 是否解密
     * 默认为false，
     * 解密时传值为true
     * @return
     */
    boolean decode() default false;
}
