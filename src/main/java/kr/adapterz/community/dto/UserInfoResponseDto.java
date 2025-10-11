package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserInfoResponseDto {
    private String email;
    private String nickname;
    private String profileImage;
}
