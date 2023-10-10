package org.example.springboot231009.web.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    
    //게시글전체조회 쿼리메소드
    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();

    
}
