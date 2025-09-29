package kr.adapterz.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String nickname;
    @Column(nullable = true, length = 320)
    private String profileImage;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    protected User() {}

    public User(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = null;
    }
}
