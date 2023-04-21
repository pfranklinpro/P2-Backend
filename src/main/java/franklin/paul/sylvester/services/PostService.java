package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewPostRequest;
import franklin.paul.sylvester.entities.Post;
import franklin.paul.sylvester.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepo;

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public void savePostByUserId(NewPostRequest req, String userId, String username, String displayName) {
        postRepo.save(UUID.randomUUID().toString(), new Date(), req.getContent(),
                req.getImgUrl(), userId, username, displayName);
    }

    public List<Post> getAllPosts() {
        return (List <Post>) postRepo.findAll();
    }

    public Post getPostByPostId(String postId) {
        return postRepo.findByPostId(postId);
    }

    public List<Post> getAllPostsByUserId(String userId) {
        return postRepo.findAllByUserId(userId);
    }

    public List<Post> getLikedPostsByUserLikedPostIds(List<String> userLikedPostIds) {
        Iterable<Post> posts = postRepo.findAll();

        List<Post> filteredPosts = new ArrayList<>();
        for(Post post : posts) {
            if(userLikedPostIds.contains(post.getPostId())) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    public List<Post> filterPostsByPosted(int limit) {
        return postRepo.filterByPosted(limit);
    }

    public boolean isValidContent(String content) {
        return content.length() <= 128;
    }

    public boolean isValidPostId(String postId) {
        return postRepo.findByPostId(postId) != null;
    }
}
