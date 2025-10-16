package kr.adapterz.community.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class PostUploadResponseDto {

    private Data data;

    @Getter @Setter
    @AllArgsConstructor
    public static class Data {
        private Long postId;
        private String title;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
    }
}
