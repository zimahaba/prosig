package com.prosig.blog.service;

import com.prosig.blog.exception.NotFoundException;
import com.prosig.blog.model.BlogComment;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.BlogCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BlogCommentServiceTest {

    @Mock
    private BlogCommentRepository repository;

    @Mock
    private BlogPostService blogPostService;

    @InjectMocks
    private BlogCommentService service;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void shouldCreateNewBlogComment() {
        // given
        BlogPost blogPost = BlogPost.builder().id(UUID.randomUUID()).build();
        BlogComment blogComment = BlogComment.builder().content("content").blogPost(blogPost).build();
        BlogComment expectedBlogComment = BlogComment.builder().id(UUID.randomUUID()).content("content").blogPost(blogPost).created(LocalDateTime.now()).build();
        when(repository.save(blogComment)).thenReturn(expectedBlogComment);
        when(blogPostService.existsById(blogPost.getId())).thenReturn(true);

        // when
        BlogComment createdBlogComment = service.create(blogComment);

        // then
        assertEquals(expectedBlogComment.getId(), createdBlogComment.getId());
        assertEquals(expectedBlogComment.getContent(), createdBlogComment.getContent());
        assertEquals(expectedBlogComment.getBlogPost().getId(), createdBlogComment.getBlogPost().getId());
        assertEquals(expectedBlogComment.getCreated(), createdBlogComment.getCreated());
    }

    @Test
    public void shouldThrowAnExceptionWhenBlogPostNotFoundOnCreate() {
        // given
        BlogPost blogPost = BlogPost.builder().id(UUID.randomUUID()).build();
        BlogComment blogComment = BlogComment.builder().content("content").blogPost(blogPost).build();
        when(blogPostService.existsById(blogPost.getId())).thenReturn(false);

        // when
        NotFoundException nfe = assertThrows(NotFoundException.class, () -> service.create(blogComment));

        // then
        assertEquals("Blog post with id '" + blogPost.getId().toString() + "' not found.", nfe.getMessage());
    }
}
