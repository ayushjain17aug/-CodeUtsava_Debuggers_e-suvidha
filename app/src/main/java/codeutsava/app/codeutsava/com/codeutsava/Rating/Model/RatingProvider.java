package codeutsava.app.codeutsava.com.codeutsava.Rating.Model;

import codeutsava.app.codeutsava.com.codeutsava.Rating.PostRatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.RatingCallback;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface RatingProvider {

    void getRating(String lt, String lng, RatingCallback callback);

    void postRating(String lt, String lng, float overall, float hygiene, float infra, float safety, String review, String feedback, MultipartBody.Part file, RequestBody name, PostRatingCallback callback);
}
