package kr.adapterz.community.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "post_like_comment_count")
@Getter @Setter
public class PostLikeAndCommentCount {

    @Id
    @OneToOne()
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @JoinColumn(name = "like_count", nullable = false)
    private Integer likeCount;

    @JoinColumn(name = "comment_count", nullable = false)
    private Integer commentCount;

    protected PostLikeAndCommentCount() {}

    public PostLikeAndCommentCount(Post post) {
        this.post = post;
        likeCount = 0;
        commentCount = 0;
    }
}
