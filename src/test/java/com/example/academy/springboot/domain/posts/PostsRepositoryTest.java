package com.example.academy.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void saveArticle(){
        String title = "텍스트 게시글 제목";
        String content = "테스트 게시글";

        postsRepository.save(Posts.builder().title(title).content(content).author("wlgud30@naver.com").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(),is(title));
        assertThat(posts.getContent(),is(content));
    }

    @Test
    public void registBaseTimeEntity(){
        LocalDateTime now = LocalDateTime.of(2021,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println("=======> createDate = "+posts.getCreatedDate());

        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getModifieDate().isAfter(now));
    }

}
