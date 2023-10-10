package org.example.springboot231009.config.auth;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.config.auth.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession hs;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("Config-auth클래스 어노테이션클래스 LoginUserArgumentResolver 오버라이딩 supportParameter() 진입");

        boolean anno = parameter.getParameterAnnotation(LoginUser.class) !=null;
        boolean u = SessionUser.class.equals(parameter.getParameterType());
        return anno && u;
    }



    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("Config-auth클래스 어노테이션클래스 LoginUserArgumentResolver 오버라이딩 resolveArgument() 진입 파라미터에 전달할 객체를 가져온다");

        return hs.getAttribute("user");
    }
}
