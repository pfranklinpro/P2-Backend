package franklin.paul.sylvester.dtos.requests;

import java.time.LocalDate;

public class NewUserRequest {
    private String username;
    private String password1;
    private String password2;
    private String email;
    private String displayName;
    private LocalDate birthDate;

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String username, String password1, String password2, String email, String displayName,
                          LocalDate birthDate) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
        this.displayName = displayName;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
