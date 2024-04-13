package com.project.forum.controller;

import com.project.forum.domain.Post;
import com.project.forum.service.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.project.forum.Constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.created(URI.create("/posts/userID")).body(postService.createPost(post));
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(postService.getPost(id));
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(postService.uploadPhoto(id, file));
    }

    @PutMapping("/photo/cloudinary")
    public ResponseEntity<String> uploadPicture(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(postService.uploadFile(id, file));
    }

    @GetMapping(path="/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE,IMAGE_GIF_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id) {
        return postService.findPostById(id)
                .map(post -> {
                    postService.deletePostById(id);
                    return ResponseEntity.ok(post);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Post> deletePostByComment(@PathVariable("id") String id,@PathVariable("comment") String comment) {
        return postService.findPostById(id)
                .map(post -> {
                    postService.deletePostByComment(comment);
                    return ResponseEntity.ok(post);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //**Add Comment to the user**//
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody @Valid Post post) {
        return postService.findPostById(id)
                .map(postComment -> {
                    postComment.setId(id);
                    postComment.setComment(post.getComment());
                    return ResponseEntity.ok(postService.updatePost(postComment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
