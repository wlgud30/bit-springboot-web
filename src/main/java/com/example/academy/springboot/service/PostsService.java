package com.example.academy.springboot.service;

import com.example.academy.springboot.domain.posts.Posts;
import com.example.academy.springboot.domain.posts.PostsRepository;
import com.example.academy.springboot.web.dto.PostsListResponseDto;
import com.example.academy.springboot.web.dto.PostsResponseDto;
import com.example.academy.springboot.web.dto.PostsSaveRequestDto;
import com.example.academy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        
        System.out.println("아이디는 : "+id);

        return id;
    }

    @Transactional
    public PostsResponseDto findByid(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
}
