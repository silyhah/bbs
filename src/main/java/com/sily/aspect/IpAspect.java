package com.sily.aspect;

import com.sily.Exception.BusinessException;
import com.sily.Utils.AddressUtil;
import com.sily.Utils.HttpContextUtil;
import com.sily.Utils.IPUtil;
import com.sily.annoation.IP;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import java.text.MessageFormat;


@Component
@Order(1)
@Aspect
public class IpAspect {

    private static final Logger logger = LoggerFactory.getLogger(IpAspect.class);

    @Pointcut("@annotation(com.sily.annoation.IP)")
    public void getIp(){

    }


    @Around("getIp()")
    public Object getIpDo(ProceedingJoinPoint point){
        try {
            Object target = point.getTarget();
            String methodName = point.getSignature().getName();
            Class<?>[] parameter = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameter);
            IP ip = method.getAnnotation(IP.class);
            if (ip.getIp()){
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                String ipAddr = IPUtil.getIpAddr(request);
                System.out.println(MessageFormat.format("当前IP为:[{0}]；当前IP地址解析出来的地址为:[{1}]", ipAddr, AddressUtil.getCityInfo(ipAddr)));
            }
            return point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("无法解析",e);
            throw new BusinessException("无法解析");
        }
    }
}
