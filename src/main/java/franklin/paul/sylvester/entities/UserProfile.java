package franklin.paul.sylvester.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private String profileId;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "location")
    private String location;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference // child
    private User user;

    public UserProfile() {
        super();
    }

    public UserProfile(String profileId, String displayName, String location, LocalDate birthDate, String occupation,
                       String bio, String profilePicUrl) {
        this.profileId = profileId;
        this.displayName = displayName;
        this.location = location;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
    }

    public UserProfile(String profileId, String displayName, String location, LocalDate birthDate, String occupation,
                       String bio, String profilePicUrl, User user) {
        this.profileId = profileId;
        this.displayName = displayName;
        this.location = location;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
        this.user = user;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileId='" + profileId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                ", occupation='" + occupation + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
