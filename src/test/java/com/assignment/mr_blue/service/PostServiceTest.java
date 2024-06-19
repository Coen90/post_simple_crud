package com.assignment.mr_blue.service;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.GetPostListRequest;
import com.assignment.mr_blue.response.GetPostResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("GetPostList to GetPostListResponse")
    void GetPostListToGetPostListResponse() {
        for (int i = 1; i < 100; i++) {
            Post post = Post.builder()
                    .title("test title" + i)
                    .content("test content" + i)
                    .build();
            postRepository.save(post);
        }
        List<GetPostResponse> list = postService.getPostResponseList(GetPostListRequest.builder()
                .page(0)
                .size(10)
                .build());
        System.out.println(list.stream().map(GetPostResponse::toString)
                .collect(Collectors.joining("\n")));
    }
}