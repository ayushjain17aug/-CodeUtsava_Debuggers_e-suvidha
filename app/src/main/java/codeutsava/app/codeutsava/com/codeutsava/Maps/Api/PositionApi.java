package codeutsava.app.codeutsava.com.codeutsava.Maps.Api;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PositionApi {

    @FormUrlEncoded
    @POST(Urls.POST_LOCATION)
    Call<PositionData> getPosition(@Field("latitude") Double lat, @Field("longitude") Double lon);


}

