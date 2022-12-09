package com.example.food_project.security;

import com.example.food_project.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
    //Dùng để khởi tạo danh sách user cứng và danh sách user này được lưu ở RAM
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user1 = User.withUsername("cybersoft")
//                .password(passwordEncoder().encode("123")).roles("USER")
//                .build();
//
//        UserDetails user2 = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin123")).roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Qui định các rule liên quan đến bảo mật và quyeền truy cập
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
        * antMatcher : Định nghĩa link cần xác thực
        * authenticated: Bắt buộc phải chứng thực (đăng nhập) vào link chỉ định ở antMatchers
        * permitAll: Cho phép truy cập full quyền vào link chỉ định ở antMatcher
        * anyRequest: Toàn bộ request gọi vào API
        */

        http.csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/file/**").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/signin/test").authenticated()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

