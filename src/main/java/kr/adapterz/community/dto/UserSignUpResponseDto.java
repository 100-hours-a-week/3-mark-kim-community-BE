package kr.adapterz.community.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class UserSignUpResponseDto {
    private Long userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;

    public UserSignUpResponseDto(Long userId, String email, String nickname, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}
