package codeutsava.app.codeutsava.com.codeutsava.Rating.Api;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RatingApi {

    @FormUrlEncoded
    @POST(Urls.GET_DETAILS)
    Call<RatingData> getRating(@Field("latitude") String lt, @Field("longitude") String lng);

    @Multipart
    @POST(Urls.POST_REVIEWS)
    Call<PostRatingData> postRating(@Part("latitude") String lt, @Part("longitude") String lng,
                                    @Part("overall") float overall, @Part("hygiene") float hygiene,
                                    @Part("infrastructure") float infra, @Part("safety") float safety,
                                    @Part("review") String review, @Part("feedback") String feedback,
                                    @Part MultipartBody.Part file, @Part("name") okhttp3.RequestBody name);

}
