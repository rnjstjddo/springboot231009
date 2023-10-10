package org.example.springboot231009.config.auth.config.auth.dto;


import lombok.Getter;
import lombok.ToString;
import org.example.springboot231009.web.domain.user.User;

import java.io.Serializable;

@Getter
@ToString
public class SessionUser implements Serializable {

    private String name, email, picture;

    public SessionUser(User user) {
        System.out.println("Cofig-auth-dto 클래스 SessionUser 생성자진입");
        System.out.println("Cofig-auth-dto 클래스 SessionUser 생성자진입 파라미터 User -> "+ user);
        //User(id=1, name=권성애, email=kwonsungae88@gmail.com, picture=https://lh3.googleusercontent.com/a/ACg8ocIMFI7TTPU3Y9_cmEjBZmtZVrhordqBqxmH-Ttu3f6Z=s96-c, role=GUEST)

        //if(user !=null) {
            this.name = user.getName();
            this.email = user.getEmail();
            this.picture = user.getPicture();

            System.out.println("Cofig-auth-dto 클래스 SessionUser 생성자진입 -> " + this);
        //}

    }

}
