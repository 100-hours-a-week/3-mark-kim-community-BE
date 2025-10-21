package kr.adapterz.community.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordDoubleCheckResponseDto {
    private Boolean isEqual;
}
