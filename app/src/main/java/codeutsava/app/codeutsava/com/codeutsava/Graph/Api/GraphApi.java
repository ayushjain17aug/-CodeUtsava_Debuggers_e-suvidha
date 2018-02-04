package codeutsava.app.codeutsava.com.codeutsava.Graph.Api;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.GraphData;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.LocationData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GraphApi {

    @POST(Urls.GET_ALL_LOCATIONS)
    Call<LocationData> getLocations();


    @FormUrlEncoded
    @POST(Urls.REVIEW_RATING_GRAPH)
    Call<GraphData> getRatingReviewGraph(@Field("id") String id);

    @FormUrlEncoded
    @POST(Urls.USAGE_GRAPH)
    Call<GraphData> getUsageGraph(@Field("id") String id, @Field("start_date") String start_date, @Field("end_date") String end_date);

}