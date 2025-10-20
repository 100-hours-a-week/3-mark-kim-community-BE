package kr.adapterz.community.dto.user;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String profileImage;
}
