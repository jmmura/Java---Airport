package be.technifutur.java.airport.model.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import  java.util.*;

@Data
public class LoginForm {

    @NotNull
    private String username;
    @NotNull
    private String password;

}
