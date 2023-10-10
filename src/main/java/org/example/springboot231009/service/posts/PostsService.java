package org.example.springboot231009.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.springboot231009.web.domain.posts.Posts;
import org.example.springboot231009.web.domain.posts.PostsRepository;
import org.example.springboot231009.web.dto.PostsListResponseDto;
import org.example.springboot231009.web.dto.PostsResponseDto;
import org.example.springboot231009.web.dto.PostsSaveRequestDto;
import org.example.springboot231009.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository pp;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        System.out.println("서비스클래스 PostService save() 진입");
        return pp.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto){
        System.out.println("서비스클래스 PostsService update() 진입");

        Posts p = pp.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. "+ id));

        p.update(dto.getTitle(), dto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id){
        System.out.println("서비스클래스 PostsService findById() 진입");
        Posts entity = pp.findById(id).orElseThrow( () ->new IllegalArgumentException("해당 게시글이 없습니다."+id));
        return new PostsResponseDto(entity);
    }
    
    //전체게시글
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        System.out.println("서비스클래스 PostsService findAllDesc() 진입");

        return pp.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
    
    //삭제
    @Transactional
    public void delete(Long id){
        System.out.println("서비스클래스 PostsService delete() 진입");
        Posts entity = pp.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다."+id));

        pp.delete(entity);
    }
}
