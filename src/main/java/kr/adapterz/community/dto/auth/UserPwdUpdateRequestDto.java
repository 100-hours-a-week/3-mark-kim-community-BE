package kr.adapterz.community.dto.auth;

import lombok.Getter;

@Getter
public class UserPwdUpdateRequestDto {
    private Long userId; // 세션 정보를 가져오게 되면 불필요
    private String password;
}
