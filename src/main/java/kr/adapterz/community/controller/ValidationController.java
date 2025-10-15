package kr.adapterz.community.controller;

import jakarta.validation.Valid;
import kr.adapterz.community.dto.ApiResponseDto;
import kr.adapterz.community.dto.CommonValidityCheckDataDto;
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
    public ResponseEntity<ApiResponseDto<CommonValidityCheckDataDto>> emailValidityCheck(
            @RequestBody @Valid EmailValidityCheckRequestDto emailValidityCheckRequestDto) {

        CommonValidityCheckResponseDto commonValidityCheckResponseDto =
                validationService.emailValidityCheck(emailValidityCheckRequestDto.getEmail());

        ApiResponseDto<CommonValidityCheckDataDto> apiResponseDto = new ApiResponseDto<>(
                commonValidityCheckResponseDto.getMessage(),
                commonValidityCheckResponseDto.getData()
        );

        return ResponseEntity.ok(apiResponseDto);
    }
}
