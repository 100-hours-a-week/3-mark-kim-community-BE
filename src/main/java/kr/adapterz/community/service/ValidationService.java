package kr.adapterz.community.service;

import kr.adapterz.community.dto.validation.*;

public interface ValidationService {
    /*
        이메일 유효성 검증 시 호출
        이메일의 중복, 형식 등을 확인
        중복 여부, 유효성을 DTO에 매핑해 반환
     */
    EmailValidityCheckResponseDto emailValidityCheck(String email);

    /*
        비밀번호 유효성 검증 시 호출
        비밀번호가 형식에 맞는지 확인
        유효성을 DTO에 매핑해 반환
     */
    PasswordValidityCheckResponseDto passwordValidityCheck(String password);

    /*
        닉네임 유효성 검증 시 호출
        닉네임의 중복, 형식 등을 확인
        띄워쓰기 포함 여부, 글자 수 초과 여부, 중복 여부를 DTO에 매핑해 반환
     */
    NicknameValidityCheckResponseDto nicknameValidityCheck(String nickname);

    /*
        비밀번호 확인 시 호출
        비밀번호와 재입력한 비밀번호가 일치하는지 확인
        일치 여부를 DTO에 매핑해 반환
     */
    PasswordDoubleCheckResponseDto passwordDoubleCheck(PasswordDoubleCheckRequestDto passwordDoubleCheckRequestDto);
}
