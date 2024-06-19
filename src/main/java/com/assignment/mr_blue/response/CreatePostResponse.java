package com.assignment.mr_blue.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreatePostResponse {

    private final Long id;

    @Builder
    public CreatePostResponse(long id) {
        this.id = id;
    }
}
