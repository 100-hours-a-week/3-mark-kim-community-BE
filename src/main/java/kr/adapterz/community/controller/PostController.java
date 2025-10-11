package kr.adapterz.community.controller;

import kr.adapterz.community.dto.PostUploadRequestDto;
import kr.adapterz.community.dto.PostUploadResponseDto;
import kr.adapterz.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostUploadResponseDto post(@RequestBody PostUploadRequestDto postUploadRequestDto) {
        return postService.savePost(postUploadRequestDto);
    }
}
