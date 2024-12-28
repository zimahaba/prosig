package com.prosig.blog.controller.resource.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostListResponse {

    private List<BlogPostResponse> blogPostList;
}
