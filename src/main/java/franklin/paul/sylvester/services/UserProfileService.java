package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewUserRequest;
import franklin.paul.sylvester.dtos.requests.UpdateProfileRequest;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.entities.UserProfile;
import franklin.paul.sylvester.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Service
@Transactional
public class UserProfileService {
    private final UserProfileRepository profileRepo;

    public UserProfileService(UserProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    public UserProfile createProfile(NewUserRequest req, User user) {
        UserProfile createdProfile = new UserProfile(UUID.randomUUID().toString(), req.getDisplayName(), null,
                req.getBirthDate(), null, null, null, user);

        profileRepo.save(createdProfile);
        return createdProfile;
    }

    public void updateProfile(UpdateProfileRequest req, String profileId) {
        profileRepo.update(req.getDisplayName(), req.getLocation(), req.getBirthDate(), req.getOccupation(),
                req.getBio(), req.getProfilePicUrl(), profileId);
    }

    public UserProfile getProfileByUserId(String userId) {
        return profileRepo.findByUserId(userId);
    }

    public boolean isEmptyDisplayName(String displayName) {
        return displayName.isEmpty();
    }

    public boolean isValidBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        return age > 13;
    }
}
