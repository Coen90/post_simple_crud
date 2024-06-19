package com.assignment.mr_blue.service;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.editPostRequest;
import com.assignment.mr_blue.response.CreatePostResponse;
import com.assignment.mr_blue.response.EditPostResponse;
import com.assignment.mr_blue.response.GetPostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    public GetPostResponse getPost(Long id) {
        return GetPostResponse.builder()
                .id(id)
                .title("test Title")
                .content("test content")
                .build();
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        Post postEntity = request.toEntity();
        return CreatePostResponse.builder()
                .id(1)
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
