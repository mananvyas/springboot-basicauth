package com.practice.springbootsecurity.basicauth.security.apiconfig;

/*
* Things are hardcoded in this sample code for understanding purpose only
* In production grade code - Roles and Authority can be moved to configuration
*
* Roles: Admin, User, Superuser, Manager
* Authorities/Permissions: Read, Write, Update, Delete, Share, List etc.
*
* */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN").authorities("ACCESS_LIST", "ACCESS_READ")
                .and()
                .withUser("rock")
                .password(passwordEncoder().encode("rock123"))
                .roles("USER").authorities("ACCESS_PROFILE")
                .and()
                .withUser("sales")
                .password(passwordEncoder().encode("sales123"))
                .roles("SALES").authorities("ACCESS_REPORT")
                .and()
                .withUser("manager")
                .password(passwordEncoder().encode("manager123"))
                .roles("MANAGER").authorities("ACCESS_ALL");
    }

    /*
    * Role based authentication and resource access
    * */
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            //Order in which antMatchers are placed matters - if messed up then create a loophole to access resource
            .antMatchers("/api/greeting/hello").permitAll()
            .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN", "MANAGER", "SALES")
            .antMatchers("/api/sales/**").hasAnyRole("SALES", "MANAGER")
            .antMatchers("/api/users/**").hasRole("ADMIN")
            .and().httpBasic();
    }*/

    /*
    * Configuring authority (more granular than roles) for controlling resource access
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //Order in which antMatchers are placed matters - if messed up then create a loophole to access resource
                .antMatchers("/api/greeting/hello").permitAll()

                //Allowed to access resource only for specific matching authority
                .antMatchers("/api/user/**").hasAuthority("ACCESS_LIST")

                //Any authority from the given list
                .antMatchers("/api/sales/**").hasAnyAuthority("ACCESS_REPORT", "ACCESS_ALL")

                .antMatchers("/api/users/**").hasAuthority("ACCESS_LIST")
                .and().httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
