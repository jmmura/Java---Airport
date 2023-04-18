package be.technifutur.java.airport.constraints;
import be.technifutur.java.airport.constraints.Not0Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Not0Validator.class)
public @interface Not0 {
    String message() default "value should not be 0";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
