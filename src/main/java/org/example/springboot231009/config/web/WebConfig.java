package org.example.springboot231009.config.web;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.config.auth.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver resolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
        System.out.println("Config-web클래스 WebConfig 오버라이딩 addArgumentResolvers() 진입 사용자어노테이션 설정추가");
    }
}
