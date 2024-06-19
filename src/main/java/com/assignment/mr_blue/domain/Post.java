package com.assignment.mr_blue.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void edit(Post post) {
        this.title = post.title;
        this.content = post.content;
    }
}
