package org.example.springboot231009.web.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        System.out.println("엔티티클래스 User 생성자진입");
        this.name= name;
        this.email= email;
        this.picture=picture;
        this.role=role;

        System.out.println("엔티티클래스 User 생성자진입 -> "+ this.toString());
    }

    public User update(String name, String picture){
        System.out.println("엔티티클래스 User update() 진입 ");
        this.name= name;
        this.picture= picture;

        return this;
    }


    public String getRoleKey(){
        System.out.println("엔티티클래스 User getRoleKey() 진입");
        return this.role.getKey();
    }
}
