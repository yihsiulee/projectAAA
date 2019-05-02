package com.allpass.projectAAA.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MemberDetailsServiceImp memberDetailsServiceImp;
    @Autowired
    private MemberLoginSuccessHandler memberLoginnSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.MEMBER_INDEX_URL,
                        SecurityConstants.MEMBER_REGISTER_URL,
                        SecurityConstants.H2_CONSOLE
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(memberLoginnSuccessHandler)
                .loginPage(SecurityConstants.MEMBER_LOGIN_URL)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl(SecurityConstants.MEMBER_LOGIN_URL)
                .logoutUrl(SecurityConstants.MEMBER_LOGOUT_URL)
                .permitAll();



        http.headers().frameOptions().disable();


    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.userDetailsService(new MemberDetailsServiceImp());
//    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withUsername("user")
//                        .password("password")
//                        .roles("user")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(memberDetailsServiceImp);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/resources/**",
                        "/static/**",
                        "/assets/**",
                        "/images/**",
                        "/css/**",
                        "/css/images/**",
                        "/js/**",
                        "/js/ie/**",
                        "/fonts/**",
                        "/sass/**",
                        "/sass/base/**",
                        "/sass/components/**",
                        "/sass/layout/**",
                        "/sass/libs/**");
    }
}
