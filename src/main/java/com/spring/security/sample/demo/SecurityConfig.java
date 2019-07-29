package com.spring.security.sample.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("MODERATOR")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("user1")
            .roles("user")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(moderator, admin);
    }

    // aplikacja zanim obsłuży żądanie, sprawdza, że osoba ma uprawnienia
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api").permitAll() // sprawdza czy odpowiednie uprawnienia zostaly przypisane do url
                .antMatchers(HttpMethod.POST,"/api").hasAnyRole("MODERATOR") // sprawdza czy odpowiednie uprawnienia zostaly przypisane do url
                .antMatchers(HttpMethod.DELETE,"/api").hasRole("ADMIN")
                .anyRequest().hasRole("ADMIN")
                 .and()
                 .formLogin().permitAll()
                 .and()
                 .logout().permitAll()
                 .and()
                .csrf().disable();
    }
}