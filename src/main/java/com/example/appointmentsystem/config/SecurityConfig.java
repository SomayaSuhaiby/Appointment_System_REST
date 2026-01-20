package com.example.appointmentsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
import com.example.appointmentsystem.repositories.UserRepository;
import com.example.appointmentsystem.services.CustomUserDetailsServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Autowired UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       // return new CustomUserDetailsServices(userRepository);
        
         UserDetails user=User.withUsername("admin")
         .password(passwordEncoder().encode("1234"))
         .roles("ADMIN")
         .build();
         return new InMemoryUserDetailsManager(user);
         
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // no need to CSRF for REST

                   
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Make REST API stateless,Best for REST APIs
                                                                                   
                .authorizeHttpRequests(auth -> auth// uthorizeRequst is deprecated
                        // public
                        .requestMatchers("/api/users/login", "/api/users/register",
                                "/api/services/**", "/api/availability/**")
                        .permitAll()

                        // api/appointment autenticated
                        .requestMatchers("/api/appointment/book",
                                "/api/appointment/user/**",
                                "/api/appointment/update/**")
                        .permitAll()
                        .requestMatchers("/api/appointment/service/**").permitAll()

                        // Users
                        .requestMatchers("/api/users/**").authenticated()

                        .anyRequest().permitAll())
                        .httpBasic(Customizer.withDefaults())   ;

               

        return http.build();
    }

}
