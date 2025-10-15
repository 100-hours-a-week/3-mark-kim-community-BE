package kr.adapterz.community.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NicknameValidityCheckRequestDto {
    @NotBlank(message = "nickname is required.")
    private String nickname;
}
