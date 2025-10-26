package kr.adapterz.community.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class PostListRetrieveResponseDto {
    private List<PostOneInListDto> posts;
}
