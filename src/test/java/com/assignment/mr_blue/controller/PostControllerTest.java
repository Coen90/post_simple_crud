package com.assignment.mr_blue.controller;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.EditPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Get Post Test")
    void testGetPost() throws Exception {
        //given
        String title = "test post";
        String content = "test content";
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        Post save = postRepository.save(post);

        //when

        //then
        mockMvc.perform(get("/api/post/{postId}", save.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(save.getId()))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Create Post Test")
    void testCreatePost() throws Exception {
        // given
        String title = "test post";
        String content = "test content";
        CreatePostRequest req = CreatePostRequest.builder()
                .title(title)
                .content(content)
                .build();
        String json = objectMapper.writeValueAsString(req);

        // when
        // then
        mockMvc.perform(post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Edit Post Test")
    void testEditPost() throws Exception {
        // given
        String title = "test post";
        String content = "test content";
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        Post save = postRepository.save(post);

        // when
        String editTitle = "edited test post";
        String editContent = "edited test content";
        EditPostRequest req = EditPostRequest.builder()
                .title(editTitle)
                .content(editContent)
                .build();
        String json = objectMapper.writeValueAsString(req);

        // then
        mockMvc.perform(patch("/api/post/{postId}", save.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(save.getId()))
                .andExpect(jsonPath("$.title").value(editTitle))
                .andExpect(jsonPath("$.content").value(editContent))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Delete Post Test")
    void testDeletePost() throws Exception {
        // given
        String title = "test post";
        String content = "test content";
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        Post save = postRepository.save(post);

        // when
        // then
        mockMvc.perform(delete("/api/post/{postId}", save.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Get Post List")
    void testGetPostList() throws Exception {
        // given
        String title = "test post";
        String content = "test content";
        List<Post> postList = IntStream.range(1, 12)
                .mapToObj(value -> Post.builder()
                        .title(title + value)
                        .content(content + value)
                        .build())
                .toList();
        postRepository.saveAll(postList);

        // then
        mockMvc.perform(get("/api/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postList.length()", Matchers.is(10)))
                .andExpect(jsonPath("$.postList[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.postList[0].title", Matchers.is(title + 1)))
                .andExpect(jsonPath("$.postList[0].content", Matchers.is(content + 1)))
                .andExpect(jsonPath("$.hasNext", Matchers.is(true)))
                .andDo(print());
        // then
        mockMvc.perform(get("/api/posts?page=2&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postList.length()", Matchers.is(1)))
                .andExpect(jsonPath("$.postList[0].id", Matchers.is(11)))
                .andExpect(jsonPath("$.postList[0].title", Matchers.is(title + 11)))
                .andExpect(jsonPath("$.postList[0].content", Matchers.is(content + 11)))
                .andExpect(jsonPath("$.hasNext", Matchers.is(false)))
                .andDo(print());

    }

}