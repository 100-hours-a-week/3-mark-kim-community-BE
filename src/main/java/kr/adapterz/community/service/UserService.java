package kr.adapterz.community.service;

import kr.adapterz.community.dto.*;

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
    UserInfoResponseDto getUserInfo(Long userId); // 세션 정보를 가져오게 되면 userId 파라미터 불필요

    /*
        회원정보 수정 시 호출
        해당 세션의 유저의 회원정보(닉네임, 프로필 사진)를 수정
        수정된 회원정보(닉네임, 프로필 사진, 수정 시각)를 반환
     */
    UserInfoUpdateResponseDto updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto);

    /*
        비밀번호 수정 시 호출
        해당 세션의 유저의 비밀번호를 수정
        수정 시각을 반환
     */
    UserPwdUpdateResponseDto updateUserPwd(UserPwdUpdateRequestDto userPwdUpdateRequestDto);
}
