package codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data;


public class Location {
    private String name, id, address, image;

    public Location(String name, String id, String address, String image) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
