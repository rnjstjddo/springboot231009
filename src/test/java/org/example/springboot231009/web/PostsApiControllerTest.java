package org.example.springboot231009.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springboot231009.web.domain.posts.Posts;
import org.example.springboot231009.web.domain.posts.PostsRepository;
import org.example.springboot231009.web.dto.PostsSaveRequestDto;
import org.example.springboot231009.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.*;
import javax.xml.ws.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate trt;

    @Autowired
    private PostsRepository pp;

    @After
    public void tearDown() throws Exception{
        System.out.println("테스트 PostsApiControllerTest @After진입");
         pp.deleteAll();
    }

    //MockMvc사용
    @Autowired
    private WebApplicationContext context;

    private MockMvc mm;

    @Before
    public void setup(){
        System.out.println("테스트 PostsApiControllerTest @Before진입");
        mm= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())
                .build();
    }
    
    //추가
    @WithMockUser(roles="USER") //가짜사용자만듬
    @Test
    public void Posts_등록된다() throws Exception{
        System.out.println("테스트 PostsApiControllerTest 진입");

        //given
        String title = "title1";
        String content ="content1";
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("author")
                .content(content)
                .title(title)
                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        //when
        mm.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
                ).andExpect(status().isOk());

        //ResponseEntity<Long> re = trt.postForEntity(url, dto, Long.class);


        //then
        //assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);

        //assertThat(re.getBody()).isGreaterThan(0L);

        List<Posts> list = pp.findAll();

        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);

    }

    //추가
    @WithMockUser(roles="USER")
    @Test
    public void Posts_수정된다() throws Exception{
        System.out.println("테스트 PostsApiController 진입 ");
        //given
        Posts p = pp.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());

        Long updateId = p.getId();
        String ut = "title2";
        String uc ="content2";

        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title(ut)
                .content(uc)
                .build();

        String url ="http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> he = new HttpEntity<>(dto);

        //when
        //ResponseEntity<Long> re = trt.exchange(url, HttpMethod.PUT, he, Long.class);

        mm.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
        ).andExpect(status().isOk());

        //then
//        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
  //      assertThat(re.getBody()).isGreaterThan(0L);

        List<Posts> list = pp.findAll();

        assertThat(list.get(0).getTitle()).isEqualTo(ut);
        assertThat(list.get(0).getContent()).isEqualTo(uc);

    }

}