package franklin.paul.sylvester.repositories;

import franklin.paul.sylvester.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    User findByUserId(String user_id);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query(value = "SELECT (username) FROM users", nativeQuery = true)
    List<String> findAllUsernames();

    @Query(value = "SELECT (email) FROM users", nativeQuery = true)
    List<String> findAllEmails();
}
