package com.example.academy.springboot.web;

import com.example.academy.springboot.service.PostsService;
import com.example.academy.springboot.web.dto.PostsResponseDto;
import com.example.academy.springboot.web.dto.PostsSaveRequestDto;
import com.example.academy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }
    
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostsUpdateRequestDto requestDto){

        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findByid(@PathVariable Long id){
        return postsService.findByid(id);
    }


}
