package franklin.paul.sylvester.repositories;

import franklin.paul.sylvester.entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {
    @Modifying
    @Query(value = "INSERT INTO posts(post_id, posted, content, img_url, user_id, username, display_name) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    void save(String postId, Date posted, String content, String imgUrl, String userId, String username,
              String displayName);

    @Modifying
    @Query(value = "SELECT * FROM posts ORDER BY posted DESC", nativeQuery = true)
    List<Post> findAll();

    @Query(value = "SELECT * FROM posts WHERE post_id = ?1", nativeQuery = true)
    Post findByPostId(String postId);

    @Query(value = "SELECT * FROM posts WHERE user_id = ?1 ORDER BY posted DESC", nativeQuery = true)
    List<Post> findAllByUserId(String userId);

    @Query(value = "SELECT * FROM posts ORDER BY posted DESC LIMIT ?1", nativeQuery = true)
    List<Post> filterByPosted(int limit);
}
