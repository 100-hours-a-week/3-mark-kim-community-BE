package kr.adapterz.community.controller;

import kr.adapterz.community.dto.ApiResponseDto;
import kr.adapterz.community.dto.post.*;
import kr.adapterz.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 추가 작업을 처리하는 메서드
    @PostMapping
    public ResponseEntity<ApiResponseDto<PostUploadResponseDto>> uploadPost(@RequestBody PostUploadRequestDto postUploadRequestDto) {
        PostUploadResponseDto postUploadResponseDto = postService.savePost(postUploadRequestDto);

        ApiResponseDto<PostUploadResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(HttpStatus.OK.value());
        apiResponseDto.setMessage("The post was uploaded successfully.");
        apiResponseDto.setPath(String.format("/posts/%s", postUploadResponseDto.getPostId()));
        apiResponseDto.setData(postUploadResponseDto);

        return ResponseEntity.ok(apiResponseDto);
    }

    // 게시글 목록 조회 작업을 처리하는 메서드
    @GetMapping
    public ResponseEntity<ApiResponseDto<PostListRetrieveResponseDto>> retrievePostList(
            @RequestParam(required = false) Long lastFetchId,
            @RequestParam Integer limit) {
        PostListRetrieveResponseDto postListRetrieveResponseDto = postService.getPostList(lastFetchId, limit);

        ApiResponseDto<PostListRetrieveResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "Posts were retrieved successfully",
                null,
                postListRetrieveResponseDto
        );

        return ResponseEntity.ok(apiResponseDto);
    }

    // 게시글 상세 조회 작업을 처리하는 메서드
    @GetMapping("/{postId}")
    public PostDetailRetrieveResponseDto retrievePostDetail(
            @PathVariable("postId") Long postId,
            @RequestParam(required = false) Long userId
    ) {
        return postService.getPostDetail(postId, userId);
    }

    // 게시글 수정 작업을 처리하는 메서드
    @PutMapping("/{postId}")
    public PostUpdateResponseDto editPost(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequestDto postUpdateRequestDto
    ) {
        return postService.updatePost(postId, postUpdateRequestDto);
    }

    // 게시글 삭제 작업을 처리하는 메서드
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
