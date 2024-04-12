package com.project.forum.service;

import com.project.forum.domain.Post;
import com.project.forum.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.project.forum.Constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPost(String id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public String uploadPhoto(String id, MultipartFile file){
        log.info("Saving picture for user ID: {}", id);
        Post post = getPost(id);
        String photoUrl = photoFunction.apply(id, file);
        post.setPhotoUrl(photoUrl);
        postRepository.save(post);
        return photoUrl;
    }

    private final Function<String,String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".")+ 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(id + fileExtension.apply(image.getOriginalFilename())), REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/contacts/image/" + filename).toUriString();
        }catch(Exception e) {
            throw new RuntimeException("Unable to save image");
        }
    };

    public Optional<Post> findPostById(String id) {
        return postRepository.findById(id);
    }
    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }

    public void deletePostByComment(String comment) {
        postRepository.deleteByComment(comment);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }
}
