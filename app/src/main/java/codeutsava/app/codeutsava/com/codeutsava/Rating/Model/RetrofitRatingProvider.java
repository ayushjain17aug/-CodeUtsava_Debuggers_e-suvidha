package codeutsava.app.codeutsava.com.codeutsava.Rating.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Api.RatingApi;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.PostRatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.RatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRatingProvider implements RatingProvider {


    private final Retrofit retrofit;
    private RatingApi ratingApi;

    public RetrofitRatingProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                readTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.Base_Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


    @Override
    public void getRating(String lt, String lng, final RatingCallback callback) {
        ratingApi = retrofit.create(RatingApi.class);
        Call<RatingData> call = ratingApi.getRating(lt, lng);
        call.enqueue(new Callback<RatingData>() {

            @Override
            public void onResponse(Call<RatingData> call, Response<RatingData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RatingData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void postRating(String lt, String lng, float overall, float hygiene, float infra, float safety, String review,
                           String feedback, final PostRatingCallback callback) {
        ratingApi = retrofit.create(RatingApi.class);
        Call<PostRatingData> call = ratingApi.postRating(lt, lng, overall, hygiene, infra, safety, review, feedback);
        call.enqueue(new Callback<PostRatingData>() {
            @Override
            public void onResponse(Call<PostRatingData> call, Response<PostRatingData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PostRatingData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}