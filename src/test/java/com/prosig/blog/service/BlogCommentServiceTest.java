package com.prosig.blog.service;

import com.prosig.blog.model.BlogComment;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.BlogCommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BlogCommentServiceTest {

    @Mock
    private BlogCommentRepository repository;

    @InjectMocks
    private BlogCommentService service;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void shouldCreateNewBlogComment() {
        // given
        BlogComment blogComment = BlogComment.builder().content("content").build();
        BlogPost blogPost = BlogPost.builder().id(UUID.randomUUID()).build();
        BlogComment expectedBlogComment = BlogComment.builder().id(UUID.randomUUID()).content("content").blogPost(blogPost).created(LocalDateTime.now()).build();
        when(repository.save(blogComment)).thenReturn(expectedBlogComment);

        // when
        BlogComment createdBlogComment = service.create(blogComment);

        // then
        assertEquals(expectedBlogComment.getId(), createdBlogComment.getId());
        assertEquals(expectedBlogComment.getContent(), createdBlogComment.getContent());
        assertEquals(expectedBlogComment.getBlogPost().getId(), createdBlogComment.getBlogPost().getId());
        assertEquals(expectedBlogComment.getCreated(), createdBlogComment.getCreated());
    }
}
