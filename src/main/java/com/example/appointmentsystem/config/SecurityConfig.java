package com.example.appointmentsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    
    private final CustomUserDetailsServices customUserDetailsServices;

    public SecurityConfig(CustomUserDetailsServices customUserDetailsServices) {
        this.customUserDetailsServices=customUserDetailsServices;
    }

    // @Autowired UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


        /*
        
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsServices(userRepository);
         * UserDetails user=User.withUsername("admin")
         * .password(passwordEncoder().encode("1234"))
         * .roles("ADMIN")
         * .build();
         * return new InMemoryUserDetailsManager(user);
         */

    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // no need to CSRF for REST

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Make REST API stateless,Best for
                                                                                 // REST APIs

                .authorizeHttpRequests(auth -> auth// uthorizeRequst is deprecated

                        // =========================
                        // PUBLIC ENDPOINTS
                        // =========================

                        .requestMatchers(
                                "/api/users/login",
                                "/api/users/register")
                        .permitAll()

                        // Anyone can view services
                        .requestMatchers(HttpMethod.GET, "/api/services/**")
                        .permitAll()

                        // Anyone can view availability
                        .requestMatchers(HttpMethod.GET, "/api/availability/**")
                        .permitAll()

                        // =========================
                        // ADMIN ENDPOINTS
                        // =========================

                        // Creating services -> ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/services/**")
                        .hasRole("ADMIN")

                        // Creating availability -> ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/availability/**")
                        .hasRole("ADMIN")

                        // =========================
                        // APPOINTMENTS
                        // =========================

                        // User must be authenticated to book
                        .requestMatchers("/api/appointment/book")
                        .authenticated()

                        // User must be authenticated
                        .requestMatchers("/api/appointment/user/**")
                        .authenticated()

                        // User must be authenticated
                        .requestMatchers("/api/appointment/update/**")
                        .authenticated()

                        // User must be authenticated
                        .requestMatchers("/api/appointment/service/**")
                        .authenticated()

                        // =========================
                        // USERS
                        // =========================

                        .requestMatchers("/api/users/**")
                        .authenticated()
                        // EVERYTHING ELSE

                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
