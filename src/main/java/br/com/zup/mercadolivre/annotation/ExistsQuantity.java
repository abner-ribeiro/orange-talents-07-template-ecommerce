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
@Constraint(validatedBy = {ExistsQuantityValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface ExistsQuantity {
    String message() default "Não existe a quantidade desejada em estoque";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
