package franklin.paul.sylvester.dtos.requests;

import java.time.LocalDate;

public class UpdateProfileRequest {
    private String displayName;
    private String location;
    private LocalDate birthDate;
    private String occupation;
    private String bio;
    private String profilePicUrl;

    public UpdateProfileRequest() {
        super();
    }

    public UpdateProfileRequest(String displayName, String location, LocalDate birthDate, String occupation,
                                String bio, String profilePicUrl) {
        this.displayName = displayName;
        this.location = location;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
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

    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "displayName='" + displayName + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                ", occupation='" + occupation + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}
