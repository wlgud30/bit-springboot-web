package com.example.academy.springboot.web;

import com.example.academy.springboot.domain.posts.Posts;
import com.example.academy.springboot.domain.posts.PostsRepository;
import com.example.academy.springboot.web.dto.PostsSaveRequestDto;
import com.example.academy.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void registPosts() throws Exception{
        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody(),greaterThan(0L));

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle(),is(title));
        assertThat(all.get(0).getContent(),is(content));

    }

    @Test
    public void updatePosts() throws Exception{
        Posts savedPosts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "new title";
        String expectedContent = "new content";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();
        System.out.println("여기1 : "+updateId);

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody(),greaterThan(0L));

        List<Posts> all = postsRepository.findAll();
        System.out.println("여기2 : "+all.get(0).getTitle());
        assertThat(all.get(0).getTitle(),is(expectedTitle));
        assertThat(all.get(0).getContent(),is(expectedContent));

    }



}
