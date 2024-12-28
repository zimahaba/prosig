package com.prosig.blog.controller.resource.mapper;

import com.prosig.blog.controller.resource.request.BlogCommentRequest;
import com.prosig.blog.controller.resource.response.BlogCommentResponse;
import com.prosig.blog.model.BlogComment;
import com.prosig.blog.model.BlogPost;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BlogCommentMapper {

    public BlogComment mapFromRequest(BlogCommentRequest request, UUID postId) {
        return BlogComment.builder().content(request.content()).blogPost(BlogPost.builder().id(postId).build()).build();
    }

    private BlogCommentResponse mapToResponse(BlogComment blogComment) {
        return BlogCommentResponse.builder().id(blogComment.getId().toString()).content(blogComment.getContent()).build();
    }

    public List<BlogCommentResponse> mapToResponseList(List<BlogComment> blogCommentList) {
        return blogCommentList.stream().map(this::mapToResponse).toList();
    }
}
