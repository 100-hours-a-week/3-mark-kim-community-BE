package kr.adapterz.community.dto;

import lombok.Getter;

@Getter
public class UserInfoUpdateRequestDto {
    private Long userId; // 세션 정보를 가져오게 되면 불필요
    private String nickname;
    private String profileImage;
}
