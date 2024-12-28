package com.prosig.blog.controller.resource.mapper;

import com.prosig.blog.controller.resource.request.BlogPostRequest;
import com.prosig.blog.controller.resource.response.BlogPostListResponse;
import com.prosig.blog.controller.resource.response.BlogPostResponse;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.projection.BlogPostProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogPostMapper {

    private final BlogCommentMapper blogCommentMapper;

    public BlogPost mapFromRequest(BlogPostRequest request) {
        return BlogPost.builder().title(request.title()).content(request.content()).build();
    }

    public BlogPostResponse mapToResponse(BlogPost blogPost) {
        var builder = BlogPostResponse.builder().id(blogPost.getId().toString()).title(blogPost.getTitle()).content(blogPost.getContent());
        builder.comments(blogCommentMapper.mapToResponseList(blogPost.getComments()));
        return builder.build();
    }

    private BlogPostResponse mapToResponse(BlogPostProjection blogPostProjection) {
        return BlogPostResponse.builder()
                .id(blogPostProjection.getId().toString())
                .title(blogPostProjection.getTitle())
                .commentsCount(blogPostProjection.getCommentsCount()).build();
    }

    public BlogPostListResponse mapToResponseList(List<BlogPostProjection> blogPostList) {
        return BlogPostListResponse.builder().blogPostList(blogPostList.stream().map(this::mapToResponse).toList()).build();
    }
}
