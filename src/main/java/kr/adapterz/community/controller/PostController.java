package kr.adapterz.community.controller;

import kr.adapterz.community.dto.PostListRetrieveResponseDto;
import kr.adapterz.community.dto.PostUploadRequestDto;
import kr.adapterz.community.dto.PostUploadResponseDto;
import kr.adapterz.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 추가 작업을 처리하는 메서드
    @PostMapping
    public PostUploadResponseDto uploadPost(@RequestBody PostUploadRequestDto postUploadRequestDto) {
        return postService.savePost(postUploadRequestDto);
    }

    // 게시글 목록 조회 작업을 처리하는 메서드
    @GetMapping
    public PostListRetrieveResponseDto retrievePostList(
            @RequestParam(required = false) Long lastFetchId,
            @RequestParam Integer limit) {
        return postService.getPostList(lastFetchId, limit);
    }
}
