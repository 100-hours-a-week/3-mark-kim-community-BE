package kr.adapterz.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private Integer code;
    private String message;
    private String path;
    private T data;
}
