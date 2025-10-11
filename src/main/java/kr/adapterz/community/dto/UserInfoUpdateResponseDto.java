package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class UserInfoUpdateResponseDto {
    private String nickname;
    private String profileImage;
    private LocalDateTime modifiedAt;
}
