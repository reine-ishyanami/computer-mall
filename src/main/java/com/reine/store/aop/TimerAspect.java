package com.reine.store.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * 切面类
 *
 * @author reine
 * 2022/5/9 22:01
 */
@Component
@Aspect
@Slf4j
public class TimerAspect {

    @Around("execution(* com.reine.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 先记录当前时间
        long start = System.currentTimeMillis();
        Object result = point.proceed();

        long end = System.currentTimeMillis();
        log.info("耗时---{}ms", (end - start));

        return result;
    }
}
