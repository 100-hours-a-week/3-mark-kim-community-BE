package kr.adapterz.community.repository;

import kr.adapterz.community.dto.user.UserInfoResponseDto;
import kr.adapterz.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 아이디로 회원정보를 찾고 DTO에 매핑해서 반환 (회원정보 수정 전)
    @Query("select new kr.adapterz.community.dto.user.UserInfoResponseDto(ua.email, u.nickname, u.profileImage) " +
            "from User u join UserAuth ua on u = ua.user " +
            "where u.id = :userId")
    Optional<UserInfoResponseDto> findUserInfoById(Long userId);

    Boolean existsByNickname(String nickname);
}
