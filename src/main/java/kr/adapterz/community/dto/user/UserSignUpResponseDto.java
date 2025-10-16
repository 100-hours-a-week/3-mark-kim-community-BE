package kr.adapterz.community.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class UserSignUpResponseDto {
    private Long userId;
    private String email;
    private String nickname;
    
    // 추후 data 필드로 분리

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public UserSignUpResponseDto(Long userId, String email, String nickname, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}
