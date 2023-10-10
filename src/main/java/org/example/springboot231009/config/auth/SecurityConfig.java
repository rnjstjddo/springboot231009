package org.example.springboot231009.config.auth;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.web.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuthUserService us;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Cofig-auth클래스 SecurityConfig 오버라이딩 configure() 진입");

        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**")
                .permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(us);

    }
}
