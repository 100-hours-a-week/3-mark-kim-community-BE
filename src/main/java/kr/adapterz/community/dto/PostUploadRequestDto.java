package kr.adapterz.community.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostUploadRequestDto {
    private String title;
    private String content;
    private List<String> images;
    private Long userId; // 세션 정보를 가져오게 되면 불필요
}
