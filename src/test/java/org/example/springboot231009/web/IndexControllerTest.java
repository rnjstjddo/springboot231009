package org.example.springboot231009.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate trt;

    @Test
    public void 메인페이지_로딩(){
        System.out.println("컨트롤러 IndexController 진입");
        //when

        String body = this.trt.getForObject("/", String.class);

        //then
        assertThat(body).contains("게시글");


    }



}