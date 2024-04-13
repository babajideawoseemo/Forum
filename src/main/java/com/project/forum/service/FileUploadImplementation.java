//package com.project.forum.service;
//
//import com.cloudinary.Cloudinary;
//import com.project.forum.domain.Post;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class FileUploadImplementation implements FileUpload {
//
//    private final PostServiceImpl postService;
//    private final Cloudinary cloudinary;
//
//
////    @Override
////    public String uploadFile(String id, MultipartFile multipartFile) throws IOException {
////        Post post = postService.getPost(id);
////        String cld = cloudinary.uploader()
////                .upload(multipartFile.getBytes(),
////                        Map.of("public_id",postService.getPost(id).toString()))
////                .get("url").toString();
////        post.setPhotoUrl(cld);
////        postRepository.save(post);
////    }
//
//}
