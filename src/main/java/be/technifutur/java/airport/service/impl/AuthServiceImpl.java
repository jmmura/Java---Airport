package be.technifutur.java.airport.service.impl;

import be.technifutur.java.airport.exceptions.FormValidationException;
import be.technifutur.java.airport.model.dto.JWTHolderDTO;
import be.technifutur.java.airport.model.entity.User;
import be.technifutur.java.airport.model.form.LoginForm;
import be.technifutur.java.airport.model.form.RegistrationForm;
import be.technifutur.java.airport.repository.UserRepository;
import be.technifutur.java.airport.service.AuthService;
import be.technifutur.java.airport.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(RegistrationForm form) {
        if(!form.getPassword().equals(form.getConfirmPswd())){
            throw  new FormValidationException("password and confirmpassword should be the same");
        }

        if(userRepository.existsByUsername(form.getUsername())){
            throw  new FormValidationException("username not available");
        }

        User user = form.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public JWTHolderDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword());
        auth=authManager.authenticate( auth );
        String token = jwtProvider.createToken((Neo4jProperties.Authentication) auth);

        return  new JWTHolderDTO(token);



    }

}
