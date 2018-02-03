package codeutsava.app.codeutsava.com.codeutsava.Maps;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;

public interface PositionCallback {
    void onSuccess(PositionData body);

    void onFailure();
}
