package codeutsava.app.codeutsava.com.codeutsava.Maps.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Api.PositionApi;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;
import codeutsava.app.codeutsava.com.codeutsava.Maps.PositionCallback;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPositionProvider implements PositionProvider {
    private final Retrofit retrofit;
    private PositionApi positionApi;

    public RetrofitPositionProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(12000, java.util.concurrent.TimeUnit.SECONDS).
                readTimeout(12000, java.util.concurrent.TimeUnit.SECONDS).
                writeTimeout(12000, java.util.concurrent.TimeUnit.SECONDS).
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
    public void getPosition(Double lat, Double lon, final PositionCallback callback) {
        positionApi = retrofit.create(PositionApi.class);
        Call<PositionData> call = positionApi.getPosition(lat, lon);
        call.enqueue(new Callback<PositionData>() {

            @Override
            public void onResponse(Call<PositionData> call, Response<PositionData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PositionData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}