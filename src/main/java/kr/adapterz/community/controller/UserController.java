package kr.adapterz.community.controller;

import kr.adapterz.community.dto.auth.UserPwdUpdateRequestDto;
import kr.adapterz.community.dto.auth.UserPwdUpdateResponseDto;
import kr.adapterz.community.dto.user.*;
import kr.adapterz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public UserSignUpResponseDto register(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        return userService.saveUser(userSignUpRequestDto);
    }

    // 회원정보 수정 시 필요한 기존 회원정보 요청을 처리하는 메서드
    @GetMapping
    public UserInfoResponseDto getUserInfo(@RequestParam Long userId) { // 세션 정보를 가져오게 되면 userId 파라미터 불필요
        return userService.getUserInfo(userId);
    }

    // 회원정보 수정 작업을 처리하는 메서드
    @PatchMapping
    public UserInfoUpdateResponseDto patchUserInfo(@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        return userService.updateUserInfo(userInfoUpdateRequestDto);
    }

    // 비밀번호 변경 작업을 처리하는 메서드
    @PatchMapping("/password")
    public UserPwdUpdateResponseDto patchUserPwd(@RequestBody UserPwdUpdateRequestDto userPwdUpdateRequestDto) {
        return userService.updateUserPwd(userPwdUpdateRequestDto);
    }

    // 회원 탈퇴 요청 작업을 처리하는 메서드
    @DeleteMapping
    public void deleteUser(@RequestParam Long userId) { // 세션 정보를 가져오게 되면 userId 파라미터 불필요
        userService.deleteUser(userId);
    }
}
