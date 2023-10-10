package org.example.springboot231009.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository pr;

    @After
    public void cleanup(){
        System.out.println("테스트 PostsRepository @After 진입");
        pr.deleteAll();
    }


    @Test
    public void 게시글저장_불러오기(){
        System.out.println("테스트 PostsRepository 진입");

        //given
        String title ="테스트게시물";
        String content="테스트본문";

        pr.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("email@email.com")
                .build());

        //when
        List<Posts> list= pr.findAll();
        //then
        Posts p = list.get(3);
        assertThat(p.getTitle()).isEqualTo(title);
        assertThat(p.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록(){
        System.out.println("컨트롤러 PostsRepositoryTest 진입");

        //given
        LocalDateTime now = LocalDateTime.of(2023,10,9,0,0,0);

        pr.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> list = pr.findAll();

        //then
        Posts entity = list.get(0);

        System.out.println("테스트 Entity -> "+ entity.toString());

        assertThat(entity.getCreatedDate()).isAfter(now);
        assertThat(entity.getModifiedDate()).isAfter(now);

    }
}