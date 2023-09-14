package ru.alfabank.api.eco.emuls.aspects;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.dto.ResponseType;
import ru.alfabank.api.eco.emuls.supplier.RandomResponseTypeSupplier;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class ThriftTimeControlAspect {
    private final RandomResponseTypeSupplier randomResponseTypeSupplier;

    @SneakyThrows
    @Around("@annotation(ru.alfabank.api.eco.emuls.annotations.ThriftTimeControl)")
    public Object onlyIfThriftTimeControl(final ProceedingJoinPoint proceedingJoinPoint) {
        long tStart = System.currentTimeMillis();

        String ws = getWSName(proceedingJoinPoint);
        final ResponseType randomResponseType = randomResponseTypeSupplier.getRandomResponseType(ws);

        final Object response = proceedingJoinPoint.proceed();

        long sleepTime = randomResponseType.getTimeoutInMilliseconds();
        long tPassed = System.currentTimeMillis() - tStart;
        if ((sleepTime - tPassed) > 0) {
            TimeUnit.MILLISECONDS.sleep(sleepTime - tPassed);
        }

        return response;
    }

    private String getWSName(final ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        final String methodName = method.getName();
        final String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        return className + "_" + methodName;
    }
}
