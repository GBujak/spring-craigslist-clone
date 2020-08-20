package com.gbujak.springcraigslistclone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/stworz-nowe/**").authenticated()
                    .antMatchers("/kategoria/**").permitAll()
                    .antMatchers("/ogloszenie/**").permitAll()
                    .antMatchers("/").permitAll()
                    .and()
                .formLogin()
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    
}
