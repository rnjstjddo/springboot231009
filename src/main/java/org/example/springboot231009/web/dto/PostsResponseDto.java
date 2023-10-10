package org.example.springboot231009.web.dto;


import lombok.Getter;
import lombok.ToString;
import org.example.springboot231009.web.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
@ToString
public class PostsResponseDto {
    private Long id;
    private String title, content, author;
    private LocalDateTime createdDate, modifiedDate;

    public PostsResponseDto(Posts entity){
        System.out.println("DTO클래스 PostsResponseDto 생성자 진입");
        this.id= entity.getId();
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.author=entity.getAuthor();
        this.createdDate=entity.getCreatedDate();
        this.modifiedDate=entity.getModifiedDate();

        System.out.println("DTO클래스 PostsResponseDto 생성자 진입 -> "+ this.toString());

    }

}
