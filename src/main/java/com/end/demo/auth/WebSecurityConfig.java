package com.end.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/holiday/**").permitAll()
//                .antMatchers("/cms/**").permitAll()
//                .antMatchers("/accept/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/check/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .defaultSuccessUrl("/accept")
//                .failureHandler(authenticationFailureHandler)
//                .usernameParameter("userid")
//                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();

        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(" select name as userName, password, enabled "
                + " from user_list where name = ? ")
                .authoritiesByUsernameQuery(" select name as userName, authority "
                + " from user_list where name = ? ")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
