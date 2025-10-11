package kr.adapterz.community.service;

import kr.adapterz.community.dto.UserInfoResponseDto;
import kr.adapterz.community.dto.UserSignUpRequestDto;
import kr.adapterz.community.dto.UserSignUpResponseDto;
import kr.adapterz.community.entity.User;
import kr.adapterz.community.entity.UserAuth;
import kr.adapterz.community.repository.UserAuthRepository;
import kr.adapterz.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,  UserAuthRepository userAuthRepository) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
    }

    @Transactional
    public UserSignUpResponseDto saveUser(UserSignUpRequestDto userSignUpRequestDto) {
        // User 엔티티 생성 및 영속화
        User user = new User(
                userSignUpRequestDto.getNickname(),
                userSignUpRequestDto.getProfileImage()
                );
        User savedUser = userRepository.save(user);

        // UserAuth 엔티티 생성 및 영속화
        UserAuth userAuth = new UserAuth(
                user,
                userSignUpRequestDto.getEmail(),
                userSignUpRequestDto.getPassword() // Bcrypt 암호화 방법에 대해 학습하고 수정
        );
        UserAuth savedUserAuth = userAuthRepository.save(userAuth);

        // 응답 DTO에 담아서 반환
        return new UserSignUpResponseDto(
                savedUser.getId(),
                savedUserAuth.getEmail(),
                savedUser.getNickname(),
                savedUser.getCreatedAt()
        );
    }

    public UserInfoResponseDto getUserInfo(Integer userId) { // 세션 정보를 가져오게 되면 userId 파라미터 불필요

        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }
}
