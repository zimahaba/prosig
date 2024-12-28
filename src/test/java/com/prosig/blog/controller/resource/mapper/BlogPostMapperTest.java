package com.prosig.blog.controller.resource.mapper;

import com.prosig.blog.controller.resource.request.BlogPostRequest;
import com.prosig.blog.controller.resource.response.BlogCommentResponse;
import com.prosig.blog.controller.resource.response.BlogPostListResponse;
import com.prosig.blog.controller.resource.response.BlogPostResponse;
import com.prosig.blog.model.BlogComment;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.projection.BlogPostProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BlogPostMapperTest {

    @Mock
    private BlogCommentMapper blogCommentMapper;

    @InjectMocks
    private BlogPostMapper blogPostMapper;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void shouldReturnBlogPostWhenMapFromRequest() {
        // given
        BlogPostRequest request = new BlogPostRequest("title", "content");

        // when
        BlogPost blogPost = blogPostMapper.mapFromRequest(request);

        // then
        assertEquals(request.title(), blogPost.getTitle());
        assertEquals(request.content(), blogPost.getContent());
    }

    @Test
    public void shouldReturnBlogPostResponseWhenMapToResponseFromBlogPost() {
        // given
        UUID commentId = UUID.randomUUID();
        List<BlogComment> comments = List.of(BlogComment.builder().id(commentId).content("content").build());
        BlogPost blogPost = BlogPost.builder().id(UUID.randomUUID()).title("title").content("content").created(LocalDateTime.now()).comments(comments).build();
        List<BlogCommentResponse> commentResponseList = List.of(BlogCommentResponse.builder().id(commentId.toString()).content("content").build());
        when(blogCommentMapper.mapToResponseList(comments)).thenReturn(commentResponseList);

        // when
        BlogPostResponse response = blogPostMapper.mapToResponse(blogPost);

        // then
        assertEquals(blogPost.getId().toString(), response.getId());
        assertEquals(blogPost.getTitle(), response.getTitle());
        assertEquals(blogPost.getContent(), response.getContent());
        assertEquals(1, response.getComments().size());
        BlogCommentResponse commentResponse = response.getComments().getFirst();
        assertEquals(commentId.toString(), commentResponse.getId());
        assertEquals("content", commentResponse.getContent());
    }

    @Test
    public void shouldReturnBlogPostListResponseWhenMapToResponseList() {
        // given
        BlogPostProjection projection = new BlogPostProjection(UUID.randomUUID(), "title", 3L);

        // when
        BlogPostListResponse response = blogPostMapper.mapToResponseList(List.of(projection));

        // then
        assertEquals(1, response.getBlogPostList().size());
        BlogPostResponse postResponse = response.getBlogPostList().getFirst();
        assertEquals(projection.getId().toString(), postResponse.getId());
        assertEquals(projection.getTitle(), postResponse.getTitle());
        assertEquals(projection.getCommentsCount(), postResponse.getCommentsCount());
    }
}
