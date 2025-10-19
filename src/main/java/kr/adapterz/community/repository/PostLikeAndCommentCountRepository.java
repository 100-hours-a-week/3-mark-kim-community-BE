package kr.adapterz.community.repository;

import kr.adapterz.community.entity.PostLikeAndCommentCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeAndCommentCountRepository extends JpaRepository<PostLikeAndCommentCount, Long> {
}
