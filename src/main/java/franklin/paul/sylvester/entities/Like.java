package franklin.paul.sylvester.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    private String likeId;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference // child
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "post_id",
            nullable = false
    )
    @JsonBackReference // child
    private Post post;

    @Column(name = "username")
    private String username;

    @Column(name = "display_name")
    private String displayName;

    public Like() {
        super();
    }

    public Like(String likeId) {
        this.likeId = likeId;
    }

    public Like(String likeId, User user, Post post, String username, String displayName) {
        this.likeId = likeId;
        this.user = user;
        this.post = post;
        this.username = username;
        this.displayName = displayName;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId='" + likeId + '\'' +
                ", user=" + user +
                ", post=" + post +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
