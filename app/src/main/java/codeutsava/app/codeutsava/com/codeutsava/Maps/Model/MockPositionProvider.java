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
    public void getPosition(String id, final PositionCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    public PositionData getMockData() {

        List<PositionInfo> positionInfos = new ArrayList<>();
        PositionInfo positionInfo = new PositionInfo(21.2558113, 81.6522232,"Swachh Bharat Toilet","https://www.amarujala.com/photo-gallery/india-news/varanasi-pm-modi-lays-foundation-for-toilet-under-swachh-bharat-abhiyan-in-shahanshahpur");
        positionInfos.add(positionInfo);
        mockData = new PositionData(true, "success", positionInfos);
        return mockData;
    }
}
