package com.inmotion.trafico.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValidator {
    String message() default "Número de teléfono inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}