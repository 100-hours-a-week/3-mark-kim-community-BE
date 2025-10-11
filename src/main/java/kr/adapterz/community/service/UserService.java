package kr.adapterz.community.service;

import kr.adapterz.community.dto.UserInfoResponseDto;
import kr.adapterz.community.dto.UserSignUpRequestDto;
import kr.adapterz.community.dto.UserSignUpResponseDto;

public interface UserService {
    /*
        회원가입 요청 시 호출
        회원가입 요청 본문을 받아 User 엔티티를 생성
        생성된 엔티티를 DB에 저장하고 저장된 데이터 정보를 반환
    */
    UserSignUpResponseDto saveUser(UserSignUpRequestDto userSignUpRequestDto);

    /*
        회원정보 수정 시 호출
        해당 세션의 유저의 회원정보(이메일, 닉네임, 프로필 사진)를 반환
    */
    UserInfoResponseDto getUserInfo(Integer userId); // 세션 정보를 가져오게 되면 userId 파라미터 불필요
}
