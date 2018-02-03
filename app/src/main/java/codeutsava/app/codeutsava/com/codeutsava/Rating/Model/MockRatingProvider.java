package codeutsava.app.codeutsava.com.codeutsava.Rating.Model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingsReviews;
import codeutsava.app.codeutsava.com.codeutsava.Rating.PostRatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.RatingCallback;

public class MockRatingProvider implements RatingProvider {


    private RatingData mockData;
    private PostRatingData mockPostData;

    @Override
    public void getRating(String lt, String lng, final RatingCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    @Override
    public void postRating(String lt, String lng, float overall, float hygiene, float infra, float safety,
                           String review, String feedback, final PostRatingCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockPostData());
            }
        }, 500);
    }

    public RatingData getMockData() {
        List<String> reviews = new ArrayList<>();
        reviews.add("Good");
        reviews.add("Nice \n Clean Premises");
        reviews.add("Good");
        reviews.add("Clean");
        reviews.add("Water supply not proper");
        reviews.add("Poor sanitation");

        List<String> images = new ArrayList<>();
        images.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        images.add("http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        images.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        images.add("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        RatingsReviews ratingsReview = new RatingsReviews(3, 2, 3, 4, reviews, images);
        mockData = new RatingData(true, "success", ratingsReview);
        return mockData;
    }

    public PostRatingData getMockPostData() {
        mockPostData = new PostRatingData("Success", true);
        return mockPostData;
    }
}