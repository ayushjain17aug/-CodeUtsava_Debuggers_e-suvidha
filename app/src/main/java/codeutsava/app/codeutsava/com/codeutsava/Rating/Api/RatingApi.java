package codeutsava.app.codeutsava.com.codeutsava.Rating.Api;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RatingApi {

    @FormUrlEncoded
    @POST(Urls.GET_DETAILS)
    Call<RatingData> getRating(@Field("latitude") String lt, @Field("longitude") String lng);

    @FormUrlEncoded
    @POST(Urls.POST_REVIEWS)
    Call<PostRatingData> postRating(@Field("latitude") String lt, @Field("longitude") String lng,
                                    @Field("overall") float overall, @Field("hygiene") float hygiene,
                                    @Field("infrastructure") float infra, @Field("safety") float safety,
                                    @Field("review") String review, @Field("feedback") String feedback);

}
