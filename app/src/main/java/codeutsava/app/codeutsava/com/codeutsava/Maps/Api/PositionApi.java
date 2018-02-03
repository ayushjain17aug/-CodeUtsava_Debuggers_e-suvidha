package codeutsava.app.codeutsava.com.codeutsava.Maps.Api;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by ayush on 03-02-2018.
 */

public interface PositionApi {

    @GET(Urls.EXPLORE_PG)
    Call<PositionData> getPosition(@Field("access_token") String id);


}

