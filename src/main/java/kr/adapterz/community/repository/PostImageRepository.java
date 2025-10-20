package kr.adapterz.community.repository;

import kr.adapterz.community.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    // 해당 postId를 참조하는 PostImage를 찾고 imagePath를 List로 반환
    @Query("select pi.imagePath " +
            "from PostImage pi " +
            "where pi.post.id = :postId")
    Optional<List<String>> findImagesByPostId(Long postId);
}
