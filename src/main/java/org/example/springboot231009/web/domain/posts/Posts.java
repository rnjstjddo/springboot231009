package org.example.springboot231009.web.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        System.out.println("엔티티클래스 Posts 생성자 진입");
            this.title=title;
            this.content=content;
            this.author=author;
    }

    public void update(String title, String content){
        System.out.println("엔티티클래스 Posts update() 진입");
        this.title=title;
        this.content =content;
    }
}
