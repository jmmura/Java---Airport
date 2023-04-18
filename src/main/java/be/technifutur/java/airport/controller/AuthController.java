package be.technifutur.java.airport.controller;


import be.technifutur.java.airport.model.dto.JWTHolderDTO;
import be.technifutur.java.airport.model.form.LoginForm;
import be.technifutur.java.airport.model.form.RegistrationForm;
import be.technifutur.java.airport.service.AuthService;
import be.technifutur.java.airport.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationForm form){
        authService.register(form);
    }

    @PostMapping("/sign_in")
    public JWTHolderDTO login(@RequestBody @Valid LoginForm form){
        return authService.login(form);
    }


}




