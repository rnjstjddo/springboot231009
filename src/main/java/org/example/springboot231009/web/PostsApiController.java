package org.example.springboot231009.web;


import lombok.RequiredArgsConstructor;
import org.example.springboot231009.service.posts.PostsService;
import org.example.springboot231009.web.dto.PostsResponseDto;
import org.example.springboot231009.web.dto.PostsSaveRequestDto;
import org.example.springboot231009.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService ps;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        System.out.println("API컨트롤러 PostsApiController save() 진입");
        return ps.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto dto){
        System.out.println("API컨트롤러 PostsApiController update() 진입");
        return ps.update(id, dto);

    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        System.out.println("API컨트롤러 PostApiController findById() 진입");
        return ps.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        System.out.println("API컨트롤러 PostsApiController delete() 진입");
        ps.delete(id);
        return id;
    }

}
