package com.learningSpringSecurity.skySecurity.configuration;

import com.learningSpringSecurity.skySecurity.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private MyUserDetailService MyuserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(custumizer->custumizer.disable());
        http.authorizeHttpRequests(request->request
                .requestMatchers("/api/v1/register","/api/v1/login" )
                .permitAll()
                .anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());
       http.httpBasic(Customizer.withDefaults());
       http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("Akash")
//                .password("A@123")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user1= User.withDefaultPasswordEncoder()
//                .username("Vijay")
//                .password("V@123")
//                .roles("TEACHER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, user1);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(MyuserDetailService);

        return provider;
    }


}
