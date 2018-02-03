package codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data;

public class PositionInfo {
    private double latitude, longitude;
    private String name, address, rating, hours, flagm, flagf;

    public PositionInfo(double latitude, double longitude, String name, String address, String rating, String hours, String flagf, String flagm) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
        this.flagf = flagf;
        this.flagm = flagm;
        this.rating = rating;
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
        return flagm;
    }

    public void setFlagm(String flagm) {
        this.flagm = flagm;
    }

    public String getFlagf() {
        return flagf;
    }

    public void setFlagf(String flagf) {
        this.flagf = flagf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}

