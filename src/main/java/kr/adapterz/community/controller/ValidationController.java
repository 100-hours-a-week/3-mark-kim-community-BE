package kr.adapterz.community.controller;

import kr.adapterz.community.dto.ApiResponseDto;
import kr.adapterz.community.dto.CommonValidityCheckResponseDto;
import kr.adapterz.community.dto.EmailValidityCheckRequestDto;
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
    public ResponseEntity<ApiResponseDto<CommonValidityCheckResponseDto>> emailValidityCheck(
            @RequestBody EmailValidityCheckRequestDto emailValidityCheckRequestDto) {

        CommonValidityCheckResponseDto commonValidityCheckResponseDto =
                validationService.emailValidityCheck(emailValidityCheckRequestDto.getEmail());

        ApiResponseDto<CommonValidityCheckResponseDto> apiResponseDto = new ApiResponseDto<>();
        String message;

        if (!commonValidityCheckResponseDto.getValidity()) {
            message = "Invalid email.";
        }
        else if (commonValidityCheckResponseDto.getIsDuplicated()) {
            message = "Duplicated email.";
        }
        else {
            message = "Email validity check passed.";
        }

        apiResponseDto.setMessage(message);
        apiResponseDto.setData(commonValidityCheckResponseDto);

        return ResponseEntity.ok(apiResponseDto);
    }
}
