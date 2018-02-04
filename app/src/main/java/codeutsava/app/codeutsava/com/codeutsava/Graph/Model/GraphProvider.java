package codeutsava.app.codeutsava.com.codeutsava.Graph.Model;


import codeutsava.app.codeutsava.com.codeutsava.Graph.GraphCallback;
import codeutsava.app.codeutsava.com.codeutsava.Graph.LocationCallback;

public interface GraphProvider {

    void getLocations(int count, LocationCallback callback);

    void getReviewRatingGraph(String id, GraphCallback callback);

    void getUsageGraph(String id, String start_date, String end_date, GraphCallback callback);

}
