package codeutsava.app.codeutsava.com.codeutsava.Graph.View;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.Location;


public interface LocationView {
    void showProgressBar(boolean b);

    void showLocations(List<Location> locations);

    void showMessage(String message);
}
