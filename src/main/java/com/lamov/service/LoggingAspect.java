package com.lamov.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(weyland)")
    public Object logWeylandCommand(ProceedingJoinPoint pjp, WeylandWatchingYou weyland) throws Throwable {

        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        String argsString = Arrays.toString(args);
        String value = weyland.value();

        if(value.equals("console")) {
            System.out.printf("[%s]: вызов %s с аргументами %s%n",
                    weyland.value(), methodName, argsString);
        }

        try {
            Object result = pjp.proceed();

            if(value.equals("console")) {
                System.out.printf("[%s]: метод %s успешно выполнен%n",
                        weyland.value(), methodName);
            }
            return result;
        } catch (IllegalArgumentException e) {
            if(value.equals("console")) {
                System.err.printf("[%s]: ошибка в методе %s: %s%n",
                        weyland.value(), methodName, e.getMessage());
            }
            throw e;
        }
    }
}
