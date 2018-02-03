package codeutsava.app.codeutsava.com.codeutsava.Maps.View;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionInfo;

public interface PositionsView {

    void showProgressBar(boolean b);

    void showMessage(String message);

    void showPosition(List<PositionInfo> data);
}
