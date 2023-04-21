package franklin.paul.sylvester.repositories;

import franklin.paul.sylvester.entities.UserProfile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {
    @Modifying
    @Query(value = "UPDATE user_profiles " +
            "SET display_name = ?1, location = ?2, birth_date = ?3, occupation = ?4, bio = ?5, profile_pic_url = ?6 " +
            "WHERE profile_id = ?7", nativeQuery = true)
    void update(String displayName, String location, LocalDate birthDate, String occupation, String bio,
                String profilePicUrl, String profileId);
    @Query(value = "SELECT * FROM user_profiles WHERE user_id = ?1", nativeQuery = true)
    UserProfile findByUserId(String userId);

    @Query(value = "SELECT (user_id) FROM user_profiles", nativeQuery = true)
    List<String> findAllUserIds();
}
