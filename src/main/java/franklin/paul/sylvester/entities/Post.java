package franklin.paul.sylvester.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    private String postId;

    @Column(name = "posted")
    private Date posted;

    @Column(name = "content")
    private String content;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference // child
    private User user;

    @Column(name = "username")
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post"
    )
    @JsonManagedReference // parent
    private List<Like> likes;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post"
    )
    @JsonManagedReference // parent
    private List<Reply> replies;

    public Post() {
        super();
    }

    public Post(String postId, Date posted, String content, String imgUrl, String username, String displayName) {
        this.postId = postId;
        this.posted = posted;
        this.content = content;
        this.imgUrl = imgUrl;
        this.username = username;
        this.displayName = displayName;
    }

    public Post(String postId, Date posted, String content, String imgUrl, User user, String username,
                String displayName) {
        this.postId = postId;
        this.posted = posted;
        this.content = content;
        this.imgUrl = imgUrl;
        this.user = user;
        this.username = username;
        this.displayName = displayName;
    }

    public Post(String postId, Date posted, String content, String imgUrl, User user, String username,
                String displayName, List<Like> likes, List<Reply> replies) {
        this.postId = postId;
        this.posted = posted;
        this.content = content;
        this.imgUrl = imgUrl;
        this.user = user;
        this.username = username;
        this.displayName = displayName;
        this.likes = likes;
        this.replies = replies;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Post{" +
                "postId='" + postId + '\'' +
                ", posted=" + posted +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", user=" + user +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", likes=" + likes +
                ", replies=" + replies +
                '}';
    }
}
