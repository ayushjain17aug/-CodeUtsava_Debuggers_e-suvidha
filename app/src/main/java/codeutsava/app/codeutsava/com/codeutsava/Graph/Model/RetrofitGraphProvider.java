package codeutsava.app.codeutsava.com.codeutsava.Graph.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Api.GraphApi;
import codeutsava.app.codeutsava.com.codeutsava.Graph.GraphCallback;
import codeutsava.app.codeutsava.com.codeutsava.Graph.LocationCallback;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.GraphData;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.LocationData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitGraphProvider implements GraphProvider {

    private final Retrofit retrofit;
    private GraphApi graphApi;

    public RetrofitGraphProvider() {
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
    public void getLocations(int count, final LocationCallback callback) {
        graphApi = retrofit.create(GraphApi.class);
        Call<LocationData> call = graphApi.getLocations();
        call.enqueue(new Callback<LocationData>() {
            @Override
            public void onResponse(Call<LocationData> call, Response<LocationData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LocationData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void getReviewRatingGraph(String id, final GraphCallback callback) {
        graphApi = retrofit.create(GraphApi.class);
        Call<GraphData> call = graphApi.getRatingReviewGraph(id);
        call.enqueue(new Callback<GraphData>() {
            @Override
            public void onResponse(Call<GraphData> call, Response<GraphData> response) {
                callback.onSuccess(response.body().getUrl());
            }

            @Override
            public void onFailure(Call<GraphData> call, Throwable t) {
                callback.onFailure();
            }
        });

    }

    @Override
    public void getUsageGraph(String id, String start_date, String end_date, final GraphCallback callback) {
        graphApi = retrofit.create(GraphApi.class);
        Call<GraphData> call = graphApi.getUsageGraph(id, start_date, end_date);
        call.enqueue(new Callback<GraphData>() {
            @Override
            public void onResponse(Call<GraphData> call, Response<GraphData> response) {
                callback.onSuccess(response.body().getUrl());
            }

            @Override
            public void onFailure(Call<GraphData> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
