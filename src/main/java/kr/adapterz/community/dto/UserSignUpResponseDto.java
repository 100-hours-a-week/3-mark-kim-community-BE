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
}
