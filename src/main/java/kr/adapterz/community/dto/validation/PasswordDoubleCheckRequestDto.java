package kr.adapterz.community.dto.validation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordDoubleCheckRequestDto {
    @NotBlank(message = "password is required.")
    private String password;

    @NotBlank(message = "password check is required.")
    private String passwordCheck;
}
