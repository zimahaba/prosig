package com.prosig.blog.repository;

import com.prosig.blog.BlogIntegrationTest;
import com.prosig.blog.repository.projection.BlogPostProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = {"/scripts/clean_db.sql", "/scripts/blog_post_repository.sql"})
public class BlogPostRepositoryTest extends BlogIntegrationTest {

    @Autowired
    private BlogPostRepository repository;

    @Test
    public void shouldReturnBlogPostWithCommentsCount() {
        List<BlogPostProjection> blogPostList = repository.findPosts();

        assertEquals(1, blogPostList.size());
        BlogPostProjection projection = blogPostList.getFirst();
        assertEquals("4c659044-0600-42e9-a614-508269c2990e", projection.getId().toString());
        assertEquals("test title 1", projection.getTitle());
        assertEquals(4, projection.getCommentsCount());
    }

}
