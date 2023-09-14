package ru.alfabank.api.eco.emuls.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Для методов классов-контроллеров,
 * использующих внутри себя только один класс мока,
 * являющимся наследником от AbstractWSMockCreator
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTimeControl {
    Class<?> mockClass();
}
