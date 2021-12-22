package com.ta.c02_5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/requestupdateitem").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/**" ).permitAll()
                .antMatchers("/user/add").hasAuthority("ADMIN")
                .antMatchers("/item/update/**").hasAuthority("STAFF_GUDANG")
                .antMatchers("/request-update-item/viewall").hasAnyAuthority("STAFF_GUDANG", "STAFF_OPERASIONAL")
                .antMatchers("/request-update-item/update/*").hasAnyAuthority("STAFF_GUDANG")
                .antMatchers("/user/viewall/**").hasAnyAuthority("ADMIN", "FACTORY_MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/api/**");
        super.configure(web);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)
//                .withUser("AdminFactory").password(encoder.encode("Admin123"))
//                .roles("ADMIN");
//
//        BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder2)
//                .withUser("FactoryManager").password(encoder.encode("Manager123"))
//                .roles("FACTORY_MANAGER");
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
