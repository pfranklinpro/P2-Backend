package franklin.paul.sylvester.entities;

import java.util.Date;

public class FollowingRelationship {
    private String relationshipId;
    private Date followed;
    private String userId;
    private String followingId;

    public FollowingRelationship(String relationshipId, Date followed, String userId, String followingId) {
        this.relationshipId = relationshipId;
        this.followed = followed;
        this.userId = userId;
        this.followingId = followingId;
    }

    public String getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Date getFollowed() {
        return followed;
    }

    public void setFollowed(Date followed) {
        this.followed = followed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }

    @Override
    public String toString() {
        return "FollowingRelationship{" +
                "relationshipId='" + relationshipId + '\'' +
                ", followed=" + followed +
                ", userId='" + userId + '\'' +
                ", followingId='" + followingId + '\'' +
                '}';
    }
}
