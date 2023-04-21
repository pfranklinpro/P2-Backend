package franklin.paul.sylvester.services;

import franklin.paul.sylvester.entities.Like;
import franklin.paul.sylvester.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepo;

    public LikeService(LikeRepository likeRepo) {
        this.likeRepo = likeRepo;
    }

    public void saveLikeByUserIdAndPostId(String userId, String postId, String username, String displayName) {
        likeRepo.save(UUID.randomUUID().toString(), userId, postId, username, displayName);
    }

    public void deleteLikeByUserIdAndPostId(String userId, String postId) {
        likeRepo.delete(userId, postId);
    }

    public List<Like> getAllLikesByUserId(String userId) {
        return likeRepo.findAllByUserId(userId);
    }

    public List<Like> getAllLikesByPostId(String postId) {
        return likeRepo.findAllByPostId(postId);
    }

    public List<String> getAllLikedPostIdsByUserId(String userId) {
        return likeRepo.findAllLikedPostIdsByUserId(userId);
    }

    public int getLikeCountByPostId(String postId) {
        return likeRepo.countAllLikesByPostId(postId);
    }

    public boolean isLiked(String userId, String postId) {
        Like validLike = likeRepo.findByUserIdAndPostId(userId, postId);

        return validLike != null;
    }
}
