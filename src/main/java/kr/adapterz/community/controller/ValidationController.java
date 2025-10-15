package kr.adapterz.community.controller;

import jakarta.validation.Valid;
import kr.adapterz.community.dto.*;
import kr.adapterz.community.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
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
        apiResponseDto.setData(emailValidityCheckResponseDto);

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
}
