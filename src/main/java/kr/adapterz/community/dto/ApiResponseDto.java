package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message;
    private T data;
}
