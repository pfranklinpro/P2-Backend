package franklin.paul.sylvester.dtos.requests;

public class NewReplyRequest {
    private String reply;
    private String postId;
    private String imgUrl;

    public NewReplyRequest(String reply, String postId, String imgUrl) {
        this.reply = reply;
        this.postId = postId;
        this.imgUrl = imgUrl;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "NewReplyRequest{" +
                "reply='" + reply + '\'' +
                ", postId='" + postId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
