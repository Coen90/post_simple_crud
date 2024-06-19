package com.assignment.mr_blue.service;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.editPostRequest;
import com.assignment.mr_blue.response.CreatePostResponse;
import com.assignment.mr_blue.response.EditPostResponse;
import com.assignment.mr_blue.response.GetPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public GetPostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return GetPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        Post postEntity = request.toEntity();
        Post save = postRepository.save(postEntity);
        return CreatePostResponse.builder()
                .id(save.getId())
                .build();
    }

    public EditPostResponse editPost(editPostRequest request) {
        Post entity = request.toEntity();
        return EditPostResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }

    public long deletePost(Long id) {
        return id;
    }

}
