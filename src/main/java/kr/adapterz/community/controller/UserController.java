package kr.adapterz.community.controller;

import kr.adapterz.community.dto.UserSignUpRequestDto;
import kr.adapterz.community.dto.UserSignUpResponseDto;
import kr.adapterz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserSignUpResponseDto register(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        return userService.saveUser(userSignUpRequestDto);
    }
}
