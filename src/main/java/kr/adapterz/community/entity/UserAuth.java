package kr.adapterz.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "users_auth")
@Getter @Setter
public class UserAuth {

    @OneToOne()
    @MapsId()
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(length = 320, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;

    protected UserAuth() {}

    public UserAuth(User user, String email, String password) {
        this.user = user;
        this.email = email;
        this.password = password;
    }
}
