package org.example.springboot231009.web;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.config.auth.LoginUser;
import org.example.springboot231009.config.auth.config.auth.dto.SessionUser;
import org.example.springboot231009.service.posts.PostsService;
import org.example.springboot231009.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService ps;

    //시큐리티추가
    //사용자정의 어노테이션 추가
    //private final HttpSession hs;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser su){
        System.out.println("컨트롤러 IndexController index() 진입");
        System.out.println("컨트롤러 IndexController index() 진입 파라미터 사용자정의어노테이션 사용 SessionUser -> "+ su);

        model.addAttribute("posts", ps.findAllDesc());
        //SessionUser user = (SessionUser) hs.getAttribute("user");


        if( su != null){
            System.out.println("컨트롤러 IndexController index() 진입 HttpSession에 user이름의 값이 존재할때");
            model.addAttribute("userName", su.getName());
        }
        return "index"; //index.mustache 이동
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        System.out.println("컨트롤러 IndexController postsSave() 진입");
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        System.out.println("컨트롤러 IndexController postsUpdate()");
        PostsResponseDto dto = ps.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
