package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for defining security rules in the application.
 * This class extends {@link WebSecurityConfigurerAdapter} to customize security settings.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()  // Allow public access
                .anyRequest().authenticated()           // Require authentication for other requests
                .and()
                .formLogin()                            // Use form-based login
                .permitAll()                            // Allow everyone to access the login page
                .and()
                .logout()                               // Configure logout
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    /**
     * Defines a {@link PasswordEncoder} bean.
     * In this configuration, it uses {@link NoOpPasswordEncoder}, which is not recommended for production.
     * Consider using a more secure password encoder in a production environment.
     *
     * @return A {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
