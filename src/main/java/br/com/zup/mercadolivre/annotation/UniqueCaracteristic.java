package br.com.zup.mercadolivre.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueCaracteristicValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface UniqueCaracteristic {
    String message() default "As caracteristicas não podem ter nome repetido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
