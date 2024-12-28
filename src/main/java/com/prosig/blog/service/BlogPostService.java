package com.prosig.blog.service;

import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.BlogPostRepository;
import com.prosig.blog.repository.projection.BlogPostProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository repository;

    public BlogPost create(BlogPost blogPost) {
        return repository.save(blogPost);
    }

    public List<BlogPostProjection> findAll() {
        return repository.findPosts();
    }

    public BlogPost findById(UUID id) {
        return repository.findByIdAndFetchComments(id);
    }
}
