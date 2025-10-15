package kr.adapterz.community.service;

import kr.adapterz.community.dto.EmailValidityCheckResponseDto;
import kr.adapterz.community.dto.PasswordValidityCheckResponseDto;

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
}
