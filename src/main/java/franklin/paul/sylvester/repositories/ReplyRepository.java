package franklin.paul.sylvester.repositories;

import franklin.paul.sylvester.entities.Reply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReplyRepository extends CrudRepository<Reply, String> {
    @Modifying
    @Query(value = "INSERT INTO replies(reply_id, reply, replied, img_url, user_id, post_id, username, display_name) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void save(String replyId, String reply, Date replied, String imgUrl, String userId, String postId, String username,
              String displayName);

    @Query(value = "SELECT * FROM replies WHERE post_id = ?1 ORDER BY replied ASC", nativeQuery = true)
    List<Reply> findAllByPostId(String postId);
}
