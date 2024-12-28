package com.prosig.blog.repository;

import com.prosig.blog.model.BlogPost;
import com.prosig.blog.repository.projection.BlogPostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {

    @Query(value = """
            SELECT bp.id as id, bp.title as title, 
                (SELECT COUNT(bc.id) 
                 FROM blog_comment bc 
                 WHERE bc.blog_post_id = bp.id
                ) as commentsCount
            FROM blog_post bp
            """, nativeQuery = true)
    List<BlogPostProjection> findPosts();

    @Query("SELECT bp FROM BlogPost bp LEFT JOIN FETCH bp.comments WHERE bp.id = :id ")
    BlogPost findByIdAndFetchComments(@Param("id") UUID id);
}
