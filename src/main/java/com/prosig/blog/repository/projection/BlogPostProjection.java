package com.prosig.blog.repository.projection;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BlogPostProjection {

    private UUID id;
    private String title;
    private Long commentsCount;

    public BlogPostProjection(UUID id, String title, Long commentsCount) {
        this.id = id;
        this.title = title;
        this.commentsCount = commentsCount;
    }
}
