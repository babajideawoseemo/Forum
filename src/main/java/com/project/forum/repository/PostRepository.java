package com.project.forum.repository;

import com.project.forum.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    @Override
    Optional<Post> findById(String id);

    void deleteByComment(String comment);

}
