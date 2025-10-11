package kr.adapterz.community.service;

import kr.adapterz.community.dto.PostUploadRequestDto;
import kr.adapterz.community.dto.PostUploadResponseDto;

public interface PostService {

    /*
        게시글 생성 시 호출
        게시글 생성 요청 본문을 받아 Post 엔티티를 생성
        생성된 엔티티를 DB에 저장하고 저장된 데이터 정보를 반환
    */
    PostUploadResponseDto savePost(PostUploadRequestDto postUploadRequestDto);
}
