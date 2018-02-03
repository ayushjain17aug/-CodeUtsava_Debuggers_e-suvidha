package codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data;

import java.util.List;

public class PositionData {
    private List<PositionInfo> data;
    private boolean success;
    private String message;

    public PositionData(boolean success, String message, List<PositionInfo> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }

    public List<PositionInfo> getData() {
        return data;
    }
}

