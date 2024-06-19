package com.assignment.mr_blue.request;

import com.assignment.mr_blue.domain.Post;
import lombok.Getter;

@Getter
public class CreatePostRequest {

    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
