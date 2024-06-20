package com.assignment.mr_blue.service;

import com.assignment.mr_blue.domain.Post;
import com.assignment.mr_blue.repository.PostRepository;
import com.assignment.mr_blue.request.CreatePostRequest;
import com.assignment.mr_blue.request.EditPostRequest;
import com.assignment.mr_blue.request.GetPostListRequest;
import com.assignment.mr_blue.response.EditPostResponse;
import com.assignment.mr_blue.response.GetPostListResponse;
import com.assignment.mr_blue.response.GetPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void createPost(CreatePostRequest request) {
        Post postEntity = request.toEntity();
        postRepository.save(postEntity);
    }

    @Transactional
    public EditPostResponse editPost(Long id, EditPostRequest request) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        Post requestEntity = request.toEntity();
        postEntity.edit(requestEntity);
        return EditPostResponse.builder()
                .id(id)
                .title(requestEntity.getTitle())
                .content(requestEntity.getContent())
                .build();
    }

    @Transactional
    public long deletePost(Long id) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        postRepository.delete(postEntity);
        return id;
    }

    public GetPostListResponse getPostResponseList(GetPostListRequest request) {
        Page<Post> list = postRepository.findAll(PageRequest.of(request.getPage() - 1, request.getSize()));
        return GetPostListResponse.builder()
                .hasNext(list.hasNext())
                .postList(list
                        .stream()
                        .map(GetPostResponse::new)
                        .toList())
                .build();
    }

}
