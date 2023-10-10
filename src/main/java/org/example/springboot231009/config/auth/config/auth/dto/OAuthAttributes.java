package org.example.springboot231009.config.auth.config.auth.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.springboot231009.web.domain.user.Role;
import org.example.springboot231009.web.domain.user.User;

import java.io.PrintStream;
import java.util.Map;

@Getter
@ToString
public class OAuthAttributes {

    private Map<String, Object> attrs;
    private String nameAttributekey;
    private String name, email, picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attrs, String nameAttributekey, String name, String email, String picture){
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입");
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입 파라미터 Map<String, Object> ->"+ attrs);
//{sub=114017297734871077662,
// name=권성애,
// given_name=성애,
// family_name=권,
// picture=https://lh3.googleusercontent.com/a/ACg8ocIMFI7TTPU3Y9_cmEjBZmtZVrhordqBqxmH-Ttu3f6Z=s96-c,
// email=kwonsungae88@gmail.com,
// email_verified=true,
// locale=ko}
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입 파라미터 nameAttributeKey -> "+ nameAttributekey);
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입 파라미터 name -> "+ name);
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입 파라미터 email -> "+ email);
        System.out.println("Config-auth-dto OAuthAttributes 클래스 생성자진입 파라미터 picture -> "+ picture);

        this.attrs= attrs;
        this.nameAttributekey= nameAttributekey;
        this.name= name;
        this.email = email;
        this.picture=picture;
    }

    public static OAuthAttributes of(String id, String name, Map<String, Object> attrs){
        System.out.println("Config-auth-dto OAuthAttributes 클래스 of() 메소드 진입 ");

        //네이버로그인추가
        if("naver".equals(id)){
            System.out.println("Config-auth-dto OAuthAttributes 클래스 of() 메소드 진입 네이버일경우");
            return ofNaver("id", attrs);
        }

        return ofGoogle(name, attrs);
    }

    private static OAuthAttributes ofNaver(String name, Map<String, Object> attrs){
        System.out.println("Config-auth-dto OAuthAttributes 클래스 ofNaver() 메소드 진입 OAuthAttributes 생성후 반환");

        Map<String, Object> response = ( Map<String, Object> ) attrs.get("response");

        return OAuthAttributes.builder()
                .name( (String) response.get("name"))
                .email( (String) response.get("email"))
                .picture( (String) response.get("profile_image"))
                .attrs(response)
                .nameAttributekey(name)
                .build();
    }

    private static OAuthAttributes ofGoogle(String name, Map<String, Object> attrs){
        System.out.println("Config-auth-dto OAuthAttributes 클래스 ofGoogle() 메소드 진입 OAuthAttributes 생성후 반환");

        return OAuthAttributes.builder()
                .name( (String)attrs.get("name"))
                .email( (String) attrs.get("email"))
                .picture( (String) attrs.get("picture"))
                .attrs(attrs)
                .nameAttributekey(name)
                .build();
    }

    public User toEntity(){
        System.out.println("Config-auth-dto OAuthAttributes 클래스 toEntity() User 생성 후 반환");

        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();

    }
}
