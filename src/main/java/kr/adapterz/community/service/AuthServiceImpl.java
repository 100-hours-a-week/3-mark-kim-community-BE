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

        Optional<UserAuth> userAuthOpt = userAuthRepository.findByEmail(loginRequestDto.getEmail());

        if (userAuthOpt.isEmpty()) {
            return Optional.empty();
        }

        UserAuth userAuth = userAuthOpt.get();

        if (!userAuth.getPassword().equals(loginRequestDto.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(new LoginResponseDto(userAuth.getUserId(), userAuth.getUser().getNickname()));
    }
}
