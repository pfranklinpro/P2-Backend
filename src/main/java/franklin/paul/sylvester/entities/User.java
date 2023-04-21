package franklin.paul.sylvester.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "registered", nullable = false)
    private Date registered;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "role_id")
    private String roleId;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference // parent
    private UserProfile profile;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference // parent
    private List<Post> posts;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference // parent
    private List<Like> likes;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonManagedReference // parent
    private List<Reply> replies;

    public User() {
        super();
    }

    public User(String userId, String username, String password, String email, Date registered, boolean isActive,
                String roleId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registered = registered;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public User(String userId, String username, String password, String email, Date registered, boolean isActive,
                String roleId, UserProfile profile, List<Post> posts, List<Like> likes, List<Reply> replies) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registered = registered;
        this.isActive = isActive;
        this.roleId = roleId;
        this.profile = profile;
        this.posts = posts;
        this.likes = likes;
        this.replies = replies;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registered=" + registered +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                ", profile=" + profile +
                ", posts=" + posts +
                ", likes=" + likes +
                ", replies=" + replies +
                '}';
    }
}
