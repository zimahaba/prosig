package com.prosig.blog.controller;

import com.prosig.blog.BlogIntegrationTest;
import com.prosig.blog.controller.resource.request.BlogPostRequest;
import com.prosig.blog.controller.resource.response.BlogPostListResponse;
import com.prosig.blog.controller.resource.response.BlogPostResponse;
import com.prosig.blog.controller.resource.response.IdResponse;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = {"/scripts/clean_db.sql", "/scripts/blog_post_controller.sql"})
public class BlogPostControllerTest extends BlogIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    private BlogPostRepository repository;

    @Test
    public void shouldCreateNewPost() {
        BlogPostRequest request = new BlogPostRequest("title create", "content create");
        HttpEntity<BlogPostRequest> entity = new HttpEntity<>(request);

        ResponseEntity<IdResponse> response = restTemplate.exchange("http://localhost:" + port + "/api/posts", POST, entity, IdResponse.class);

        assertEquals(201, response.getStatusCode().value());
        UUID blogPostId = UUID.fromString(response.getBody().getId());
        Optional<BlogPost> blogPost = repository.findById(blogPostId);
        assertEquals("title create", blogPost.get().getTitle());
        assertEquals("content create", blogPost.get().getContent());
    }

    @Test
    public void shouldListAllPosts() {
        HttpEntity<Void> entity = new HttpEntity<>(null);
        ResponseEntity<BlogPostListResponse> response = restTemplate.exchange("http://localhost:" + port + "/api/posts", GET, entity, BlogPostListResponse.class);

        assertEquals(200, response.getStatusCode().value());
        List<BlogPostResponse> responseList = response.getBody().getBlogPostList();
        BlogPostResponse blogPostResponse = responseList.getFirst();
        assertEquals("828409bd-4374-47b6-91d4-df1d163f5588", blogPostResponse.getId());
        assertEquals("controller 1", blogPostResponse.getTitle());
        assertEquals(2, blogPostResponse.getCommentsCount());
    }
}
