package codeutsava.app.codeutsava.com.codeutsava.Rating;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;

public interface RatingCallback {
    void onSuccess(RatingData body);

    void onFailure();
}
