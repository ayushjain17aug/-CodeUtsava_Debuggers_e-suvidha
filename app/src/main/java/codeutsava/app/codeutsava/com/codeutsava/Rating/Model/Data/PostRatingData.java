package codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data;


public class PostRatingData {
    private String message;
    private boolean success;

    public PostRatingData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
