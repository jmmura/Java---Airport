package be.technifutur.java.airport.config;

import jakarta.validation.Constraint;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Spring doc:  No session will be created or used by Spring Security

        http.authorizeHttpRequests( (authorize) -> {
            authorize
                    //.requestMatchers("/plane/add").authenticated()          //on ne peut ajouter un avion que si on est identifié
                    .requestMatchers("/plane/all").anonymous()              //n'importe qui peut voir tous les avions
                    .requestMatchers("/plane/update-maintenance/*").hasRole("ADMIN")    //seuls ayant role ADMIN peuvent faire les updatee
                    //.requestMatchers("/plane/*/update").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/plane/*").hasAnyRole("USER","ADMIN")     //méthodes GET uniquement pour USERs et ADMINs
                    //.requestMatchers("/plane/add").hasRole("USER")
                    .requestMatchers("/plane/?/delete").hasRole("ADMIN")        //? remplace 1 caractère
                    .requestMatchers(request -> request.getRequestURI().length()>50).hasRole("ADMIN")
                    //.requestMatchers(HttpMethod.POST).hasRole("ADMIN")


                    .requestMatchers(HttpMethod.GET, "/flight/*").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/flight/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/flight/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/flight/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/flight/*").hasRole("ADMIN")

                    .requestMatchers("/auth/register").anonymous()

                    .anyRequest().permitAll();
        });

        return http.build();


    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService userDetailsService( PasswordEncoder encoder ){
//        List<UserDetails> users = List.of(      //UserdDetail: prend un username-> rend ses informations
//                User.builder()
//                        .username("user")
//                        .password(encoder.encode("pass" ))
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(encoder.encode("pass" ))
//                        .roles("ADMIN","USER")
//                        .build()
//        );
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

