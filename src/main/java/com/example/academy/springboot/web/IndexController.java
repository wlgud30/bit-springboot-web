package com.example.academy.springboot.web;


import com.example.academy.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String saveposts(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePosts(@PathVariable Long id, Model model){
        return "";
    }
}
