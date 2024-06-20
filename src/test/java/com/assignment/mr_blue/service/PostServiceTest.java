package com.assignment.mr_blue.service;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.EditPostRequest;
import com.assignment.mr_blue.request.GetPostListRequest;
import com.assignment.mr_blue.response.GetPostListResponse;
import com.assignment.mr_blue.response.GetPostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class PostServiceTest {

    final String title = "test title";
    final String content = "test content";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Create Post Test")
    void createPostTest() {
        //given
        CreatePostRequest req = CreatePostRequest.builder()
                .title(title)
                .content(content)
                .build();
        //when
        postService.createPost(req);
        //then
        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals(title, post.getTitle());
        Assertions.assertEquals(content, post.getContent());
    }

    @Test
    @DisplayName("Get Post Test")
    void getPostTest() {
        // given
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        postRepository.save(post);
        // when
        GetPostResponse res = postService.getPost(post.getId());

        // then
        Assertions.assertEquals(1L, res.getId());
        Assertions.assertEquals(title, res.getTitle());
        Assertions.assertEquals(content, res.getContent());

    }

    @Test
    @DisplayName("Edit Post Test")
    void editPostTest() {
        // given
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        postRepository.save(post);
        // when

        EditPostRequest editPost = EditPostRequest.builder()
                .title(title + 1)
                .content(content + 1)
                .build();
        postService.editPost(post.getId(), editPost);

        // then
        Post editedPost = postRepository.findById(post.getId())
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(title + 1, editedPost.getTitle());
        Assertions.assertEquals(content + 1, editedPost.getContent());
    }

    @Test
    @DisplayName("Paging Test")
    void PagingTest() {
        // given
        List<Post> list = IntStream.range(1, 12)
                .mapToObj(i -> Post.builder()
                        .title(title + i)
                        .content(content + 1)
                        .build())
                .toList();
        postRepository.saveAll(list);

        // when
        GetPostListRequest page1 = GetPostListRequest.builder()
                .page(1)
                .size(10)
                .build();
        GetPostListRequest page2 = GetPostListRequest.builder()
                .page(2)
                .size(10)
                .build();
        // then
        GetPostListResponse responsePage1 = postService.getPostResponseList(page1);
        Assertions.assertTrue(responsePage1.isHasNext());
        Assertions.assertEquals(10, responsePage1.getPostList().size());
        GetPostListResponse responsePage2 = postService.getPostResponseList(page2);
        Assertions.assertFalse(responsePage2.isHasNext());
        Assertions.assertEquals(1, responsePage2.getPostList().size());

    }
}