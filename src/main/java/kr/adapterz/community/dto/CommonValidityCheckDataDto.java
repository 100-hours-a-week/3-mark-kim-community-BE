package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CommonValidityCheckDataDto {
    private Boolean validity;
    private Boolean isDuplicated;
}
