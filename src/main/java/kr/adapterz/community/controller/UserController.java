package kr.adapterz.community.controller;

import kr.adapterz.community.dto.ApiResponseDto;
import kr.adapterz.community.dto.auth.UserPwdUpdateRequestDto;
import kr.adapterz.community.dto.auth.UserPwdUpdateResponseDto;
import kr.adapterz.community.dto.user.*;
import kr.adapterz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 요청을 처리하는 메서드
    @PostMapping
    public ResponseEntity<ApiResponseDto<UserSignUpResponseDto>> register(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {

        ApiResponseDto<UserSignUpResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.CREATED.value(),
                "Registration completed successfully.",
                null,
                userService.saveUser(userSignUpRequestDto)
        );

        return ResponseEntity.created(URI.create("/users")).body(apiResponseDto);
    }

    // 회원정보 수정 시 필요한 기존 회원정보 요청을 처리하는 메서드
    @GetMapping
    public ResponseEntity<ApiResponseDto<UserInfoResponseDto>> getUserInfo(@RequestParam Long userId) { // 세션 정보를 가져오게 되면 userId 파라미터 불필요

        ApiResponseDto<UserInfoResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "User information successfully retrieved.",
                null,
                userService.getUserInfo(userId)
        );

        return ResponseEntity.ok(apiResponseDto);
    }

    // 회원정보 수정 작업을 처리하는 메서드
    @PatchMapping
    public ResponseEntity<ApiResponseDto<UserInfoUpdateResponseDto>> patchUserInfo(@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        ApiResponseDto<UserInfoUpdateResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "User information was successfully modified.",
                null,
                userService.updateUserInfo(userInfoUpdateRequestDto)
        );

        return ResponseEntity.ok(apiResponseDto);
    }

    // 비밀번호 변경 작업을 처리하는 메서드
    @PatchMapping("/password")
    public ResponseEntity<ApiResponseDto<UserPwdUpdateResponseDto>> patchUserPwd(@RequestBody UserPwdUpdateRequestDto userPwdUpdateRequestDto) {
        ApiResponseDto<UserPwdUpdateResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "New password has been set.",
                null,
                userService.updateUserPwd(userPwdUpdateRequestDto)
        );

        return ResponseEntity.ok(apiResponseDto);
    }

    // 회원 탈퇴 요청 작업을 처리하는 메서드
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@RequestParam Long userId) { // 세션 정보를 가져오게 되면 userId 파라미터 불필요
        userService.deleteUser(userId);

        ApiResponseDto<UserPwdUpdateResponseDto> apiResponseDto = new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "Account has been successfully deleted.",
                null,
                null
        );

        return ResponseEntity.ok(apiResponseDto);
    }
}
