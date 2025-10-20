package kr.adapterz.community.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidityCheckResponseDto {
    private Boolean validity;
    private Boolean isDuplicated;
}
