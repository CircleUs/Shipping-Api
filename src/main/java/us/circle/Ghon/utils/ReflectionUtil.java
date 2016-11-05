package us.circle.Ghon.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import org.apache.ibatis.javassist.ClassPool;
//import org.apache.ibatis.javassist.CtClass;
//import org.apache.ibatis.javassist.CtMethod;
//import org.apache.ibatis.javassist.Modifier;
//import org.apache.ibatis.javassist.NotFoundException;
//import org.apache.ibatis.javassist.bytecode.CodeAttribute;
//import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
//import org.apache.ibatis.javassist.bytecode.MethodInfo;


public class ReflectionUtil {
	public static String convertGet(String fieldName){
		String head = fieldName.substring(0,1);
		String other = fieldName.substring(1);
		return new StringBuilder("get").append(head.toUpperCase()).append(other).toString();
	}
	
	public static String convertSet(String fieldName){
		String head = fieldName.substring(0,1);
		String other = fieldName.substring(1);
		return new StringBuilder("set").append(head.toUpperCase()).append(other).toString();
	}
	
	public static final Class<?>[] classes = new Class[0];
	
	public static Class<?>[] handleType(Type type){
		if(null == type){
			return null;
		}
		String a = type.toString();
		if(a.indexOf("<") == -1 || a.lastIndexOf(">") == -1){
			return null;
		}
		a = a.substring(a.indexOf("<") + 1, a.lastIndexOf(">"));
		while(a.indexOf("<") != -1){
			a = a.replaceAll("<[^<>]*>", "");
		}
		String[] b = a.split(",");
		List<Class<?>> list = new ArrayList<Class<?>>();
		for(int i = 0; i < b.length; i++){
			try {
				String className = b[i].trim();
				if("java.util.List".equals(className)){
					className = "java.util.ArrayList";
				}else if("java.util.Map".equals(className)){
					className = "java.util.HashMap";
				}
				list.add(Class.forName(className));
			} catch (ClassNotFoundException e) {
			}
		}
		return list.toArray(classes);
	}
	
	/**
     * 
     * <p>
     * 获取方法参数名称
     * </p>
     * 
     * @param cm
     * @return
     */
//    private static String[] getMethodParamNames(CtMethod cm) throws Exception{
//        CtClass cc = cm.getDeclaringClass();
//        MethodInfo methodInfo = cm.getMethodInfo();
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//                .getAttribute(LocalVariableAttribute.tag);
//        if (attr == null) {
//            throw new BaseException("ReflectionUtil_sdfa5", cc.getName());
//        }
// 
//        String[] paramNames = null;
//        paramNames = new String[cm.getParameterTypes().length];
//       
//        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//        for (int i = 0; i < paramNames.length; i++) {
//            paramNames[i] = attr.variableName(i + pos);
//        }
//        return paramNames;
//    }
 
    /**
     * 获取方法参数名称，按给定的参数类型匹配方法
     * 
     * @param clazz
     * @param method
     * @param paramTypes
     * @return
     */
//    public static String[] getMethodParamNames(Class<?> clazz, String method,
//            Class<?>... paramTypes) throws Exception{
// 
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = null;
//        CtMethod cm = null;
//        cc = pool.get(clazz.getName());
//        String[] paramTypeNames = new String[paramTypes.length];
//        for (int i = 0; i < paramTypes.length; i++)
//        	paramTypeNames[i] = paramTypes[i].getName();
//        cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
//        return getMethodParamNames(cm);
//    }
 
    /**
     * 获取方法参数名称，匹配同名的某一个方法
     * 
     * @param clazz
     * @param method
     * @return
     * @throws NotFoundException
     *             如果类或者方法不存在
     * @throws MissingLVException
     *             如果最终编译的class文件不包含局部变量表信息
     */
//    public static String[] getMethodParamNames(Class<?> clazz, String method) throws Exception{
// 
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc;
//        CtMethod cm = null;
//        cc = pool.get(clazz.getName());
//        cm = cc.getDeclaredMethod(method);
//        return getMethodParamNames(cm);
//    }
}
