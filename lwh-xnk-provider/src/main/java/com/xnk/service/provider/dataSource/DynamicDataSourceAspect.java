package com.xnk.service.provider.dataSource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yanzhong.wu
 */
@Aspect
// 该切面应当先于 @Transactional 执行
@Order(-1)
@Component
public class DynamicDataSourceAspect {
	private Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	
    /** * Restore DataSource * * @param point * @param targetDataSource */
    @After("@annotation(targetDataSource))")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("Restore DataSource to [{}] in Method [{}] " +
                DynamicDataSourceContextHolder.getDataSourceKey() + point.getSignature());
    }
    
    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     * 
     * @param point
     * @throws Exception
     */
     @Before("execution(* com.xnk.service.*.service.*.*(..)) or execution(* com.xnk.service.*.api.*.*(..))")
    public void execution(JoinPoint point) throws Exception {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     * 
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(TargetDataSource.class)) {
            	TargetDataSource dataSource = clazz.getAnnotation(TargetDataSource.class);
            	DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
                logger.debug("DynamicDataSource class [execution]:"+dataSource.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
            	TargetDataSource dataSource = m.getAnnotation(TargetDataSource.class);
            	DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
                logger.debug("DynamicDataSource method [execution]:"+dataSource.value());
            }
        } catch (Exception e) {
        	logger.error("resolveDataSource", e);
        }
    }
    
    
}
