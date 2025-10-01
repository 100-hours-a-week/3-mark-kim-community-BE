package kr.adapterz.community.service;

import kr.adapterz.community.dto.UserSignUpRequestDto;
import kr.adapterz.community.dto.UserSignUpResponseDto;

public interface UserService {
    /*
        회원가입 요청 시 호출
        회원가입 요청 본문을 받아 User 엔티티를 생성
        생성된 엔티티를 DB에 저장하고 저장된 데이터 정보를 반환
    */
    public UserSignUpResponseDto saveUser(UserSignUpRequestDto userSignUpRequestDto);
}
