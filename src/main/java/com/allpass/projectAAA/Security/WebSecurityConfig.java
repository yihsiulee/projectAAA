package com.allpass.projectAAA.Security;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
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
                .loginPage(SecurityConstants.MEMBER_LOGIN_URL)
                .successForwardUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl(SecurityConstants.MEMBER_LOGOUT_URL)
                .logoutSuccessUrl(SecurityConstants.MEMBER_LOGIN_URL)
                .permitAll();




        http.headers().frameOptions().disable();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MemberDetailsServiceImp());
    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withUsername(idCardNumber)
//                        .password(password)
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}
