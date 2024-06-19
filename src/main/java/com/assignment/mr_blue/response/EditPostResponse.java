package com.assignment.mr_blue.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EditPostResponse {

    private final Long id;

    private final String title;

    private final String content;

    @Builder
    public EditPostResponse(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
