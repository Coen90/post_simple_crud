package com.assignment.mr_blue.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPostListResponse {

    private final boolean hasNext;

    private final List<GetPostResponse> postList;

    @Builder
    public GetPostListResponse(boolean hasNext, List<GetPostResponse> postList) {
        this.hasNext = hasNext;
        this.postList = postList;
    }
}
