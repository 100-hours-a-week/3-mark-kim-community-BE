package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class NicknameValidityCheckResponseDto {
    private Boolean hasSpace;
    private Boolean overLimit;
    private Boolean isDuplicated;
}
