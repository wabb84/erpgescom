package com.produccion.gescom.advice.validation.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import com.produccion.gescom.advice.validation.validator.ValidNameValidator;

@Documented
@Constraint(validatedBy = ValidNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidName {
	String message() default "{custom.validacion.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
