package kr.adapterz.community.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private List<Long> deletedImages;
    private List<String> addedImages;
}
