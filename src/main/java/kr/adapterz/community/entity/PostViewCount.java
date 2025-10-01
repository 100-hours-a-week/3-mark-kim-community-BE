package kr.adapterz.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "posts_view_count")
@Getter @Setter
public class PostViewCount {

    @Id
    @OneToOne()
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Column(name = "view_count")
    private Long viewCount;

    protected PostViewCount() {}

    public PostViewCount(Post post) {
        this.post = post;
        this.viewCount = 0L;
    }
}
