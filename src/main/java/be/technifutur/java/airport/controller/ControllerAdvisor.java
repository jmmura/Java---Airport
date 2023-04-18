package be.technifutur.java.airport.controller;

import be.technifutur.java.airport.exceptions.FormValidationException;
import be.technifutur.java.airport.exceptions.RessourceNotFoundException;
import be.technifutur.java.airport.model.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.*;

@ControllerAdvice
public class ControllerAdvisor {

    //?????????
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> testHandler(RessourceNotFoundException e, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO(
                request.getMethod(), request.getRequestURL().toString(),e.getMessage(), HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
