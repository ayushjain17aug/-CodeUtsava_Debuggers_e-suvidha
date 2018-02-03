package codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data;

import java.util.List;

public class RatingsReviews {

    private float overall, hygiene, infrastructure, safety;
    private List<String> reviews;
    private List<String> images;

    public RatingsReviews(float overall, float hygiene, float infrastructure, float safety, List<String> reviews, List<String> images) {
        this.overall = overall;
        this.hygiene = hygiene;
        this.infrastructure = infrastructure;
        this.safety = safety;
        this.reviews = reviews;
        this.images = images;
    }

    public float getHygiene() {
        return hygiene;
    }

    public float getInfrastructure() {
        return infrastructure;
    }

    public float getSafety() {
        return safety;
    }

    public float getOverallScore() {
        return overall;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public List<String> getImages() {
        return images;
    }
}
