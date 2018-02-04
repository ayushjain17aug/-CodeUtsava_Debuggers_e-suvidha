package codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data;

public class PositionInfo {
    private double latitude, longitude;
    private String name, address, overall, hours, m, f;

    public PositionInfo(double latitude, double longitude, String name, String address, String rating, String hours, String f, String m) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
        this.f = f;
        this.m = m;
        this.overall = rating;
        this.hours = hours;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagm() {
        return m;
    }

    public void setFlagm(String flagm) {
        this.m = flagm;
    }

    public String getFlagf() {
        return f;
    }

    public void setFlagf(String flagf) {
        this.f = flagf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return overall;
    }

    public void setRating(String rating) {
        this.overall = rating;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}

