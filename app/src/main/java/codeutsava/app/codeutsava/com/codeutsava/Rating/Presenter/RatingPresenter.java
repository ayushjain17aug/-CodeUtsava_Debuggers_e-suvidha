package codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface RatingPresenter {
    void getRating(String lt, String lng);

    void postRating(String lt, String lng, float overall, float hygiene, float infra,
                    float safety, String review, String feedback, MultipartBody.Part file, RequestBody name);
}
