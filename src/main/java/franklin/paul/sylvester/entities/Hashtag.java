package franklin.paul.sylvester.entities;

public class Hashtag {
    private String hashtagId;
    private String hashtag;
    private String postId;

    public Hashtag(String hashtagId, String hashtag, String postId) {
        this.hashtagId = hashtagId;
        this.hashtag = hashtag;
        this.postId = postId;
    }

    public String getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(String hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtag_id='" + hashtagId + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", post_id='" + postId + '\'' +
                '}';
    }
}
