package codeutsava.app.codeutsava.com.codeutsava.Maps.Model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionInfo;
import codeutsava.app.codeutsava.com.codeutsava.Maps.PositionCallback;

/**
 * Created by ayush on 03-02-2018.
 */

public class MockPositionProvider implements PositionProvider {
    private PositionData mockData;

    @Override
    public void getPosition(Double lat, Double lon, final PositionCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    public PositionData getMockData() {

        List<PositionInfo> positionInfos = new ArrayList<>();
        PositionInfo positionInfo = new PositionInfo(21.2558113, 81.6522232, "Swachh Bharat Toilet", "Raipur", "5.0", "4:00 a.m.", "1", "1", "0");
        positionInfos.add(positionInfo);
        positionInfo = new PositionInfo(20, 80, "Swachh Bharat Toilet", "Jabalpur", "5.0", "4:00 a.m.", "1", "0", "0");
        positionInfos.add(positionInfo);
        positionInfo = new PositionInfo(22, 82, "Swachh Bharat Toilet", "Bhilai", "5.0", "4:00 a.m.", "0", "1", "0");
        positionInfos.add(positionInfo);
        positionInfo = new PositionInfo(23, 79, "Swachh Bharat Toilet", "Durg", "5.0", "4:00 a.m.", "1", "0", "0");
        positionInfos.add(positionInfo);

        mockData = new PositionData(true, "success", positionInfos);
        return mockData;
    }
}
