package kr.adapterz.community.service;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.adapterz.community.dto.PostListRetrieveResponseDto;
import kr.adapterz.community.dto.PostOneInListDto;
import kr.adapterz.community.dto.PostUploadRequestDto;
import kr.adapterz.community.dto.PostUploadResponseDto;
import kr.adapterz.community.entity.*;
import kr.adapterz.community.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @PersistenceContext
    private EntityManager em;

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final PostLikeAndCommentCountRepository postLikeAndCommentCountRepository;
    private final PostViewCountRepository postViewCountRepository;
    private final PostLikeRepository postLikeRepository;

    private final UserRepository userRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public PostServiceImpl(
            PostRepository postRepository,
            PostImageRepository postImageRepository,
            PostLikeAndCommentCountRepository postLikeAndCommentCountRepository,
            PostViewCountRepository postViewCountRepository,
            PostLikeRepository postLikeRepository,
            UserRepository userRepository,
            JPAQueryFactory jpaQueryFactory
    ) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
        this.postLikeAndCommentCountRepository = postLikeAndCommentCountRepository;
        this.postViewCountRepository = postViewCountRepository;
        this.postLikeRepository = postLikeRepository;
        this.userRepository = userRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional
    public PostUploadResponseDto savePost(PostUploadRequestDto postUploadRequestDto) {
        // 해당 User 조회
        User user = userRepository.findById(postUploadRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("user not found"));

        // Post 엔티티 생성 및 영속화
        Post post = new Post(postUploadRequestDto.getTitle(), postUploadRequestDto.getContent(), user);
        postRepository.save(post);

        // 게시글에 포함된 이미지가 존재하는 경우 PostImage 엔티티 생성 및 영속화
        if (postUploadRequestDto.getImages() != null && !postUploadRequestDto.getImages().isEmpty()) {
            for (String url : postUploadRequestDto.getImages()) {
                PostImage postImage = new PostImage(url, post);
                postImageRepository.save(postImage);
            }
        }

        return new PostUploadResponseDto(new PostUploadResponseDto.Data(post.getId(), post.getTitle(), post.getCreatedAt()));
    }

    public PostListRetrieveResponseDto getPostList(Long lastFetchId, Integer limit) {
        // Q클래스
        QPost post = QPost.post;
        QUser user = QUser.user;
        QPostLikeAndCommentCount postLikeAndCommentCount = QPostLikeAndCommentCount.postLikeAndCommentCount;
        QPostViewCount postViewCount = QPostViewCount.postViewCount;

        // lastFetchId가 null인 경우 해당 조건을 제외
        BooleanExpression scrollingCondition = lastFetchId == null ? null : post.id.lt(lastFetchId);

        // 조인 후 조건에 맞는 게시글 정보 조회 및 DTO 매핑
        List<PostOneInListDto> posts =  jpaQueryFactory
                .select(Projections.constructor(
                        PostOneInListDto.class,
                        post.id,
                        user.nickname,
                        post.title,
                        postLikeAndCommentCount.likeCount.coalesce(0),
                        postLikeAndCommentCount.commentCount.coalesce(0),
                        postViewCount.viewCount.coalesce(0L),
                        post.createdAt
                ))
                .from(post)
                .leftJoin(post.user, user)
                .leftJoin(postLikeAndCommentCount).on(postLikeAndCommentCount.postId.eq(post.id))
                .leftJoin(postViewCount).on(postViewCount.postId.eq(post.id))
                .where(scrollingCondition)
                .orderBy(post.id.desc())
                .limit(limit)
                .fetch();

        return new PostListRetrieveResponseDto(new PostListRetrieveResponseDto.Data(posts));
    }
}
