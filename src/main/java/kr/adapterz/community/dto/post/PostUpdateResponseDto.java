package kr.adapterz.community.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class PostUpdateResponseDto {
    private Data data;

    @Getter @Setter
    @AllArgsConstructor
    public static class Data {
        private String title;
        private String content;
        private List<String> images;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime modifiedAt;
    }
}
