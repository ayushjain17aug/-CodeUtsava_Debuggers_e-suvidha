package codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data;


public class RatingData {
    RatingsReviews data;
    private boolean success;
    private String message;

    public RatingData(boolean success, String message, RatingsReviews data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }

    public RatingsReviews getData() {
        return data;
    }
}
