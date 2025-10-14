package kr.adapterz.community.service;

import kr.adapterz.community.dto.LoginRequestDto;
import kr.adapterz.community.dto.LoginResponseDto;

import java.util.Optional;

public interface AuthService {
    /*
        로그인 요청 시 호출
        유저의 인증 정보와 요청 바디의 email과 password를 비교
        일치할 시 응답 DTO 반환
     */
    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto);
}
