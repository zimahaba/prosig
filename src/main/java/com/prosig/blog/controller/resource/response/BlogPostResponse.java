package com.prosig.blog.controller.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogPostResponse {

    private String id;
    private String title;
    private String content;
    private Long commentsCount;
    private List<BlogCommentResponse> comments;
}
