package com.example.docmanagemntapi.controller;

import com.example.docmanagemntapi.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{postId}")
    public void createPostWithDocument(@PathVariable String postId, @RequestParam("documentFileName") String documentFileName) {
        postService.createPostWithDocument(postId, documentFileName);
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable String postId) {
       String result = postService.viewPost(postId);
       return result;
    }

    @PostMapping("/postIdComments")
    public void createPostWithDocument(@RequestParam("documentFileName") String documentFileName) {
        postService.postCommentsWithDocument(documentFileName);
    }

    @PostMapping(value = "/comments" )
    public void createProducts(@RequestParam String documentFileName) {
        postService.createComments(documentFileName);
    }

}
