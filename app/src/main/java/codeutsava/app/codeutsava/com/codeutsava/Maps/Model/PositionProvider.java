package codeutsava.app.codeutsava.com.codeutsava.Maps.Model;

import codeutsava.app.codeutsava.com.codeutsava.Maps.PositionCallback;

/**
 * Created by ayush on 03-02-2018.
 */

public interface PositionProvider {
    void getPosition(Double lat, Double lon, PositionCallback callback);
}
