package com.assignment.mr_blue.controller;

import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.EditPostRequest;
import com.assignment.mr_blue.request.GetPostListRequest;
import com.assignment.mr_blue.response.EditPostResponse;
import com.assignment.mr_blue.response.GetPostListResponse;
import com.assignment.mr_blue.response.GetPostResponse;
import com.assignment.mr_blue.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable(name = "postId") Long id) {
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(postService.getPost(id));
    }

    @PostMapping("/post")
    public void createPost(@RequestBody CreatePostRequest req) {
        postService.createPost(req);
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<EditPostResponse> editPost(@PathVariable(name = "postId") Long id, @RequestBody EditPostRequest req) {
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(postService.editPost(id, req));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long id) {
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(postService.deletePost(id) + " deleted");
    }

    @GetMapping("/posts")
    public ResponseEntity<GetPostListResponse> getPostList(GetPostListRequest req) {
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(postService.getPostResponseList(req));
    }
}
