package codeutsava.app.codeutsava.com.codeutsava.Graph;


import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.LocationData;

public interface LocationCallback {
    void onSuccess(LocationData body);

    void onFailure();
}
