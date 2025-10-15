package kr.adapterz.community.service;

import kr.adapterz.community.dto.EmailValidityCheckResponseDto;
import kr.adapterz.community.entity.UserAuth;
import kr.adapterz.community.repository.UserAuthRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationServiceImpl implements ValidationService {
    private final UserAuthRepository userAuthRepository;

    @Autowired
    public ValidationServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public EmailValidityCheckResponseDto emailValidityCheck(String email) {

        // 이메일 유효성 검증
        if (!EmailValidator.getInstance().isValid(email)) {
            return new EmailValidityCheckResponseDto(false, false);
        }

        // 해당 이메일에 대응하는 인증 정보를 조회
        Optional<UserAuth> userAuthOpt = userAuthRepository.findByEmail(email);

        // 이메일이 인증 정보에 존재하는 경우
        if (userAuthOpt.isPresent()) {
            return new EmailValidityCheckResponseDto(true, true);
        }

        // 이메일이 인증 정보에 존재하지 않는 경우
        return new EmailValidityCheckResponseDto(true, false);
    }
}
