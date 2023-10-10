package org.example.springboot231009.config.auth;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.config.auth.config.auth.dto.OAuthAttributes;
import org.example.springboot231009.config.auth.config.auth.dto.SessionUser;
import org.example.springboot231009.web.domain.user.User;
import org.example.springboot231009.web.domain.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository ur;

    private final HttpSession hs;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입");
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 파라미터 OAuth2UserRequest -> "+ userRequest);

        OAuth2UserService<OAuth2UserRequest, OAuth2User> os = new DefaultOAuth2UserService();
        OAuth2User ou =os.loadUser(userRequest);
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 OAuth2User -> "+ ou.toString());

        String id = userRequest.getClientRegistration().getRegistrationId(); // google //naver
        String name = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint() // sub //response
                .getUserNameAttributeName();
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 getRegistrationId() -> "+id);
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 getUserNameAttributeName() -> "+ name);

        OAuthAttributes attrs = OAuthAttributes.of(id, name, ou.getAttributes());
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 OAuthATttributes -> "+ attrs);

        User user = saveOrUpdate(attrs);//DB저장
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 오버라이딩 loadUser() 진입 User -> "+ user);

        hs.setAttribute("user", new SessionUser(user)); //dto

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attrs.getAttrs(), attrs.getNameAttributekey());
    }

    private User saveOrUpdate(OAuthAttributes attrs){
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 saveOrUpdate() 진입 User생성후 DB저장후 반환 ");
        User user = ur.findByEmail(attrs.getEmail())
                .map(entity -> entity.update(attrs.getName(), attrs.getPicture()))
                .orElse(attrs.toEntity());
        System.out.println("Config-auth클래스 CustomOAuthUserService 클래스 saveOrUpdate() 진입 User반환 -> "+ user.toString());

        return ur.save(user);
    }
}
