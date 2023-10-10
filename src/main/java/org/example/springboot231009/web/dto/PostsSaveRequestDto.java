package org.example.springboot231009.web.dto;


import lombok.*;
import org.example.springboot231009.web.domain.posts.Posts;
import org.springframework.stereotype.Service;

@Getter
@NoArgsConstructor
@ToString
public class PostsSaveRequestDto {

    private String title, content, author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        System.out.println("DTO클래스 PostsSaveRequestDto 생성자 진입");
        this.title= title;
        this.content=content;
        this.author=author;
        System.out.println("DTO클래스 PostsSaveRequestDto 생성자 진입 -> "+ this.toString());

    }

    public Posts toEntity(){
        System.out.println("DTO클래스 PostsSaveRequestDto toEentity() 진입");
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
