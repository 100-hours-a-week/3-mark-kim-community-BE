package kr.adapterz.community.service;

import kr.adapterz.community.dto.LoginRequestDto;
import kr.adapterz.community.dto.LoginResponseDto;
import kr.adapterz.community.entity.UserAuth;
import kr.adapterz.community.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    public AuthServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) {

        // 해당 email을 가지는 유저 인증 정보 찾기
        Optional<UserAuth> userAuthOpt = userAuthRepository.findByEmail(loginRequestDto.getEmail());

        // 해당 email을 가지는 유저 인증 정보가 없을 경우, 빈 Optional 반환
        if (userAuthOpt.isEmpty()) {
            return Optional.empty();
        }

        UserAuth userAuth = userAuthOpt.get();

        // 해당 email을 가지는 유저 인증 정보의 password와 일치하지 않을 경우, 빈 Optional 반환
        if (!userAuth.getPassword().equals(loginRequestDto.getPassword())) {
            return Optional.empty();
        }

        // 로그인 성공한 유저의 아이디와 닉네임을 DTO에 매핑
        return Optional.of(new LoginResponseDto(userAuth.getUserId(), userAuth.getUser().getNickname()));
    }
}
