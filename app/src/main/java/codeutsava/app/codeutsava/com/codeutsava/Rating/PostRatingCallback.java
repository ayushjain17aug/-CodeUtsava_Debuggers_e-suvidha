package codeutsava.app.codeutsava.com.codeutsava.Rating;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;

public interface PostRatingCallback {

    void onSuccess(PostRatingData body);

    void onFailure();
}
