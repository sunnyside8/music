package com.example.music.config;

import com.example.music.service.impl.MusicDbUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MusicDbUserService musicDbUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(MusicDbUserService musicDbUserService, PasswordEncoder passwordEncoder) {
        this.musicDbUserService = musicDbUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/js/**","/css/**","/img/**").permitAll()
                .antMatchers("/","/users/login","users/register").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/users/login-error");
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(musicDbUserService);
                //.passwordEncoder()
    }
}
