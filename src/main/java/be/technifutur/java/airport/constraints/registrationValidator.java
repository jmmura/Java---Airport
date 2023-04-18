package be.technifutur.java.airport.constraints;

import be.technifutur.java.airport.model.entity.Plane;
import jakarta.validation.*;

import java.time.LocalDate;


public class registrationValidator implements ConstraintValidator<registration, LocalDate> {


    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if(date.isBefore(LocalDate.now().minusDays(7L))){
            return true;
        }
        else{
            context.buildConstraintViolationWithTemplate("date devrait être au moins 7 jours dans le passé").addConstraintViolation();
            return false;
        }
    }
}

