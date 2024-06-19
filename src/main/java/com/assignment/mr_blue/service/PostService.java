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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {
        Post postEntity = request.toEntity();
        Post save = postRepository.save(postEntity);
        return CreatePostResponse.builder()
                .id(save.getId())
                .build();
    }

    @Transactional
    public EditPostResponse editPost(Long id, editPostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        Post requestEntity = request.toEntity();
        post.edit(requestEntity);
        return EditPostResponse.builder()
                .id(id)
                .title(requestEntity.getTitle())
                .content(requestEntity.getContent())
                .build();
    }

    public long deletePost(Long id) {
        return id;
    }

}
