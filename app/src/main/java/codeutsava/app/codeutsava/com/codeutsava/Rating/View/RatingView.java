package codeutsava.app.codeutsava.com.codeutsava.Rating.View;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingsReviews;

public interface RatingView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showRatings(RatingsReviews data);
}
