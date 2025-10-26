package kr.adapterz.community.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginResponseDto {
    private Long userId;
    private String nickname;
    private String profileImage;
}
