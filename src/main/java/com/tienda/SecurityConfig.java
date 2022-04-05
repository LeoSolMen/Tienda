/*
Clase Security Config para el proyecto tienda
 */
package com.tienda;

import com.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author LeoSolis
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userDetailService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
   
    //El siguiente metodo funciona para hacer la autenticacion del usuario
    @Override
    protected void configure (AuthenticationManagerBuilder auth)
            throws Exception{
                    // auth.userDetailsService(userDetailsService).passwordEncoder());
               // }
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123")
                .roles("ADMIN","VENDEDOR","USER")
                .and()
                .withUser("vendedor")
                .password("{noop}123")
                .roles("VENDEDOR","USER")
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER");
        }
    

    //El siguiente metodo funciona para realizar la autorizacion de los accesos
    @Override
    protected void configure (HttpSecurity http)
            throws Exception{
                    
        http.authorizeRequests()
                .antMatchers("/crear")
                .hasRole("ADMIN")
                .antMatchers("/articulo/listado","/categoria/listado","cliente/listado")
                .hasAnyRole("ADMIN","VENDEDOR")
                .antMatchers("/")
                .hasAnyRole("USER","VENDEDOR","ADMIN")
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
        }    
}
