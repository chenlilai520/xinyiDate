package com.example.esdemotest.logging;
import com.example.esdemotest.config.Constants;
import com.example.esdemotest.utils.GsonUtil;
import com.example.esdemotest.utils.HttpServletRequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务日志
 *
 * @author Administrator
 */
@Aspect
@Order(0)
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Environment env;

    @Pointcut("within(com.example.esdemotest.service.*) || within(com.example.esdemotest.controller.*)")
    public void loggingPointcut() {
    }


    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles(Profiles.of(Constants.SPRING_PROFILE_DEVELOPMENT))) {
            log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage());
        } else {
            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
        }
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //计算时长
            long start = System.currentTimeMillis();

            Object result = joinPoint.proceed();
            //计算时长
            long last = System.currentTimeMillis() - start;
            printLog(joinPoint, result, last);
            return result;
        } catch (Throwable t) {
            log.error("异常捕获: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw t;
        }
    }

    private void printLog(ProceedingJoinPoint pjp, Object returnObject, long lastTime) {
        List<Object> collect = Arrays.stream(pjp.getArgs()).filter(x -> x instanceof Principal).collect(Collectors.toList());
        String accountId = "0";
        if (!collect.isEmpty()) {
            Object o = collect.get(0);
            if (o != null) {
                accountId = ((Principal) o).getName();
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String bodyString = HttpServletRequestUtil.getBodyString(request);
        log.info("aop拦截方法: [类名]={}  [方法]={} [版本]={} [平台]={} [持续时间]={} [参数]=【[用户ID]：{} [body]：{}  [query]：{}】 [返回]={}",
                pjp.getTarget().getClass().toString(),
                pjp.getSignature().getName(),
                request.getHeader("version"),
                request.getHeader("platform"),
                lastTime,
                accountId,
                bodyString,
                GsonUtil.toJson(parameterMap),
                returnObject);
    }


}
