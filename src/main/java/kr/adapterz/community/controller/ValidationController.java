package kr.adapterz.community.controller;

import jakarta.validation.Valid;
import kr.adapterz.community.dto.*;
import kr.adapterz.community.dto.validation.*;
import kr.adapterz.community.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    // 이메일 유효성 검증 작업을 처리하는 메서드
    @PostMapping("/email")
    public ResponseEntity<ApiResponseDto<EmailValidityCheckResponseDto>> emailValidityCheck(
            @RequestBody @Valid EmailValidityCheckRequestDto emailValidityCheckRequestDto) {

        EmailValidityCheckResponseDto emailValidityCheckResponseDto =
                validationService.emailValidityCheck(emailValidityCheckRequestDto.getEmail());

        ApiResponseDto<EmailValidityCheckResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(HttpStatus.OK.value());
        apiResponseDto.setPath(null);
        apiResponseDto.setData(emailValidityCheckResponseDto);

        // 유효성, 중복에 따라 메시지를 다르게 설정
        String message;
        if (!emailValidityCheckResponseDto.getValidity()) {
            message = "Invalid email format.";
        }
        else if (emailValidityCheckResponseDto.getIsDuplicated()) {
            message = "Duplicated email.";
        }
        else {
            message = "Email validity check passed.";
        }

        apiResponseDto.setMessage(message);

        return ResponseEntity.ok(apiResponseDto);
    }

    // 비밀번호 유효성 검증 작업을 처리하는 메서드
    @PostMapping("/password")
    public ResponseEntity<ApiResponseDto<PasswordValidityCheckResponseDto>> passwordValidityCheck(
            @RequestBody @Valid PasswordValidityCheckRequestDto passwordValidityCheckRequestDto
    ) {
        PasswordValidityCheckResponseDto passwordValidityCheckResponseDto =
                validationService.passwordValidityCheck(passwordValidityCheckRequestDto.getPassword());

        ApiResponseDto<PasswordValidityCheckResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(HttpStatus.OK.value());
        apiResponseDto.setPath(null);
        apiResponseDto.setData(passwordValidityCheckResponseDto);

        // 유효성에 따라 메시지를 다르게 설정
        String message;
        if (!passwordValidityCheckResponseDto.getValidity()) {
            message = "Invalid password.";
        }
        else {
            message = "Password validity check passed.";
        }
        apiResponseDto.setMessage(message);

        return ResponseEntity.ok(apiResponseDto);
    }

    // 닉네임 유효성 검증 작업을 처리하는 메서드
    @PostMapping("/nickname")
    public ResponseEntity<ApiResponseDto<NicknameValidityCheckResponseDto>> nicknameValidityCheck(
            @RequestBody @Valid NicknameValidityCheckRequestDto nicknameValidityCheckRequestDto
    ) {
        NicknameValidityCheckResponseDto nicknameValidityCheckResponseDto =
                validationService.nicknameValidityCheck(nicknameValidityCheckRequestDto.getNickname());

        ApiResponseDto<NicknameValidityCheckResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(HttpStatus.OK.value());
        apiResponseDto.setPath(null);
        apiResponseDto.setData(nicknameValidityCheckResponseDto);

        // 유효성에 따라 메시지를 다르게 설정
        String message;
        if (nicknameValidityCheckResponseDto.getHasSpace() ||
        nicknameValidityCheckResponseDto.getOverLimit()) {
            message = "Invalid nickname format.";
        }
        else if (nicknameValidityCheckResponseDto.getIsDuplicated()) {
            message = "Duplicated nickname.";
        }
        else {
            message = "Nickname validity check passed.";
        }
        apiResponseDto.setMessage(message);

        return ResponseEntity.ok(apiResponseDto);
    }

    @PostMapping("/password-check")
    public ResponseEntity<ApiResponseDto<PasswordDoubleCheckResponseDto>> passwordCheck(
            @RequestBody @Valid PasswordDoubleCheckRequestDto passwordDoubleCheckRequestDto
    ) {
        PasswordDoubleCheckResponseDto passwordDoubleCheckResponseDto = validationService.passwordDoubleCheck(passwordDoubleCheckRequestDto);
        ApiResponseDto<PasswordDoubleCheckResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(HttpStatus.OK.value());
        apiResponseDto.setPath(null);
        apiResponseDto.setData(passwordDoubleCheckResponseDto);

        String message;
        if (passwordDoubleCheckResponseDto.getIsEqual()) {
            message = "Password double check passed.";
        }
        else {
            message = "Password double check failed.";
        }
        apiResponseDto.setMessage(message);

        return ResponseEntity.ok(apiResponseDto);
    }
}
