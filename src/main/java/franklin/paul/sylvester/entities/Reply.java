package franklin.paul.sylvester.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    private String replyId;

    @Column(name = "reply", nullable = false)
    private String reply;

    @Column(name = "replied", nullable = false)
    private Date replied;

    @Column(name = "img_url")
    private String imgUrl;

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

    public Reply() {
    }

    public Reply(String replyId, String reply, Date replied, String imgUrl, String username, String displayName) {
        this.replyId = replyId;
        this.reply = reply;
        this.replied = replied;
        this.imgUrl = imgUrl;
        this.username = username;
        this.displayName = displayName;
    }

    public Reply(String replyId, String reply, Date replied, String imgUrl, User user, Post post, String username,
                 String displayName) {
        this.replyId = replyId;
        this.reply = reply;
        this.replied = replied;
        this.imgUrl = imgUrl;
        this.user = user;
        this.post = post;
        this.username = username;
        this.displayName = displayName;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getReplied() {
        return replied;
    }

    public void setReplied(Date replied) {
        this.replied = replied;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId='" + replyId + '\'' +
                ", reply='" + reply + '\'' +
                ", replied=" + replied +
                ", imgUrl='" + imgUrl + '\'' +
                ", user=" + user +
                ", post=" + post +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
