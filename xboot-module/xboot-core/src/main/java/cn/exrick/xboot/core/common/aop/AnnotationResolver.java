package cn.exrick.xboot.core.common.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.integration.support.json.JacksonJsonUtils;


/**
 * 该类的作用可以把方法上的参数绑定到注解的变量中,注解的语法#{变量名}
 * 能解析类似#{task}或者#{task.taskName}  目前只能解析两级
 * @author liuxg
 * @date 2016年4月13日 下午8:42:34
 */
public class AnnotationResolver {

	private static AnnotationResolver resolver ;
	
	
	public static AnnotationResolver newInstance(){
		
		if (resolver == null) {
			return resolver = new AnnotationResolver();
		}else{
			return resolver;
		}
		
	}
	
	
	/**
	 * 解析注解上的值
	 * @param joinPoint
	 * @param str 需要解析的字符串
	 * @return
	 */
	public Object resolver(JoinPoint joinPoint, String str) {

		if (str == null) return null ;
		String value = str;
		Pattern p= Pattern.compile("\\#\\{[a-zA-Z0-9_.]+\\}");
		Matcher m=p.matcher(value);
		if (value.contains("#")) {// 如果name匹配上了#{},则把内容当作变量
			ArrayList<String> params = new ArrayList<>();

			while(m.find()){
				params.add(m.group());
			}
			value=value.replaceAll("#\\{", "").replaceAll("\\}", "");
			for (String key : params) {
				Object keyValue=null;
				String newStr = key.replaceAll("#\\{", "").replaceAll("\\}", "");
				if (newStr.contains(".")) { // 复杂类型
					try {
						keyValue = complexResolver(joinPoint, newStr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					keyValue = simpleResolver(joinPoint, newStr);
				}

			if (keyValue==null){
				keyValue="";
			}
				value=value.replace(newStr,keyValue.toString());
			}

		}
		return value;
	}

	
	private Object complexResolver(JoinPoint joinPoint, String str) throws Exception {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String[] names = methodSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		String[] strs = str.split("\\.");

		for (int i = 0; i < names.length; i++) {
			if (strs[0].equals(names[i])) {
				Object obj = args[i];
				Method dmethod = getDeclaredMethod(obj,getMethodName(strs[1]));
				Object value = dmethod.invoke(args[i]);
				return getValue(value, 1, strs);
			}
		}

		return null;

	}



	/**

	 * 循环向上转型, 获取对象的 DeclaredMethod

	 * @param object

	 * @param methodName


	 * @return

	 */

	public static Method getDeclaredMethod(Object object, String methodName){
		for(Class superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
				Method[] declaredMethods = superClass.getDeclaredMethods();
				for (Method declaredMethod : declaredMethods) {
					if (methodName.equals(declaredMethod.getName())){
						return declaredMethod;
					}
				}


		}
		return null;
	}
	private Object getValue(Object obj, int index, String[] strs) {

		try {
			if (obj != null && index < strs.length - 1) {
				Method method = getDeclaredMethod(obj,getMethodName(strs[index + 1]));
				obj = method.invoke(obj);
				getValue(obj, index + 1, strs);
			}

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getMethodName(String name) {
		return "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
	}

	
	private Object simpleResolver(JoinPoint joinPoint, String str) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String[] names = methodSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < names.length; i++) {
			if (str.equals(names[i])) {
				//判断是否为数组
				if (args[i]!=null){
					if(args[i].getClass().isArray()&&args[i] instanceof String[]) {
						return Arrays.toString((String[]) args[i]);
					}
					return args[i];
				}

			}
		}
		return null;
	}

}
