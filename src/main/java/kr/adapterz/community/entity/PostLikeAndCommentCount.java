package kr.adapterz.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "posts_like_comment_count")
@Getter @Setter
public class PostLikeAndCommentCount {

    @Id
    @Column(name = "post_id")
    private Long postId;

    @OneToOne()
    @MapsId()
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "comment_count", nullable = false)
    private Integer commentCount;

    protected PostLikeAndCommentCount() {}

    public PostLikeAndCommentCount(Post post) {
        this.post = post;
        likeCount = 0;
        commentCount = 0;
    }
}
