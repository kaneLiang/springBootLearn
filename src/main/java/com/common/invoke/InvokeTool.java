package com.common.invoke;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @auth snifferhu
 * @date 2016/12/27 22:09
 */
@Component
public class InvokeTool {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static Map<String,Method> methodCache = new ConcurrentHashMap<>(1024);

    public Annotation getAnnotation(JoinPoint jp,Class targetAnnotation){
        Class<?> clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        if (methodCache.containsKey(methodName)){
            return methodCache.get(methodName).getAnnotation(targetAnnotation);
        }
        Method[] methods = clazz.getMethods();
        return Stream.of(methods)
                .filter(x -> x.getName().equals(methodName))
                .findAny()
                .orElseThrow(() -> {
                    logger.error("invoke annotation not found!methodName={}, annotation={}",methodName,targetAnnotation);
                    return new RuntimeException(String.format("invoke annotation not found!methodName={}, annotation={}",methodName,targetAnnotation));
                })
                .getAnnotation(targetAnnotation);
    }

    public Method getMethod(JoinPoint jp,Class targetAnnotation){
        Class<?> clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        if (methodCache.containsKey(methodName)){
            return methodCache.get(methodName);
        }

        return Stream.of(clazz.getMethods())
                .filter(x -> x.getName().equals(methodName))
                .findAny()
                .orElseThrow(() -> {
                    logger.error("invoke annotation not found!methodName={}, annotation={}",methodName,targetAnnotation);
                    return new RuntimeException(String.format("invoke annotation not found!methodName={}, annotation={}",methodName,targetAnnotation));
                });
    }
}