package kr.adapterz.community.dto.validation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailValidityCheckRequestDto {
    @NotBlank(message = "Email is required.")
    private String email;
}
