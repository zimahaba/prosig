package com.prosig.blog.controller.resource.mapper;

import com.prosig.blog.controller.resource.request.BlogCommentRequest;
import com.prosig.blog.controller.resource.response.BlogCommentResponse;
import com.prosig.blog.model.BlogComment;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlogCommentMapperTest {

    private BlogCommentMapper blogCommentMapper = new BlogCommentMapper();

    @Test
    public void shouldReturnBlogPostWhenMapFromRequest() {
        // given
        UUID postId = UUID.randomUUID();
        BlogCommentRequest request = new BlogCommentRequest("content");

        // when
        BlogComment blogComment = blogCommentMapper.mapFromRequest(request, postId);

        // then
        assertEquals(request.content(), blogComment.getContent());
        assertNotNull(blogComment.getBlogPost());
        assertEquals(postId, blogComment.getBlogPost().getId());
    }

    @Test
    public void shouldReturnBlogCommentResponseListWhenMapToResponseList() {
        // given
        BlogComment blogComment = BlogComment.builder().id(UUID.randomUUID()).content("content").build();

        // when
        List<BlogCommentResponse> responseList = blogCommentMapper.mapToResponseList(List.of(blogComment));

        // then
        assertEquals(1, responseList.size());
        BlogCommentResponse response = responseList.getFirst();
        assertEquals(blogComment.getId().toString(), response.getId());
        assertEquals(blogComment.getContent(), response.getContent());
    }
}
