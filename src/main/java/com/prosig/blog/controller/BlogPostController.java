package com.prosig.blog.controller;

import com.prosig.blog.controller.resource.mapper.BlogCommentMapper;
import com.prosig.blog.controller.resource.mapper.BlogPostMapper;
import com.prosig.blog.controller.resource.request.BlogCommentRequest;
import com.prosig.blog.controller.resource.request.BlogPostRequest;
import com.prosig.blog.controller.resource.response.BlogPostListResponse;
import com.prosig.blog.controller.resource.response.BlogPostResponse;
import com.prosig.blog.controller.resource.response.IdResponse;
import com.prosig.blog.model.BlogComment;
import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.projection.BlogPostProjection;
import com.prosig.blog.service.BlogCommentService;
import com.prosig.blog.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final BlogCommentService blogCommentService;
    private final BlogPostMapper blogPostMapper;
    private final BlogCommentMapper blogCommentMapper;

    @GetMapping
    public ResponseEntity<BlogPostListResponse> listPosts() {
        List<BlogPostProjection> blogPostList = blogPostService.findAll();
        return ResponseEntity.ok(blogPostMapper.mapToResponseList(blogPostList));
    }

    @PostMapping
    public ResponseEntity<IdResponse> createPost(@RequestBody BlogPostRequest request) {
        BlogPost blogPost = blogPostService.create(blogPostMapper.mapFromRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.builder().id(blogPost.getId().toString()).build());
    }

    @GetMapping("{postId}")
    public ResponseEntity<BlogPostResponse> getPost(@PathVariable("postId") UUID postId) {
        BlogPost blogPost = blogPostService.findById(postId);
        return ResponseEntity.ok(blogPostMapper.mapToResponse(blogPost));
    }

    @PostMapping("{postId}/comments")
    public ResponseEntity<IdResponse> createComment(@PathVariable("postId") UUID postId, @RequestBody BlogCommentRequest request) {
        BlogComment blogComment = blogCommentService.create(blogCommentMapper.mapFromRequest(request, postId));
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.builder().id(blogComment.getId().toString()).build());
    }
}
