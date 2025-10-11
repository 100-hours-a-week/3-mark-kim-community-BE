package kr.adapterz.community.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.adapterz.community.dto.PostUploadRequestDto;
import kr.adapterz.community.dto.PostUploadResponseDto;
import kr.adapterz.community.entity.Post;
import kr.adapterz.community.entity.PostImage;
import kr.adapterz.community.entity.User;
import kr.adapterz.community.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public PostServiceImpl(
            PostRepository postRepository,
            PostImageRepository postImageRepository,
            PostLikeAndCommentCountRepository postLikeAndCommentCountRepository,
            PostViewCountRepository postViewCountRepository,
            PostLikeRepository postLikeRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
        this.postLikeAndCommentCountRepository = postLikeAndCommentCountRepository;
        this.postViewCountRepository = postViewCountRepository;
        this.postLikeRepository = postLikeRepository;
        this.userRepository = userRepository;
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
}
