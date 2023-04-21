package franklin.paul.sylvester.dtos.requests;

public class NewPostRequest {
    private String content;
    private String imgUrl;

    public NewPostRequest() {
        super();
    }

    public NewPostRequest(String content, String imgUrl) {
        this.content = content;
        this.imgUrl = imgUrl;
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

    @Override
    public String toString() {
        return "NewPostRequest{" +
                "content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
