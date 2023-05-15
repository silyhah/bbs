package com.sily.aspect;

import com.sily.Utils.JsonUtils;
import com.sily.Utils.StringTools;
import com.sily.Utils.VerifyUtils;
import com.sily.annoation.GlobalInterceptor;
import com.sily.annoation.VerifyParam;
import com.sily.common.BusinessException;
import com.sily.entity.enums.VerifyRegexEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class OperationAspect {

    private static final String[] TYPE_BASE = {"java.lang.String", "java.lang.Integer", "java.lang.Long"};

    private static final Logger logger = LoggerFactory.getLogger(OperationAspect.class);

    @Pointcut("@annotation(com.sily.annoation.GlobalInterceptor)")
    public void requestInterceptor() {

    }

    @Around("requestInterceptor()")
    public Object interceptorDo(ProceedingJoinPoint point) {
        try {
            Object target = point.getTarget();
            Object[] args = point.getArgs();
            String methodName = point.getSignature().getName();
            Class<?>[] parameter = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameter);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);

            if (interceptor.checkLogin()) {

            }

            if (interceptor.checkParam()) {
                validateParas(method, args);
            }
        } catch (Exception e) {
            logger.error("错误");
        }
        return null;
    }

    private void validateParas(Method method, Object[] arguments) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = arguments[i];
            logger.info(JsonUtils.ConvertObj2Json(value));
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }

            if (ArrayUtils.contains(TYPE_BASE, parameter.getParameterizedType().getTypeName())) {
                checkValue(value,verifyParam);
            } else {

            }

        }
    }

    private void checkValue(Object value, VerifyParam verifyParam) {
        boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        int length = value == null ? 0 : value.toString().length();
        /**
         * 校验为空
         */
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException("600");
        }
        /**
         * 校验长度
         */
        if (!isEmpty && (verifyParam.max() != -1 && verifyParam.max() > length || verifyParam.min() != -1 && verifyParam.min() < length)) {
            throw new BusinessException("600");
        }
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyUtils.verify(verifyParam.regex(), String.valueOf(value))) {
            throw new BusinessException("600");
        }
    }

}
