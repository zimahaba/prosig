package com.prosig.blog.controller.resource.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogCommentResponse {

    private String id;
    private String content;
}
