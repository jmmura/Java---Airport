package be.technifutur.java.airport.service;

import be.technifutur.java.airport.model.dto.JWTHolderDTO;
import be.technifutur.java.airport.model.form.LoginForm;
import be.technifutur.java.airport.model.form.RegistrationForm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UserService {

    void register (RegistrationForm form);

    JWTHolderDTO login (LoginForm form);


}
