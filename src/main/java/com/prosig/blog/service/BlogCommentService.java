package com.prosig.blog.service;

import com.prosig.blog.exception.NotFoundException;
import com.prosig.blog.model.BlogComment;
import com.prosig.blog.repository.BlogCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogCommentService {

    private final BlogPostService blogPostService;
    private final BlogCommentRepository repository;

    public BlogComment create(BlogComment blogComment) {
        UUID blogPostId = blogComment.getBlogPost().getId();
        if (!blogPostService.existsById(blogPostId)) {
            throw new NotFoundException("Blog post with id '%s' not found.".formatted(blogPostId));
        }
        return repository.save(blogComment);
    }
}
