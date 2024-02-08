package com.merchant.portal.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/json/**", "/transaction/**").
                permitAll()
                .anyRequest().authenticated().and().httpBasic()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and().csrf().disable()
                .logout()
                .permitAll();
    }




    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false)
                .inMemoryAuthentication()
                .withUser("abdulrhman")
                .password("{bcrypt}$2a$10$m9m9VY.8PRgRrhmCOXqh2exD1iNb3DmIILgoV6fuQFPEWi6MYMgeO") // 123
                .authorities("USER");
    }
}
