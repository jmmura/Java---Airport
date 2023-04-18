package be.technifutur.java.airport.service;

import be.technifutur.java.airport.model.dto.JWTHolderDTO;
import be.technifutur.java.airport.model.form.LoginForm;
import be.technifutur.java.airport.model.form.RegistrationForm;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public void register(RegistrationForm form);
    public JWTHolderDTO login(LoginForm form);
}
