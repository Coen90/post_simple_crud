package com.assignment.mr_blue.controller;

import com.assignment.mr_blue.request.GetPostListRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest {

    @Test
    @DisplayName("test")
    void TestDefault() {
        GetPostListRequest req1 = GetPostListRequest.builder()
                .page(1)
                .size(1000)
                .build();
        System.out.println(req1);
        GetPostListRequest req2 = GetPostListRequest.builder()
                .build();
        System.out.println(req2);
    }

}