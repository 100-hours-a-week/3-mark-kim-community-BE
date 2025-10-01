package kr.adapterz.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity(name = "post_images")
@Getter @Setter
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 320, nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    protected PostImage() {}

    public PostImage(String imagePath, Post post) {
        this.imagePath = imagePath;
        this.createdAt = LocalDateTime.now();
        this.post = post;
    }
}
