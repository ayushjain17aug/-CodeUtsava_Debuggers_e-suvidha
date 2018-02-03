package codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data;

public class PositionInfo {
    private double latitude, longitude;
    private String ImgUrl,name;

    public PositionInfo(double latitude, double longitude,String name, String ImgUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.ImgUrl = ImgUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
}

