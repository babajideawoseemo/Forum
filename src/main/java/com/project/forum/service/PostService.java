package com.project.forum.service;

import com.project.forum.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getPosts();

    Post createPost(Post post);

    Post getPost(String id);

    String uploadPhoto(String id, MultipartFile file);

    Optional<Post> findPostById(String id);

    void deletePostById(String id);

    void deletePostByComment(String comment);

    Post updatePost(Post post);

    String uploadFile(String id, MultipartFile multipartFile) throws IOException;
}
