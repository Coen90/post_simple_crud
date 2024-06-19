package com.assignment.mr_blue.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetPostResponse {

    private final Long id;

    private final String title;

    private final String content;

    @Builder
    public GetPostResponse (Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
