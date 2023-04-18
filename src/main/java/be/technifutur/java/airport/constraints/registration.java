package be.technifutur.java.airport.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = registrationValidator.class)
public @interface registration {
    String message() default "mauvaise date d'enregistrement";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}

