package org.example.springboot231009.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostsUpdateRequestDto {

    private String title, content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        System.out.println("DTO클래스 PostsUpdateRequestDto 생성자진입");
        this.title=title;
        this.content=content;
        System.out.println("DTO클래스 PostsUpdateRequestDto 생성자진입 -> "+ this.toString());

    }
}
