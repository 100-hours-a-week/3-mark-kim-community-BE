package kr.adapterz.community.controller;

import kr.adapterz.community.dto.ApiResponseDto;
import kr.adapterz.community.dto.LoginRequestDto;
import kr.adapterz.community.dto.LoginResponseDto;
import kr.adapterz.community.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 로그인 요청 작업을 처리하는 메서드
    @PostMapping
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {

        Optional<LoginResponseDto> loginResponseDtoOpt = authService.login(loginRequestDto);

        if (loginResponseDtoOpt.isEmpty()) {
            ApiResponseDto<LoginResponseDto> apiResponseDto = new ApiResponseDto<>(
                    "Login failed",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseDto);
        }

        ApiResponseDto<LoginResponseDto> apiResponseDto = new ApiResponseDto<>(
                "Login success.",
                loginResponseDtoOpt.get()
        );

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
    }

}
