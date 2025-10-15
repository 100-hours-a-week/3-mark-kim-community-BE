package kr.adapterz.community.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordValidityCheckRequestDto {
    @NotBlank(message = "password is required.")
    private String password;
}
