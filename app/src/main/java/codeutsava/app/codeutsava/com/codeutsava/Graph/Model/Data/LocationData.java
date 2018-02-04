package codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data;

import java.util.List;

public class LocationData {

    private boolean success;
    private String message;
    private List<Location> locationList;

    public LocationData(boolean success, String message, List<Location> locationList) {
        this.success = success;
        this.message = message;
        this.locationList = locationList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Location> getLocationList() {
        return locationList;
    }
}