package com.assignment.mr_blue.controller;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.response.GetPostResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {


    @GetMapping("/post/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable(name = "postId") Long id) {
        GetPostResponse res = GetPostResponse.builder()
                .id(id)
                .title("test Title")
                .content("test content")
                .build();
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(res);
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        log.debug(post.toString());
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(post);
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable(name = "postId") Long id, @RequestBody Post post) {
        log.debug(post.toString());
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(post);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long id) {
        return ResponseEntity.status(HttpServletResponse.SC_OK)
                .body(id + " deleted");
    }
}
