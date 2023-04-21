package franklin.paul.sylvester.entities;

import java.util.Date;

public class Repost {
    private Date reposted;
    private String repostId;
    private String userId;
    private String postId;

    public Repost(Date reposted, String repostId, String userId, String postId) {
        this.reposted = reposted;
        this.repostId = repostId;
        this.userId = userId;
        this.postId = postId;
    }

    public Date getReposted() {
        return reposted;
    }

    public void setReposted(Date reposted) {
        this.reposted = reposted;
    }

    public String getRepostId() {
        return repostId;
    }

    public void setRepostId(String repostId) {
        this.repostId = repostId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Repost{" +
                "reposted=" + reposted +
                ", repostId='" + repostId + '\'' +
                ", userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
