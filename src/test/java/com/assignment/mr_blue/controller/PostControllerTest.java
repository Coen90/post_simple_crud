package com.assignment.mr_blue.controller;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.CreatePostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(save.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
                .andDo(MockMvcResultHandlers.print());
    }
}