package com.prosig.blog.service;

import com.prosig.blog.model.BlogComment;
import com.prosig.blog.repository.BlogCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogCommentService {

    private final BlogCommentRepository repository;

    public BlogComment create(BlogComment blogComment) {
        return repository.save(blogComment);
    }
}
