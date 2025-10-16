package kr.adapterz.community.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class PostListRetrieveResponseDto {

    private Data data;

    @Getter @Setter
    @AllArgsConstructor
    public static class Data {
        private List<PostOneInListDto> posts;
    }
}
