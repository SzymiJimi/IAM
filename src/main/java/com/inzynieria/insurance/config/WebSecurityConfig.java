package com.inzynieria.insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableSpringConfigured
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("pass").roles("ADMIN");
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true " + "from user where username=? ")
                .authoritiesByUsernameQuery("select user.username, role.name from user, role, userRoles where user.idUser=userRoles.USER_IDUSER and userRoles.ROLE_IDROLE=role.idRole AND username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/navBarStyle.css").anonymous()
                .antMatchers("/navBarStyle.css").permitAll()
                .antMatchers("/login/*").anonymous()
                .antMatchers("/order/*").anonymous()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN", "AGENT", "CARSPECIALIST", "HEALTHSPECIALIST", "TRAVELSPECIALIST", "SPECIALIST","CLIENT")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe().tokenValiditySeconds(2419200).key("iamKey")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

}