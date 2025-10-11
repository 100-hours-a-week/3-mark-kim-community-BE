package kr.adapterz.community.controller;

import kr.adapterz.community.dto.*;
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
    public UserInfoResponseDto getUserInfo(@RequestParam Long userId) {
        return userService.getUserInfo(userId);
    }

    @PatchMapping
    public UserInfoUpdateResponseDto patchUserInfo(@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        return userService.updateUserInfo(userInfoUpdateRequestDto);
    }
}
