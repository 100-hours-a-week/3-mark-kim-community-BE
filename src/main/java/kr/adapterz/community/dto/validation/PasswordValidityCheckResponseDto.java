package kr.adapterz.community.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PasswordValidityCheckResponseDto {
    private Boolean validity;
}
