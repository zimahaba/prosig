package com.prosig.blog.service;

import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BlogPostServiceTest {

    @Mock
    private BlogPostRepository repository;

    @InjectMocks
    private BlogPostService service;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void shouldCreateNewBlogPost() {
        // given
        BlogPost blogPost = BlogPost.builder().id(UUID.randomUUID()).title("title").content("content").build();
        BlogPost expectedBlogPost = BlogPost.builder().id(UUID.randomUUID()).title("title").content("content").created(LocalDateTime.now()).build();
        when(repository.save(blogPost)).thenReturn(expectedBlogPost);

        // when
        BlogPost createdBlogPost = service.create(blogPost);

        // then
        assertEquals(expectedBlogPost.getId(), createdBlogPost.getId());
        assertEquals(expectedBlogPost.getTitle(), createdBlogPost.getTitle());
        assertEquals(expectedBlogPost.getContent(), createdBlogPost.getContent());
        assertEquals(expectedBlogPost.getCreated(), createdBlogPost.getCreated());
    }
}
