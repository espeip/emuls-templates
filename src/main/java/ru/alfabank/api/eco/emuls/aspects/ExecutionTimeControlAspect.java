package ru.alfabank.api.eco.emuls.aspects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.alfabank.api.eco.emuls.annotations.ExecutionTimeControl;
import ru.alfabank.api.eco.emuls.exceptions.NoSuchClassFieldException;
import ru.alfabank.api.eco.emuls.mock_creators.AbstractWSMockCreator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@SuppressWarnings("all")
@Component
public class ExecutionTimeControlAspect {
    @SneakyThrows
    @Around("@annotation(ru.alfabank.api.eco.emuls.annotations.ExecutionTimeControl)")
    public Object onlyIfExecutionTimeControl(final ProceedingJoinPoint proceedingJoinPoint) {
        final AbstractWSMockCreator mock = prepareMock(proceedingJoinPoint);

        long tStart = System.currentTimeMillis();

        final Object rsp = proceedingJoinPoint.proceed();

        long sleepTime = mock.getCurrentResponseType().getTimeoutInMilliseconds();
        long tPassed = System.currentTimeMillis() - tStart;
        if ((sleepTime - tPassed) > 0) {
            TimeUnit.MILLISECONDS.sleep(sleepTime - tPassed);
        } else {
            //log.error("Response time exceeded on mock {} on {}%", mock.getClass().getName(),
                    //((double) (tPassed - sleepTime) / sleepTime) * 100);
        }
        return rsp;
    }

    @SneakyThrows
    private AbstractWSMockCreator prepareMock(final ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        final Class<?> controller = proceedingJoinPoint.getTarget().getClass();

        ExecutionTimeControl executionTimeControl = method.getAnnotation(ExecutionTimeControl.class);
        final Class<?> mockClass = executionTimeControl.mockClass();
        Field field = Arrays.stream(controller.getDeclaredFields())
                .filter(f -> mockClass.isAssignableFrom(f.getType()))
                .findFirst()
                .orElseThrow(() -> new NoSuchClassFieldException(mockClass.getName() + " is not a field of " + controller.getName()));

        field.setAccessible(true);

        AbstractWSMockCreator mock = (AbstractWSMockCreator) field.get(proceedingJoinPoint.getTarget());
        field.setAccessible(false);
        return mock;
    }
}
