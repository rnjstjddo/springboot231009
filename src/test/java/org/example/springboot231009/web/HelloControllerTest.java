package org.example.springboot231009.web;

import org.example.springboot231009.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class, excludeFilters =
        {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {

    @Autowired
    private MockMvc mm;


    //추가
    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        System.out.println("테스트 HelloControllerTest 클래스 진입");
        String hello="hello";

        mm.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string(hello));
    }



    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws  Exception{
        System.out.println("테스트 HelloControllerTest 클래스 진입");

        String name="hello";
        int amount=1000;

        mm.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }



}