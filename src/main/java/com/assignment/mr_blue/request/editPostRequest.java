package com.assignment.mr_blue.request;

import com.assignment.mr_blue.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class editPostRequest {

    private final String title;

    private final String content;

    @Builder
    public editPostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
