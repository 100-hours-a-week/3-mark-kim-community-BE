package kr.adapterz.community.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;
}
