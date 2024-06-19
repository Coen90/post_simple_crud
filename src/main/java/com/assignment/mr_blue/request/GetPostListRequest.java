package com.assignment.mr_blue.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetPostListRequest {

    private final int page;
    private final int size;

    // TODO default value 개선하기
    @Builder
    public GetPostListRequest(int page, int size) {
        if (page == 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
        if (size == 0 || size > 100) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }
}
