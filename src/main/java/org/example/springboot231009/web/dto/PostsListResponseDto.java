package org.example.springboot231009.web.dto;


import lombok.Getter;
import lombok.ToString;
import org.example.springboot231009.web.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
@ToString
public class PostsListResponseDto {

    private Long id;
    private String title, author;

    private LocalDateTime modifiedDate, createdDate;

    public PostsListResponseDto(Posts entity){
        System.out.println("DTO클래스 PostsListResponseDto 생성자진입 ");

        this.id=entity.getId();
        this.title = entity.getTitle();
        this.author= entity.getAuthor();
        this.modifiedDate=entity.getModifiedDate();
        this.createdDate=entity.getCreatedDate();
        System.out.println("DTO클래스 PostsListResponseDto 생성자진입 ->"+ this.toString());

    }
}
